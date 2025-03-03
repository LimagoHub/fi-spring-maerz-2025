package de.fi.hallo.unterordner;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("lower")
@Profile("test")
public class ToLowerTranslator implements Translator {
    @Override
    public String translate(final String text) {
        return text.toLowerCase();
    }
}
