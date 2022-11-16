package com.itv.ats.ca.smoke.scenario;


import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.TextContent;
import net.thucydides.core.annotations.Title;

import java.util.Collection;
import java.util.List;

public class Scenarios {


    public static Question<Collection<String>> names() {
        return actor -> actor.asksFor(Text.ofEach(ScenarioList.NAMES));
    }

}
