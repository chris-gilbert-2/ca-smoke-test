package com.itv.ats.ca.smoke.steps;

import com.itv.ats.ca.smoke.pages.LandingPage;
import io.cucumber.java.en.Given;

public class DataViewSteps {

    @Given("I am logged in")
    public void login() {
        System.out.println("running the test");
        new LandingPage();
    }

}
