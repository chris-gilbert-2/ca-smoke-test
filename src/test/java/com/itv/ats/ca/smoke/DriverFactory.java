package com.itv.ats.ca.smoke;

import dev.failsafe.RetryPolicy;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.http.*;
import org.openqa.selenium.remote.tracing.TracedHttpClient;
import org.openqa.selenium.remote.tracing.opentelemetry.OpenTelemetryTracer;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class DriverFactory {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DriverFactory.class);

    private static RemoteWebDriver driver;


    private static ChromeOptions options() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-dev-shm-usage");
//        if (System.getProperty("run.headless") != null && System.getProperty("run.headless").equals("true")) {
//            options.setHeadless(true);
//        }
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        return options;
    }

    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options());
        driver.manage().window().maximize();
        return driver;
    }


    public static RemoteWebDriver getRemoteDriver(
            ChromeOptions options) {
        ChromeDriverService driverService = ChromeDriverService.createServiceWithConfig(options());
        RetryRequest retryRequest = new RetryRequest();
        Field readTimeoutPolicyField;
        try {
            readTimeoutPolicyField = retryRequest.getClass().getDeclaredField("readTimeoutPolicy");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        readTimeoutPolicyField.setAccessible(true);

        RetryPolicy<Object> readTimeoutPolicy =
                RetryPolicy.builder()
                        .handle(Arrays.asList(TimeoutException.class, io.netty.handler.timeout.TimeoutException.class))
                        .withBackoff(1, 4, ChronoUnit.SECONDS)
                        .withMaxRetries(3)
                        .withMaxDuration(Duration.ofSeconds(300))
                        .onRetry(e -> System.out.printf(
                                "Read timeout #%s. Retrying.%n",
                                e.getAttemptCount()))
                        .build();

        FieldUtils.removeFinalModifier(readTimeoutPolicyField);
        try {
            readTimeoutPolicyField.set(retryRequest, readTimeoutPolicy);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Filter filter = new AddSeleniumUserAgent().andThen(retryRequest);
        ClientConfig config = ClientConfig
                .defaultConfig()
                .baseUrl(driverService.getUrl())
                .readTimeout(Duration.ofSeconds(90))
                .withFilter(filter);
        OpenTelemetryTracer tracer = OpenTelemetryTracer.getInstance();
        HttpClient.Factory httpClientFactory = HttpClient.Factory.createDefault();
        TracedHttpClient.Factory tracedHttpClientFactory = new TracedHttpClient.Factory(
                tracer,
                httpClientFactory);
        CommandExecutor executor = new HttpCommandExecutor(Collections.emptyMap(), config, tracedHttpClientFactory);
        TracedCommandExecutor tracedCommandExecutor = new TracedCommandExecutor(executor, tracer);
        return new RemoteWebDriver(tracedCommandExecutor, options);
    }

}