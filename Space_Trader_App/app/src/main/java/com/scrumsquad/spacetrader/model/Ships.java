package com.scrumsquad.spacetrader.model;


// This should be treated essentially as a resource for the ship class
public enum Ships {
    GNAT  (15, 14, 1, 0, 1, 0);


    //These allow us to build the ship.
    // They should stay inside of this ships class
    // Only use them to instantiate
    private int cargoCap;
    private int hullStrength;
    private int weaponCap;
    private int shieldCap;
    private int gadgetCap;
    private int crewCap;
    private int range;



    Ships(int cargoCap, int range, int weaponCap, int shieldCap, int gadgetCap, int crewCap) {
        this.cargoCap = cargoCap;
        this.range = range;
        this.weaponCap = weaponCap;
        this.shieldCap = shieldCap;
        this.gadgetCap = gadgetCap;
        this.crewCap = crewCap;

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

    public int getRange() {return this.range;}






}
