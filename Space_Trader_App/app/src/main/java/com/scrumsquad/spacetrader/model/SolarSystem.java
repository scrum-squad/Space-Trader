package com.scrumsquad.spacetrader.model;

public class SolarSystem {
    private int xCord;
    private int yCord;
    private Planet[] planets;
    private String name;

    /**
     * used for only one planet
     * @param xCord x coordinate
     * @param yCord y coordinate
     * @param planetName planet name
     */
    public SolarSystem(int xCord, int yCord, String planetName) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.name = planetName;
        String[] planetNames = {planetName};
        makePlanets(planetNames);
    }

    /**
     * used for making multiple planets
     * @param xCord x coordinate
     * @param yCord y coordinate
     * @param planetNames planet names
     */
    public SolarSystem(int xCord, int yCord, String[] planetNames) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.name = planetNames[0];
        makePlanets(planetNames);
    }

    /**
     * makes a new random planet for each planet name
     * @param planetNames the names to be made into planets
     */
    private void makePlanets(String[] planetNames) {
        planets = new Planet[planetNames.length];
        for (int i = 0; i < planets.length; i++) {
            planets[i] = new Planet(planetNames[i]);
        }
    }

    public Planet[] getPlanets() {
        return planets;
    }

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return "(" + xCord + "," + yCord + ")";
    }

    public int getxCord() {
        return this.xCord;
    }

    public int getyCord() {
        return this.yCord;
    }
}
