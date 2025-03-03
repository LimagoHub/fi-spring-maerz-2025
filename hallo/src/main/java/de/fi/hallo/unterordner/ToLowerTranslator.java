package de.fi.hallo.unterordner;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("lower")
public class ToLowerTranslator implements Translator {
    @Override
    public String translate(final String text) {
        return text.toLowerCase();
    }
}
