package com.itv.ats.ca.smoke.login;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Login {

    public static Performable withCredentials(String username, String password) {
        return Task.where("{0} logs in as #username",
                Enter.theValue(username).into(LoginForm.USER_NAME_FIELD),
                Enter.theValue(password).into(LoginForm.PASSWORD_FIELD)
                        .then(Click.on(LoginForm.LOGIN_BUTTON))
        );
    }
}
