package com.itv.ats.ca.smoke.scenario;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class CreateTestScenario {

    private String station;
    private String date;

    public static CreateTestScenario defineATestScenario() {
        return new CreateTestScenario();
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
                Enter.theValue("smoke test").into(ScenarioForm.SCENARIO_NAME_FIELD),
                SelectFromOptions.byVisibleText(station).from(ScenarioForm.STATION_SELECTION),
                Clear.field(ScenarioForm.START_DATE)
                        .then(Click.on(ScenarioForm.START_DATE))
//                Enter.theValue(date).into(ScenarioForm.START_DATE)
//                        Enter.theValue(date).into(ScenarioForm.END_DATE)
//                .then(Click.on(ScenarioForm.CREATE_BUTTON))
        );
    }
}
