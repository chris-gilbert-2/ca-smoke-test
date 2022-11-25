package com.itv.ats.ca.smoke.stepdefinitions;

import com.itv.ats.ca.smoke.login.Login;
import com.itv.ats.ca.smoke.navigation.NavigateTo;
import com.itv.ats.ca.smoke.scenario.Close;
import com.itv.ats.ca.smoke.scenario.Create;
import com.itv.ats.ca.smoke.scenario.Delete;
import com.itv.ats.ca.smoke.scenario.Scenarios;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.UUID.randomUUID;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ScenarioStepDefinitions {

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return theActorCalled(actorName);
    }

    @ParameterType("[0-9]{4}-[0-9]{2}-[0-9]{2}")
    public LocalDate isoDate(String isoDate) {
        return LocalDate.parse(isoDate, DateTimeFormatter.ISO_DATE);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void cleanup() {

        String newScenario = theActorInTheSpotlight().recall("scenario-name");
        theActorInTheSpotlight().attemptsTo(
                Delete.scenario(newScenario)
        );

    }

    @Given("{actor} has logged into Caspa")
    public void login(Actor actor) {
        actor
                .attemptsTo(
                        NavigateTo.theCaspaLandingPage(),
                        Login.withCredentials("test1", "test2"));
    }

    @When("{actor} creates a test scenario for {word} dated {isoDate}")
    public void createTestScenario(Actor actor, String station, LocalDate scenarioDate) {
        String scenarioName = "smoke test " + randomUUID();
        actor
                .attemptsTo(
                        NavigateTo.scenarios(),
                        Create.scenario(scenarioName)
                                .forStation(station)
                                .dated(scenarioDate),
                        WaitUntil.angularRequestsHaveFinished(),
                        Close.theScenarioView()
                );
        actor.remember("scenario-name", scenarioName);
    }

    @Then("the new scenario is listed on the scenarios page")
    public void checkThatNewScenarioIsListed() {
        String newScenario = theActorInTheSpotlight().recall("scenario-name");
        theActorInTheSpotlight().attemptsTo(
                NavigateTo.scenarios(),
                Ensure.that(Scenarios.names()).contains(newScenario)
        );
//        Scenarios.names().answeredBy(theActorInTheSpotlight()).contains("Smoke Test");
//        theActorInTheSpotlight().should(
//                seeThat(Scenarios.names(), contains("Smoke Test")));
    }

}
