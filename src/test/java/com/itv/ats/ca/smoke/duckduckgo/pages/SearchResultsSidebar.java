package com.itv.ats.ca.smoke.duckduckgo.pages;

import net.serenitybdd.core.pages.PageComponent;

public class SearchResultsSidebar extends PageComponent {

    public String heading() {
        return $(".module__title").getText();
    }
}
