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

    public double ammoWeight() {
        return ammoRounds * weapon.weightPerShot;
    }

    /**
     * How many magazines (boxes) of ammo are loaded. Ammo is always bought in
     * whole boxes of weapon.ammoPerBox, so this divides evenly.
     */
    public int magazineCount() {
        return weapon.ammoPerBox > 0 ? Math.round((float) ammoRounds / weapon.ammoPerBox) : 0;
    }

    /**
     * Magazines beyond the first. The base load comes with the weapon; each
     * extra magazine adds its own cost/weight/space (see VehicleCalculator).
     */
    public int extraMagazines() {
        int count = magazineCount();
        return count > 1 ? count - 1 : 0;
    }
}
