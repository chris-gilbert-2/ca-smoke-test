package com.itv.ats.ca.smoke.features;

import com.itv.ats.ca.smoke.steps.CreateScenario;
import com.itv.ats.ca.smoke.steps.Login;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.annotations.CastMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Month;

import static java.time.Month.JANUARY;

@ExtendWith(SerenityJUnit5Extension.class)
public class Scenarios {

    @CastMember
    Actor simon;

    @Test
    @DisplayName("Create a new scenario")
    void createNewScenario() {
        simon.attemptsTo(
                Login.as("test1", "test2"),
                Click.on("#scenariosMenu"),
                CreateScenario.named("test"),
                CreateScenario.withStartDate(LocalDate.of(2023, JANUARY, 23)));

    }

}
