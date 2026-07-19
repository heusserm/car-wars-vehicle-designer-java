package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class GasTankType {
    public final String name;
    public final int dp;
    public final double weightPerGallon;
    public final double costPerGallon;

    public GasTankType(String name, int dp, double weightPerGallon, double costPerGallon) {
        this.name = name;
        this.dp = dp;
        this.weightPerGallon = weightPerGallon;
        this.costPerGallon = costPerGallon;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final List<GasTankType> ALL = Arrays.asList(
            new GasTankType("Economy", 2, 1, 2),
            new GasTankType("Heavy-Duty", 4, 2, 5),
            new GasTankType("Racing", 4, 5, 10),
            new GasTankType("Duelling", 8, 10, 25)
    );

    /** Spaces taken by a gas tank of the given total gallon capacity. */
    public static int spacesFor(double gallons) {
        if (gallons <= 5) return 0;
        return (int) Math.ceil((gallons - 5) / 10.0);
    }
}
