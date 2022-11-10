package com.itv.ats.ca.smoke.steps;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Locale.ENGLISH;

public class CreateTestScenario {

    private static final DateTimeFormatter dateInputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", ENGLISH);

    @FindBy(css = "[ng-model='scenariosManagement.ScenariosManager.StationSelector.stations.selected']")
    private static WebElementFacade stationSelection;

    public static Performable withStartDate(LocalDate startDate) {
        return Task.where("{0} chooses " + startDate + " as start",
                actor -> {
                    actor.attemptsTo(
                            Enter.theValue("Smoke Test").into("#newScenarioName"),
                            SelectFromOptions.byVisibleText("ITV1").from(stationSelection),
                            Enter.theValue(CreateTestScenario.dateInputFormat.format(startDate)).into("#newScenarioTargetDate"),
                            Enter.theValue(CreateTestScenario.dateInputFormat.format(startDate)).into("#newScenarioEndDate"),
                            Click.on("newScenarioCreate")
                            );
                });
    }

}
