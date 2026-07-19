package com.xndev.carwarsvehicledesignerjava.model;

import java.util.Arrays;
import java.util.List;

public class SuspensionType {
    public final String name;
    /** Fraction of body price (e.g. 1.50 for Heavy = 150% of body cost). */
    public final double costFactor;
    public final int hcRegular;
    public final int hcVan;
    public final int hcSubcompact;

    public SuspensionType(String name, double costFactor, int hcRegular, int hcVan, int hcSubcompact) {
        this.name = name;
        this.costFactor = costFactor;
        this.hcRegular = hcRegular;
        this.hcVan = hcVan;
        this.hcSubcompact = hcSubcompact;
    }

    public int handlingClassFor(HandlingCategory category) {
        switch (category) {
            case SUBCOMPACT:
                return hcSubcompact;
            case VAN:
                return hcVan;
            case REGULAR:
            default:
                return hcRegular;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static final List<SuspensionType> ALL = Arrays.asList(
            new SuspensionType("Light", 0, 1, 0, 2),
            new SuspensionType("Improved", 1.00, 2, 1, 3),
            new SuspensionType("Heavy", 1.50, 3, 2, 4),
            new SuspensionType("Off-road", 5.00, 2, 1, 3)
    );
}
