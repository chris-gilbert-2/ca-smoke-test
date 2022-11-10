package com.itv.ats.ca.smoke.steps;

import com.itv.ats.ca.smoke.pages.StartDatePicker;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actions.selectactions.SelectByValueFromBy;

import java.time.LocalDate;

public class CreateScenario {

    StartDatePicker date;

    public static Performable named(String name) {
        return Task.where("{0} names a new scenario as " + name,
                actor -> {
                    actor.attemptsTo(Enter.theValue(name).into("#newScenarioName"));
                });
    }

    public static Performable withStartDate(LocalDate startDate) {
        return Task.where("{0} chooses " + startDate + " as start",
                actor -> {
                    actor.attemptsTo(
                            Click.on("#newScenarioTargetDate"),
                            Click.on(".pika-label"), //.afterWaitingUntilEnabled(),
                            SelectFromOptions.byValue("November").from("select.pika-select-month")
                            );
                });
    }

}
