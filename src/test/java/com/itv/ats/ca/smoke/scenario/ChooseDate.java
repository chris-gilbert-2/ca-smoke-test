package com.itv.ats.ca.smoke.scenario;

import com.itv.ats.ca.smoke.navigation.DatePicker;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import org.openqa.selenium.By;

import java.time.LocalDate;

public class ChooseDate {


    public static Performable of(LocalDate selection) {
        return Task.where("{0} selects the date " + selection,
                Click.on(DatePicker.year).then(SelectFromOptions.byValue("2025").from(DatePicker.year)),
                Click.on(DatePicker.month).then(SelectFromOptions.byValue("1").from(DatePicker.month)),
                Click.on(By.cssSelector("td[data-day='15']")));

    }
}
