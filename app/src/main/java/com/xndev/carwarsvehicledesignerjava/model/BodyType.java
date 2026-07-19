package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class BodyType {
    public final String name;
    public final int price;
    public final int weight;
    public final int maxLoad;
    public final int spaces;
    public final int cargoSpaces;
    public final int armorCostPerPoint;
    public final int armorWeightPerPoint;
    public final HandlingCategory handlingCategory;

    public BodyType(String name, int price, int weight, int maxLoad, int spaces, int cargoSpaces,
                     int armorCostPerPoint, int armorWeightPerPoint, HandlingCategory handlingCategory) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.maxLoad = maxLoad;
        this.spaces = spaces;
        this.cargoSpaces = cargoSpaces;
        this.armorCostPerPoint = armorCostPerPoint;
        this.armorWeightPerPoint = armorWeightPerPoint;
        this.handlingCategory = handlingCategory;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final List<BodyType> ALL = Arrays.asList(
            new BodyType("Subcompact", 300, 1000, 2300, 7, 0, 11, 5, HandlingCategory.SUBCOMPACT),
            new BodyType("Compact", 400, 1300, 3700, 10, 0, 13, 6, HandlingCategory.REGULAR),
            new BodyType("Mid-sized", 600, 1600, 4800, 13, 0, 16, 8, HandlingCategory.REGULAR),
            new BodyType("Sedan", 700, 1700, 5100, 16, 0, 18, 9, HandlingCategory.REGULAR),
            new BodyType("Luxury", 800, 1800, 5500, 19, 0, 20, 10, HandlingCategory.REGULAR),
            new BodyType("Station Wagon", 800, 1800, 5500, 14, 7, 20, 10, HandlingCategory.REGULAR),
            new BodyType("Pickup", 900, 2100, 6500, 13, 11, 22, 11, HandlingCategory.VAN),
            new BodyType("Camper", 1400, 2300, 6500, 17, 7, 30, 14, HandlingCategory.VAN),
            new BodyType("Van", 1000, 2000, 6000, 24, 6, 30, 14, HandlingCategory.VAN)
    );
}
