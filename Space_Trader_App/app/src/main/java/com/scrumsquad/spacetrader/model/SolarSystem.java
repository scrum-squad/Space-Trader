package com.scrumsquad.spacetrader.model;

public class SolarSystem {
    private int xCord;
    private int yCord;
    private Planet[] planets;

    /**
     * used for only one planet
     * @param xCord x coordinate
     * @param yCord y coordinate
     * @param planetName planet name
     */
    public SolarSystem(int xCord, int yCord, String planetName) {
        this.xCord = xCord;
        this.yCord = yCord;
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
}
