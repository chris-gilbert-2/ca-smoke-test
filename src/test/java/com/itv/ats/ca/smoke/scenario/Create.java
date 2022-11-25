package com.itv.ats.ca.smoke.scenario;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;
import static org.apache.commons.lang3.StringUtils.truncate;

public class Create {

    private String name;
    private String station;
    private LocalDate date;

    public static Create scenario() {
        Create scenarioCreation = new Create();
        return scenarioCreation;
    }

    public Create named(String name) {
        return this.withARandomName(name);
    }

    public Create withARandomName(String prefix) {
        this.name = truncate(prefix + " " + randomUUID(), 0, 20);
        return this;
    }

    public Create withARandomName() {
        return withARandomName("");
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
