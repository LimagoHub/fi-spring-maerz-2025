package de.fi.hallo.unterordner;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("upper")
public class ToUpperTranslator implements Translator {

    public String translate(String text) {
        return text.toUpperCase();
    }
}
