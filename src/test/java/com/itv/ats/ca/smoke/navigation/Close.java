package com.itv.ats.ca.smoke.navigation;

import com.itv.ats.ca.smoke.scenario.ScenarioForm;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class Close {

    public static Performable theScenarioView() {
        return Task.where("{0} closes the scenario view",
                Click.on(ScenarioForm.CLOSE_SCENARIO));
    }
}
