package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class Weapon {
    public final String name;
    public final String abbreviation;
    public final String toHit;
    public final String damage;
    public final int dp;
    public final int cost;
    public final int weight;
    public final double space;
    /** Rounds per box of ammo; 0 means the weapon doesn't use purchasable ammo (energy weapons, single-shot rounds). */
    public final int ammoPerBox;
    /** Cost per shot fired, in dollars; 0 when ammoPerBox is 0. */
    public final double costPerShot;
    /** Weight per shot fired, in pounds; 0 when ammoPerBox is 0. */
    public final double weightPerShot;

    public Weapon(String name, String abbreviation, String toHit, String damage, int dp,
                  int cost, int weight, double space, int ammoPerBox, double costPerShot, double weightPerShot) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.toHit = toHit;
        this.damage = damage;
        this.dp = dp;
        this.cost = cost;
        this.weight = weight;
        this.space = space;
        this.ammoPerBox = ammoPerBox;
        this.costPerShot = costPerShot;
        this.weightPerShot = weightPerShot;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, damage);
    }

    /**
     * Core offensive weapons from the Weapon Charts (Small/Large-Bore Projectile,
     * Rockets, Lasers, Flamethrowers). Dropped gases/liquids/solids and hand
     * dischargers are left out of this pass.
     *
     * ammoPerBox/costPerShot/weightPerShot are cross-checked against the Weapon
     * Charts in the Car Wars Compendium (SJG30-7142), pages 50-51. Where a weapon
     * has multiple ammo types (HEAT/APFSDS/HESH, standard/incendiary, etc.), the
     * base/standard round is used. Grenade Launcher, Mine-Flinger, Micromissile
     * Launcher, and Variable-Fire Rocket Pod are left out: their ammo rules
     * (variable-cost loads, multi-shot pods) are more complex than this app models.
     */
    public static final List<Weapon> ALL = Arrays.asList(
            // Small-Bore Projectile Weapons
            new Weapon("Machine Gun", "MG", "7", "1d", 3, 1000, 150, 1, 20, 25, 2.5),
            new Weapon("Vulcan MG", "VMG", "6", "2d", 3, 2000, 350, 2, 20, 35, 5),
            new Weapon("Flechette Gun", "FG", "6", "1d+1", 2, 700, 100, 1, 20, 10, 2.5),
            new Weapon("Vehicular Shotgun", "VS", "6", "2 hits", 2, 950, 90, 1, 10, 5, 1),
            new Weapon("Gauss Gun", "GG", "6", "3d", 4, 10000, 300, 2, 10, 50, 10),
            new Weapon("Recoilless Rifle", "RR", "7", "2d", 4, 1500, 300, 2, 10, 35, 5),
            new Weapon("Autocannon", "AC", "6", "3d", 4, 6500, 500, 3, 10, 75, 10),

            // Large-Bore Projectile Weapons
            new Weapon("Bomb", "B", "9", "4d", 2, 100, 100, 2, 0, 0, 0),
            new Weapon("Cluster Bomb", "CB", "9", "2d", 2, 200, 150, 2, 0, 0, 0),
            new Weapon("Starshell Launcher", "SL", "-", "-", 2, 500, 100, 1, 5, 50, 5),
            new Weapon("Spike Gun", "SG", "7", "1d", 2, 750, 150, 2, 10, 40, 10),
            new Weapon("Anti-Tank Gun", "ATG", "8", "3d", 6, 2000, 600, 3, 10, 50, 10),
            new Weapon("Oil Gun", "OG", "7/5", "-", 3, 1000, 250, 3, 10, 25, 5),
            new Weapon("Paint Gun", "PG", "7/5", "-", 3, 1000, 250, 3, 10, 25, 5),
            new Weapon("Blast Cannon", "BC", "7", "4d", 5, 4500, 500, 4, 10, 100, 10),
            new Weapon("Tank Gun", "TG", "7", "8d", 10, 10000, 1200, 10, 10, 100, 20),

            // Rockets - most are single-shot rounds where the listed cost already
            // prices one round, so no separate ammo purchase is modeled. Rocket
            // Launcher is magazine-fed per the chart and does have purchasable ammo.
            new Weapon("Mini Rocket", "MNR", "9", "1d-1", 1, 50, 20, 1.0 / 3, 0, 0, 0),
            new Weapon("Light Rocket", "LtR", "9", "1d", 1, 75, 25, 0.5, 0, 0, 0),
            new Weapon("Medium Rocket", "MR", "9", "2d", 2, 140, 50, 1, 0, 0, 0),
            new Weapon("Heavy Rocket", "HR", "9", "3d", 2, 200, 100, 1, 0, 0, 0),
            new Weapon("Anti-Power-Plant Rocket", "APPR", "9", "1d-1*", 3, 500, 40, 1, 0, 0, 0),
            new Weapon("Surface-to-Air Missile", "SAM", "6/11", "4d", 1, 500, 150, 1, 0, 0, 0),
            new Weapon("Radar-Guided Missile", "RGM", "7", "3d", 1, 3000, 100, 1, 0, 0, 0),
            new Weapon("Wire-Guided Missile", "WGM", "6", "3d", 1, 2000, 100, 1, 0, 0, 0),
            new Weapon("Six-Shooter", "-", "9", "1d*", 2, 450, 150, 2, 0, 0, 0),
            new Weapon("Rocket Launcher", "RL", "8", "2d", 2, 1000, 200, 2, 10, 35, 5),

            // Lasers - draw power from the plant, no purchasable ammo.
            new Weapon("Targeting Laser", "TL", "6", "-", 1, 1000, 50, 0, 0, 0, 0),
            new Weapon("Light Laser", "LL", "6", "1d", 2, 3000, 200, 1, 0, 0, 0),
            new Weapon("Medium Laser", "ML", "6", "2d", 2, 5500, 350, 2, 0, 0, 0),
            new Weapon("Laser", "L", "6", "3d", 2, 8000, 500, 2, 0, 0, 0),
            new Weapon("Twin Laser", "TwL", "6", "2d+6", 3, 10000, 750, 2, 0, 0, 0),
            new Weapon("Heavy Laser", "HL", "6", "4d", 2, 12000, 500, 3, 0, 0, 0),
            new Weapon("X-ray Laser", "XL", "7", "4d", 3, 15000, 750, 3, 0, 0, 0),
            new Weapon("Heavy X-ray Laser", "HXL", "7", "5d", 3, 20000, 1500, 3, 0, 0, 0),

            // Flamethrowers
            new Weapon("Light Flamethrower", "LFT", "6", "1d-2", 1, 350, 250, 1, 10, 15, 3),
            new Weapon("Flamethrower", "FT", "6", "1d", 2, 500, 450, 2, 10, 25, 5),
            new Weapon("HD Flamethrower", "HDFT", "6", "2d", 3, 1250, 650, 3, 10, 50, 10)
    );
}
