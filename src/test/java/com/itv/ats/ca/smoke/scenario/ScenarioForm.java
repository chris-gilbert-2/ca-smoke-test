package com.itv.ats.ca.smoke.scenario;

import org.openqa.selenium.By;

public class ScenarioForm {

    static By SCENARIO_NAME_FIELD = By.id("newScenarioName");
    static By STATION_SELECTION = By.cssSelector("[ng-model='scenariosManagement.ScenariosManager.StationSelector.stations.selected']");
    static By START_DATE = By.id("newScenarioTargetDate");
    static By END_DATE = By.id("newScenarioEndDate");
    static By CREATE_BUTTON = By.id("newScenarioCreate");
    public static By CLOSE_SCENARIO = By.cssSelector("ul.icon-menu i.fa-remove");
}
