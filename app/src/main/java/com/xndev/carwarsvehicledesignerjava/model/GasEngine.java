package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class GasEngine {
    public final String name;
    public final int cost;
    public final int weight;
    public final int spaces;
    public final int dp;
    public final int power;
    public final int baseMpg;

    public GasEngine(String name, int cost, int weight, int spaces, int dp, int power, int baseMpg) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.spaces = spaces;
        this.dp = dp;
        this.power = power;
        this.baseMpg = baseMpg;
    }

    @Override
    public String toString() {
        return String.format("%s (%d PF)", name, power);
    }

    public static final List<GasEngine> ALL = Arrays.asList(
            new GasEngine("10 cid", 400, 60, 1, 1, 300, 80),
            new GasEngine("30 cid", 750, 115, 1, 2, 500, 70),
            new GasEngine("50 cid", 1250, 150, 1, 3, 700, 60),
            new GasEngine("100 cid", 2500, 265, 2, 6, 1300, 50),
            new GasEngine("150 cid", 4000, 375, 3, 9, 1900, 45),
            new GasEngine("200 cid", 5500, 480, 4, 12, 2500, 35),
            new GasEngine("250 cid", 6500, 715, 5, 14, 3200, 28),
            new GasEngine("300 cid", 7800, 825, 6, 16, 4000, 22),
            new GasEngine("350 cid", 9500, 975, 7, 19, 5000, 18),
            new GasEngine("400 cid", 10500, 1050, 8, 22, 6300, 15),
            new GasEngine("450 cid", 11700, 1125, 9, 24, 7800, 13),
            new GasEngine("500 cid", 13000, 1200, 10, 26, 9500, 12),
            new GasEngine("700 cid", 19000, 1275, 14, 30, 13000, 10)
    );
}
