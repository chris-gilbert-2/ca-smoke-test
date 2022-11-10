package com.itv.ats.ca.smoke.duckduckgo;

import com.itv.ats.ca.smoke.duckduckgo.actions.NavigateActions;
import com.itv.ats.ca.smoke.duckduckgo.actions.SearchActions;
import com.itv.ats.ca.smoke.duckduckgo.pages.SearchResultsSidebar;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenSearchingByKeyWord {

    @Managed(driver = "chrome")
    WebDriver driver;

    NavigateActions navigate;
    SearchActions search;
    SearchResultsSidebar searchResultsSidebar;

    @Test
    void theKeywordShouldAppearInTheResultsSidebar() {
        navigate.toTheDuckDuckGoSearchPage();
        search.byKeyword("Cucumber");
        Serenity.reportThat("The keyword should appear in the sidebar heading",
                () -> assertThat(searchResultsSidebar.heading()).isEqualTo("Cucumber"));

    }
}
