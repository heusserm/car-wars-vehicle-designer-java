package com.xndev.carwarsvehicledesignerjava.util;

public class VehicleStats {
    public final double totalCost;
    public final double ammoCost;
    public final double totalWeight;
    public final double maxLoad;
    public final double spacesUsed;
    public final double spacesAvailable;
    public final int handlingClass;
    public final int powerFactors;
    public final int acceleration;
    public final double topSpeed;
    public final boolean isUnderpowered;
    public final int driverWeight;
    public final int driverSpaces;
    public final int driverDp;

    public VehicleStats(double totalCost, double ammoCost, double totalWeight, double maxLoad, double spacesUsed,
                         double spacesAvailable, int handlingClass, int powerFactors,
                         int acceleration, double topSpeed, boolean isUnderpowered,
                         int driverWeight, int driverSpaces, int driverDp) {
        this.totalCost = totalCost;
        this.ammoCost = ammoCost;
        this.totalWeight = totalWeight;
        this.maxLoad = maxLoad;
        this.spacesUsed = spacesUsed;
        this.spacesAvailable = spacesAvailable;
        this.handlingClass = handlingClass;
        this.powerFactors = powerFactors;
        this.acceleration = acceleration;
        this.topSpeed = topSpeed;
        this.isUnderpowered = isUnderpowered;
        this.driverWeight = driverWeight;
        this.driverSpaces = driverSpaces;
        this.driverDp = driverDp;
    }

    public double weightAvailable() {
        return maxLoad - totalWeight;
    }
}
