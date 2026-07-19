package com.xndev.carwarsvehicledesignerjava.model;

public class VehicleArmor {
    public int front = 0;
    public int back = 0;
    public int left = 0;
    public int right = 0;
    public int top = 0;
    public int underbody = 0;

    public int totalPoints() {
        return front + back + left + right + top + underbody;
    }
}
