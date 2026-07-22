package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class TargetingComputer {
    public final String name;
    public final int cost;
    public final double weight;
    public final double space;

    public TargetingComputer(String name, int cost, double weight, double space) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.space = space;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final TargetingComputer NONE = new TargetingComputer("None", 0, 0, 0);

    /**
     * Regular and Hi-Res Targeting Computers are pure electronics with no
     * weight/space cost. Cyberlink is a driver neural interface that takes up
     * physical space and weight in the vehicle.
     */
    public static final List<TargetingComputer> ALL = Arrays.asList(
            NONE,
            new TargetingComputer("Targeting Computer", 1000, 0, 0),
            new TargetingComputer("Hi-Res Targeting Computer", 4000, 0, 0),
            new TargetingComputer("Cyberlink", 16000, 100, 1)
    );
}
