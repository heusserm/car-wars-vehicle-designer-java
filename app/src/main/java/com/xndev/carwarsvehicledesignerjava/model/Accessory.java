package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class Accessory {
    public final String name;
    public final int cost;
    public final double weight;
    public final double space;
    /** Special rules text shown to the user; not used in the calculations. */
    public final String description;

    public Accessory(String name, int cost, double weight, double space, String description) {
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.space = space;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final List<Accessory> ALL = Arrays.asList(
            new Accessory("Antilock Braking System (ABS)", 1000, 0, 0,
                    "Eliminates tire damage from rapid deceleration and reduces braking hazards "
                            + "(rain, snow, ice, oil, gravel) by D1. Cannot be used on oversized vehicles."),
            new Accessory("Active Suspension", 4000, 100, 0,
                    "Adds 1 to the HC of any car, trike or cycle (not off-road). If a wheel is lost, "
                            + "the vehicle suffers a D6 hazard instead of going directly to HC -6, and its "
                            + "HC drops by 2 instead of 3."),
            new Accessory("Gunner", 0, 150, 1,
                    "A crew member to operate weapons."),
            new Accessory("Gunner w/ Body Armor", 250, 150, 1,
                    "A crew member to operate weapons, wearing body armor "
                            + "(3 hits before it is useless, doubling DP from 3 to 6)."),
            new Accessory("No-Paint Windshield", 1000, 0, 0,
                    "Paint clouds have no effect on vehicles equipped with this windshield. Helmets "
                            + "and gas masks can be modified with this material for $100."),
            new Accessory("Laser Battery", 500, 100, 1,
                    "1 DP. Located adjacent to the power plant and destroyed after it is. Required "
                            + "for a gas-powered vehicle to fire lasers or run power-draining electronics "
                            + "(radar, IR, etc.). Holds 100 power units. A recharge costs $10 and takes two "
                            + "minutes."),
            new Accessory("Link", 50, 0, 0,
                    "Links two or more pieces of equipment so they can both be activated by a "
                            + "single firing action. Normally used on weapons, but can be hooked to other "
                            + "equipment. See Linked Weapons, p. 45."),
            new Accessory("Fire Extinguisher", 300, 150, 1,
                    "Roll 1 die at the end of each turn the vehicle is on fire. On a 1 to 3 "
                            + "(1 to 2 with a gas engine), the fire is put out. Destroyed when the power "
                            + "plant is destroyed."),
            new Accessory("Overdrive", 400, 0, 0, // $100 per wheel; priced for the standard 4-wheel vehicle
                    "$100 per wheel (must be bought for all wheels). Increases top speed by 20 mph when "
                            + "activated, but reduces acceleration by 5 mph (minimum 2.5 mph). "
                            + "Activation/deactivation is a firing action. If deactivated above normal top "
                            + "speed, the vehicle must decelerate at least 15 mph per turn until at or below "
                            + "its pre-overdrive maximum. Power consumption while active is figured as if "
                            + "going 20 mph slower. Installation is a Hard job. Cannot be used on hovercraft, "
                            + "boats or helicopters.")
    );
}
