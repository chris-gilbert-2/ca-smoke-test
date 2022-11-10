package com.itv.ats.ca.smoke.steps;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;

public class Login {

    public static Performable as(String username, String password) {
        return Task.where(
                "{0} logs in as " + username,
                Open.url("http://localhost:8080"),
                Enter.theValue(username).into("#username"),
                Enter.theValue(password).into("#password"),
                Click.on("#loginButton")
        );
    }

}
