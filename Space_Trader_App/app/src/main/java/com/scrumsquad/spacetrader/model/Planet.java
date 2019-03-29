package com.scrumsquad.spacetrader.model;

public class Planet {
    private TechLevel techLevel;
    private Resources resources;
    private String name;

    /**
     * makes a planet with random tech level and resource level
     * @param name name of the planet
     */
    public Planet(String name) {
        this.name = name;
        this.techLevel = TechLevel.getRandomTechLevel();
        this.resources = Resources.getRandomResource();
    }

    /**
     * makes a planet with name, chosen techlevel, and chose resource
     * @param name name of the planet
     * @param techLevel tech level
     * @param resources resource
     */
    public Planet(String name, TechLevel techLevel, Resources resources) {
        this.techLevel = techLevel;
        this.resources = resources;
        this.name = name;
    }

    public Resources getResources() {
        return resources;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public String getName() {
        return name;
    }
}
