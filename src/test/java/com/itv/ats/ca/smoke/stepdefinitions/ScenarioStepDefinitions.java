package com.itv.ats.ca.smoke.stepdefinitions;

import com.itv.ats.ca.smoke.login.Login;
import com.itv.ats.ca.smoke.navigation.NavigateTo;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class ScenarioStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^(.*) has logged into Caspa")
    public void login(String actor) {
        theActorCalled(actor)
                .attemptsTo(NavigateTo.theCaspaLandingPage(),
                        Login.withCredentials("test1", "test2"));
    }

    @When("^s?he creates a test scenario for (.*) dated ([0-9]{4}-[0-9]{2}-[0-9]{2})")
    public void createTestScenario(String station, String scenarioDate) {
        System.out.println("Creating test scenario for " + station + " on " + scenarioDate);

    }
}
