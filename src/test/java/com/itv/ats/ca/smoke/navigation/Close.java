package com.itv.ats.ca.smoke.navigation;

import com.itv.ats.ca.smoke.scenario.CreateScenarioForm;
import com.itv.ats.ca.smoke.scenario.FilterScenarioForm;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Close {

    public static Performable theScenarioView() {
        return Task.where("{0} closes the scenario view",
                WaitUntil.the(FilterScenarioForm.CLOSE_SCENARIO, isVisible()).forNoMoreThan(Duration.ofSeconds(20))
                        .then(Click.on(FilterScenarioForm.CLOSE_SCENARIO)));
    }
}
