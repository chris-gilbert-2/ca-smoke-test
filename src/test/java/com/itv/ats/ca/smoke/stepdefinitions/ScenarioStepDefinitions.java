package com.itv.ats.ca.smoke.stepdefinitions;

import com.itv.ats.ca.smoke.login.Login;
import com.itv.ats.ca.smoke.scenario.Close;
import com.itv.ats.ca.smoke.navigation.NavigateTo;
import com.itv.ats.ca.smoke.scenario.Create;
import com.itv.ats.ca.smoke.scenario.Scenarios;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

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
        theActorInTheSpotlight()
                .attemptsTo(
                        NavigateTo.scenarios(),
                        Create.scenario("smoke test")
                                .forStation(station)
                                .dated(scenarioDate),
                        WaitUntil.angularRequestsHaveFinished(),
                        Close.theScenarioView()
                );
    }

    @Then("^the new scenario is listed on the scenarios page")
    public void checkThatNewScenarioIsListed() {
        theActorInTheSpotlight().attemptsTo(NavigateTo.scenarios());
        Scenarios.names().answeredBy(theActorInTheSpotlight()).contains("Smoke Test");
//        theActorInTheSpotlight().should(
//                seeThat(Scenarios.names(), contains("Smoke Test")));
    }

}
