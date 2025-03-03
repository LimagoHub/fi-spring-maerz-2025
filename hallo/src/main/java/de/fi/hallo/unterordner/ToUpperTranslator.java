package de.fi.hallo.unterordner;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("upper")
@Profile("production")
public class ToUpperTranslator implements Translator {

    public String translate(String text) {
        return text.toUpperCase();
    }
}
