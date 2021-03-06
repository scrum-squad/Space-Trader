package com.scrumsquad.spacetrader.model;

public class Planet {
    private final TechLevel techLevel;
    private final Resources resources;
    private final String name;

    public Planet() {
        name = "";
        techLevel = TechLevel.getRandomTechLevel();
        resources = Resources.getRandomResource();
    }

    /**
     * makes a planet with random tech level and resource level
     * @param name name of the planet
     */
    public Planet(String name) {
        this.name = name;
        techLevel = TechLevel.getRandomTechLevel();
        resources = Resources.getRandomResource();
    }

    /**
     * makes a planet with name, chosen techLevel, and chose resource
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
