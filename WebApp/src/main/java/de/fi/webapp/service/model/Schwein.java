package de.fi.webapp.service.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
@Data
@Setter(AccessLevel.PRIVATE)
public class Schwein {

    private String name;
    private int gewicht;

    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }
}
