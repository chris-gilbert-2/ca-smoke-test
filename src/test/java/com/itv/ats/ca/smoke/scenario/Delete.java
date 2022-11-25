package com.itv.ats.ca.smoke.scenario;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static com.itv.ats.ca.smoke.scenario.ScenarioList.DELETE;

public class Delete {

    public static Performable scenario(String scenarioName) {
        return Task.where("{0} deletes the scenario named " + scenarioName,
                Click.on(DELETE(scenarioName)));
    }
}
