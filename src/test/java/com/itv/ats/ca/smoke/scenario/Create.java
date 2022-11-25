package com.itv.ats.ca.smoke.scenario;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Create {

    private String name;
    private String station;
    private LocalDate date;

    public static Create scenario(String name) {
        Create scenarioCreation = new Create();
        scenarioCreation.name = name;
        return scenarioCreation;
    }

    public Create forStation(String station) {
        this.station = station;
        return this;
    }

    public Performable dated(LocalDate date) {
        this.date = date;
        return create();
    }

    private Performable create() {
        return Task.where("{0} creates a scenario for " + station + " on " + date,
                Enter.theValue(name).into(CreateScenarioForm.SCENARIO_NAME_FIELD),
                SelectFromOptions.byVisibleText(station).from(CreateScenarioForm.STATION_SELECTION),
                Click.on(CreateScenarioForm.START_DATE),
                ChooseDate.of(date)
                        .then(Click.on(CreateScenarioForm.CREATE_BUTTON))
        );
    }
}
