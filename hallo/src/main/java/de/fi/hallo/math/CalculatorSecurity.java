package de.fi.hallo.math;

public class CalculatorSecurity implements Calculator {

    private final Calculator calculator;

    public CalculatorSecurity(final Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public double add(final double a, final double b) {
        System.out.println("Du kommst hier rein");
        return calculator.add(a, b);
    }

    @Override
    public double sub(final double a, final double b) {
        return calculator.sub(a, b);
    }
}
