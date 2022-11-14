package com.itv.ats.ca.smoke.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {

    public static Performable theCaspaLandingPage() {
        return Task.where("{0} opens the Caspa landing page",
                Open.browserOn().the(CaspaLandingPage.class)
        );
    }

    public static Performable scenarios() {
        return Task.where("{0} navigates to scenarios",
                Click.on(LeftNavigation.SCENARIOS_LINK));
    }
}