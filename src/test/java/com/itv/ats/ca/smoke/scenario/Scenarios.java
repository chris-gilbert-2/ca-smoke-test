package com.itv.ats.ca.smoke.scenario;


import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Collection;

public class Scenarios {

    public static Question<Collection<String>> names() {
        return actor -> actor.asksFor(Text.ofEach(ScenarioList.NAMES));
    }



}
