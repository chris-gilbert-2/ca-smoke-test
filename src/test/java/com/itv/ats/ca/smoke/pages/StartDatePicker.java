package com.itv.ats.ca.smoke.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class StartDatePicker extends PageObject {

    @FindBy(className = "pika-select-year")
    WebElementFacade yearSelection;

    public void selectYear(int year) {
        yearSelection.selectByValue(Integer.toString(year));
    }

}
