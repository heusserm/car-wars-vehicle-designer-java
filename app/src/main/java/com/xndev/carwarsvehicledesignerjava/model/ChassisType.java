package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class ChassisType {
    public final String name;
    /** Fractional change to the body's max load (e.g. -0.10 for Light). */
    public final double maxLoadModifier;
    /** Fractional change to the body's price (e.g. 0.50 for Heavy). */
    public final double priceModifier;

    public ChassisType(String name, double maxLoadModifier, double priceModifier) {
        this.name = name;
        this.maxLoadModifier = maxLoadModifier;
        this.priceModifier = priceModifier;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final List<ChassisType> ALL = Arrays.asList(
            new ChassisType("Light", -0.10, -0.20),
            new ChassisType("Standard", 0, 0),
            new ChassisType("Heavy", 0.10, 0.50),
            new ChassisType("Extra Heavy", 0.20, 1.00)
    );
}
