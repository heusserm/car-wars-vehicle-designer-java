package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class ElectricPowerPlant {
    public final String name;
    public final int cost;
    public final int weight;
    public final int spaces;
    public final int dp;
    public final int powerFactors;

    public ElectricPowerPlant(String name, int cost, int weight, int spaces, int dp, int powerFactors) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.spaces = spaces;
        this.dp = dp;
        this.powerFactors = powerFactors;
    }

    @Override
    public String toString() {
        return String.format("%s (%d PF)", name, powerFactors);
    }

    public static final List<ElectricPowerPlant> ALL = Arrays.asList(
            new ElectricPowerPlant("Small", 500, 500, 3, 5, 800),
            new ElectricPowerPlant("Medium", 1000, 700, 4, 8, 1400),
            new ElectricPowerPlant("Large", 2000, 900, 5, 10, 2000),
            new ElectricPowerPlant("Super", 3000, 1100, 6, 12, 2600),
            new ElectricPowerPlant("Sport", 6000, 1000, 6, 12, 3000),
            new ElectricPowerPlant("Thundercat", 12000, 2000, 8, 15, 6700)
    );
}
