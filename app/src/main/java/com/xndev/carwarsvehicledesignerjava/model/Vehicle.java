package com.xndev.carwarsvehicledesignerjava.model;

import java.util.ArrayList;

/** A vehicle saved by the user. */
public class Vehicle {
    public final String name;
    public final String chassis;
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

    public Vehicle(String name, String chassis, String powerPlant, String notes,
                    int armorFront, int armorBack, int armorLeft, int armorRight,
                    int armorTop, int armorUnderbody, int tireDp, ArrayList<String> weapons) {
        this.name = name;
        this.chassis = chassis;
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
    }
}
