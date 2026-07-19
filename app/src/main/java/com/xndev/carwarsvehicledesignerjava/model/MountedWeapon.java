package com.xndev.carwarsvehicledesignerjava.model;

/** A weapon mounted on the vehicle under design, plus how much ammo was bought for it. */
public class MountedWeapon {
    public final Weapon weapon;
    public int ammoRounds;

    public MountedWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.ammoRounds = weapon.ammoPerBox;
    }

    public double ammoCost() {
        return ammoRounds * weapon.costPerShot;
    }
}
