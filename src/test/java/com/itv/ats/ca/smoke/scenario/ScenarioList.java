package com.itv.ats.ca.smoke.scenario;

import org.openqa.selenium.By;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ScenarioList {

    public static By NAMES = By.cssSelector(".scenario-list div.scenario-list__item span.scenario-list__master");

    public static By DELETE(String scenarioName) {
        return By.cssSelector(String.format("div[title='%s'] button.fa-trash-o", scenarioName));
    }

    public static By CONFIRM_DELETE = By.cssSelector("button.btn--danger");
}
