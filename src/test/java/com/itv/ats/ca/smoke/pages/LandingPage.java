package com.itv.ats.ca.smoke.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LandingPage {

    @FindBy(id = "username")
    private WebElement usernameTextBox;

    @FindBy(id = "password")
    private WebElement passwordTextBox;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    private WebDriver driver;

    public void login () {
        driver.navigate().to("http://localhost:8080");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(usernameTextBox));
        usernameTextBox.sendKeys("test-user");
        passwordTextBox.sendKeys("1234");
        loginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(visibilityOf(driver.findElement(By.className("l-navigation"))));

    }
}
