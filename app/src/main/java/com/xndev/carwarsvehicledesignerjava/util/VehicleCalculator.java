package com.xndev.carwarsvehicledesignerjava.util;

import com.xndev.carwarsvehicledesignerjava.model.BodyType;
import com.xndev.carwarsvehicledesignerjava.model.ChassisType;
import com.xndev.carwarsvehicledesignerjava.model.ElectricPowerPlant;
import com.xndev.carwarsvehicledesignerjava.model.GasEngine;
import com.xndev.carwarsvehicledesignerjava.model.GasTankType;
import com.xndev.carwarsvehicledesignerjava.model.MountedWeapon;
import com.xndev.carwarsvehicledesignerjava.model.SuspensionType;
import com.xndev.carwarsvehicledesignerjava.model.TargetingComputer;
import com.xndev.carwarsvehicledesignerjava.model.TireType;
import com.xndev.carwarsvehicledesignerjava.model.VehicleArmor;

import java.util.List;

public final class VehicleCalculator {

    public static final int DRIVER_WEIGHT = 150;
    public static final int DRIVER_SPACES = 2;
    public static final int BASE_DRIVER_DP = 3;
    public static final int TIRE_COUNT = 4;

    /**
     * Woven plastic-cord body armor: $250, takes 3 hits before it is useless,
     * effectively doubling the wearer's DP from 3 to 6.
     */
    public static final int BODY_ARMOR_COST = 250;
    public static final int BODY_ARMOR_DP_BONUS = 3;

    private VehicleCalculator() {}

    public static class Input {
        public BodyType body;
        public ChassisType chassis;
        public SuspensionType suspension;
        public boolean isElectric;
        public ElectricPowerPlant electricPlant;
        public GasEngine gasEngine;
        public GasTankType gasTankType;
        public double gasGallons;
        public TireType tire;
        public VehicleArmor armor;
        public List<MountedWeapon> mountedWeapons;
        public boolean hasBodyArmor;
        public TargetingComputer targetingComputer = TargetingComputer.NONE;
    }

    public static VehicleStats compute(Input in) {
        double maxLoad = in.body.maxLoad * (1 + in.chassis.maxLoadModifier);

        double bodyPrice = in.body.price * (1 + in.chassis.priceModifier);
        double suspensionCost = in.body.price * in.suspension.costFactor;

        double powerPlantCost;
        double powerPlantWeight;
        int powerPlantSpacesUsed;
        int powerFactors;
        if (in.isElectric) {
            ElectricPowerPlant plant = in.electricPlant;
            powerPlantCost = plant.cost;
            powerPlantWeight = plant.weight;
            powerPlantSpacesUsed = plant.spaces;
            powerFactors = plant.powerFactors;
        } else {
            GasEngine engine = in.gasEngine;
            GasTankType tankType = in.gasTankType;
            powerPlantCost = engine.cost + Math.round(tankType.costPerGallon * in.gasGallons);
            powerPlantWeight = engine.weight + Math.round(tankType.weightPerGallon * in.gasGallons);
            powerPlantSpacesUsed = engine.spaces + GasTankType.spacesFor(in.gasGallons);
            powerFactors = engine.power;
        }

        double tiresCost = in.tire.price * TIRE_COUNT;
        double tiresWeight = in.tire.weight * TIRE_COUNT;

        double armorCost = in.armor.totalPoints() * in.body.armorCostPerPoint;
        double armorWeight = in.armor.totalPoints() * in.body.armorWeightPerPoint;

        double weaponsCost = 0;
        double ammoCost = 0;
        double weaponsWeight = 0;
        double ammoWeight = 0;
        double weaponsSpace = 0;
        for (MountedWeapon mw : in.mountedWeapons) {
            weaponsCost += mw.weapon.cost;
            ammoCost += mw.ammoCost();
            weaponsWeight += mw.weapon.weight;
            ammoWeight += mw.ammoWeight();
            weaponsSpace += mw.weapon.space;
        }

        int bodyArmorCostApplied = in.hasBodyArmor ? BODY_ARMOR_COST : 0;
        int driverDp = BASE_DRIVER_DP + (in.hasBodyArmor ? BODY_ARMOR_DP_BONUS : 0);

        double totalCost = bodyPrice + suspensionCost + powerPlantCost + tiresCost + armorCost
                + weaponsCost + ammoCost + bodyArmorCostApplied + in.targetingComputer.cost;

        double totalWeight = in.body.weight + powerPlantWeight + tiresWeight + armorWeight
                + weaponsWeight + ammoWeight + DRIVER_WEIGHT + in.targetingComputer.weight;

        double spacesUsed = powerPlantSpacesUsed + weaponsSpace + DRIVER_SPACES + in.targetingComputer.space;
        double spacesAvailable = in.body.spaces - spacesUsed;

        int handlingClass = in.suspension.handlingClassFor(in.body.handlingCategory);

        boolean isUnderpowered = powerFactors < totalWeight / 3;
        int acceleration;
        if (isUnderpowered) {
            acceleration = 0;
        } else if (powerFactors < totalWeight / 2) {
            acceleration = 5;
        } else if (powerFactors < totalWeight) {
            acceleration = 10;
        } else {
            acceleration = 15;
        }

        int speedMultiplier = in.isElectric ? 360 : 240;
        double rawTopSpeed = isUnderpowered ? 0.0 : speedMultiplier * powerFactors / (powerFactors + totalWeight);
        double topSpeed = Math.floor(rawTopSpeed / 2.5) * 2.5;

        return new VehicleStats(totalCost, ammoCost, totalWeight, maxLoad, spacesUsed, spacesAvailable,
                handlingClass, powerFactors, acceleration, topSpeed, isUnderpowered,
                DRIVER_WEIGHT, DRIVER_SPACES, driverDp);
    }
}
