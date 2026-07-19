package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class TireType {
    public final String name;
    public final int price;
    public final int weight;
    public final int dp;

    public TireType(String name, int price, int weight, int dp) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.dp = dp;
    }

    @Override
    public String toString() {
        return String.format("%s (%d DP)", name, dp);
    }

    public static final List<TireType> ALL = Arrays.asList(
            new TireType("Standard", 50, 30, 4),
            new TireType("Heavy-Duty", 100, 40, 6),
            new TireType("Puncture-Resistant", 200, 50, 9),
            new TireType("Solid", 500, 75, 12),
            new TireType("Plasticore", 1000, 150, 25)
    );
}
