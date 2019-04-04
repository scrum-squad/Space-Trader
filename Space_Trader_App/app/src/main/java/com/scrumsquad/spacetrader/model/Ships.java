package com.scrumsquad.spacetrader.model;


// This should be treated essentially as a resource for the ship class
public enum Ships {
    GNAT  (15, 14);


    //These allow us to build the ship.
    // They should stay inside of this ships class
    // Only use them to instantiate
    private int cargoCap;
    private int hullStrength;
    private int weaponCap;
    private int shieldCap;
    private int gadgetCap;
    private int crewCap;
    private int fuelCap;

    Ships() {
        this.cargoCap = 0;
        this.fuelCap = 0;
    }

    Ships(int cargoCap, int fuelCap) {
        this.cargoCap = cargoCap;
        this.fuelCap = fuelCap;
    }

    //Rewritten name method
    public String toString() {
        String name = this.name();
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

}
