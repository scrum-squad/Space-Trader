package com.scrumsquad.spacetrader.model;

public enum Weapon {
    WATERGUN(35, 15, 25),
    QUARTZGUN(50, 30, 15),
    TOPAZGUN(60, 50, 12),
    DIAMONDGUN(78, 65, 6);

    int cost;
    int power;
    int ammo;

    Weapon(int cost, int power, int ammo) {
        this.cost = cost;
        this.power = power;
        this.ammo = ammo;
    }

    public int getCost() {
        return cost;
    }

    public int getPower() {
        return power;
    }

    public int getAmmo() {
        return ammo;
    }
}
