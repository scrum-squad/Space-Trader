package com.scrumsquad.spacetrader.model;


import java.security.PrivilegedAction;

// This should be treated essentially as a resource for the ship class
@SuppressWarnings("SameParameterValue")
public enum Ships {

    GNAT  (15, 14, Weapon.WATERGUN),
    FLEA (8, 20, Weapon.WATERGUN),
    FIREFLY (20, 17, Weapon.WATERGUN),
    MOSQUITO (15, 13, Weapon.WATERGUN),
    BUMBLEBEE(20,15, Weapon.WATERGUN);

    //These allow us to build the ship.
    // They should stay inside of this ships class
    // Only use them to instantiate
    private final int cargoCap;
    private int hullStrength;
    private int weaponCap;
    private int shieldCap;
    private int gadgetCap;
    private int crewCap;
    private final int fuelCap;
    private Weapon weapon;

    Ships() {
        cargoCap = 0;
        fuelCap = 0;
    }


    Ships(int cargoCap, int fuelCap, Weapon weapon) {
        this.cargoCap = cargoCap;
        this.fuelCap = fuelCap;
        this.weapon = weapon;
    }

    //Rewritten name method
    public String toString() {
        String name = name();
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }

    // These will be getters for each characteristic
    public int getCargoCap() {
        return cargoCap;
    }

    // For now, I am assuming there is a 1:1 ratio of fuel:distance (in parsecs)
    // For example, 1 gallon of fuel allows for 1 parsec of travel
    public int getFuelCap() {
        return fuelCap;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
