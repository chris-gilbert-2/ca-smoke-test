package com.itv.ats.ca.smoke.scenario;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.LocalDate;
import java.time.Month;

public class CreateTestScenario {

    private String name;
    private String station;
    private String date;

    public static CreateTestScenario defineATestScenarioNamed(String name) {
        CreateTestScenario scenarioCreation = new CreateTestScenario();
        scenarioCreation.name = name;
        return scenarioCreation;
    }

    public CreateTestScenario forStation(String station) {
        this.station = station;
        return this;
    }

    public CreateTestScenario on(String date) {
        this.date = date;
        return this;
    }

    public Performable andCreateIt() {
        return Task.where("{0} creates a scenario for " + station + " on " + date,
                WaitUntil.angularRequestsHaveFinished(),
                Enter.theValue(name).into(ScenarioForm.SCENARIO_NAME_FIELD),
                SelectFromOptions.byVisibleText(station).from(ScenarioForm.STATION_SELECTION),
                Click.on(ScenarioForm.START_DATE),
                ChooseDate.of(LocalDate.of(2025, Month.DECEMBER, 31))
                        .then(Click.on(ScenarioForm.CREATE_BUTTON))
        );
    }
}
