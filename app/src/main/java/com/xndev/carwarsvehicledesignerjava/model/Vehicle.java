package com.xndev.carwarsvehicledesignerjava.model;

import java.util.ArrayList;

/** A vehicle saved by the user. */
public class Vehicle {
    public final String name;
    public final String bodyType;
    public final String chassisType;
    public final String suspensionType;
    public final String powerPlant;
    public final String notes;
    public final int armorFront;
    public final int armorBack;
    public final int armorLeft;
    public final int armorRight;
    public final int armorTop;
    public final int armorUnderbody;
    public final int tireDp;
    public final ArrayList<String> weapons;
    public final boolean hasBodyArmor;
    public final String targetingComputer;
    public final double totalCost;
    public final double weight;
    public final int handlingClass;
    public final int acceleration;
    public final boolean isUnderpowered;
    public final double topSpeed;

    public Vehicle(String name, String bodyType, String chassisType, String suspensionType, String powerPlant,
                    String notes, int armorFront, int armorBack, int armorLeft, int armorRight,
                    int armorTop, int armorUnderbody, int tireDp, ArrayList<String> weapons,
                    boolean hasBodyArmor, String targetingComputer,
                    double totalCost, double weight, int handlingClass, int acceleration,
                    boolean isUnderpowered, double topSpeed) {
        this.name = name;
        this.bodyType = bodyType;
        this.chassisType = chassisType;
        this.suspensionType = suspensionType;
        this.powerPlant = powerPlant;
        this.notes = notes;
        this.armorFront = armorFront;
        this.armorBack = armorBack;
        this.armorLeft = armorLeft;
        this.armorRight = armorRight;
        this.armorTop = armorTop;
        this.armorUnderbody = armorUnderbody;
        this.tireDp = tireDp;
        this.weapons = weapons;
        this.hasBodyArmor = hasBodyArmor;
        this.targetingComputer = targetingComputer;
        this.totalCost = totalCost;
        this.weight = weight;
        this.handlingClass = handlingClass;
        this.acceleration = acceleration;
        this.isUnderpowered = isUnderpowered;
        this.topSpeed = topSpeed;
    }
}
