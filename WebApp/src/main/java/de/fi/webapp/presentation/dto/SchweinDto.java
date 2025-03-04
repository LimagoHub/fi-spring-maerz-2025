package de.fi.webapp.presentation.dto;

import jakarta.validation.constraints.DecimalMin;

public class SchweinDto {

    private String name;

    @DecimalMin("10")
    private int gewicht;
}
