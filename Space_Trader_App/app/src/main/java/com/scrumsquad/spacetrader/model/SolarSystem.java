package com.scrumsquad.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    private int xCord;
    private int yCord;
    private List<Planet> planets;
    private String name;

    public SolarSystem() {

    }

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
        List<String> planetNames = new ArrayList<>();
        planetNames.add(planetName);
        makePlanets(planetNames);
    }

    /**
     * used for making multiple planets
     * @param xCord x coordinate
     * @param yCord y coordinate
     * @param planetNames planet names
     */
    public SolarSystem(int xCord, int yCord, List<String> planetNames) {
        if (planetNames.isEmpty()) {
            this.name = planetNames.get(0);
        } else {
            this.name = "Empty";
        }
        this.xCord = xCord;
        this.yCord = yCord;
        makePlanets(planetNames);
    }

    /**
     * makes a new random planet for each planet name
     * @param planetNames the names to be made into planets
     */
    private void makePlanets(List<String> planetNames) {
        planets = new ArrayList<>(planetNames.size());
        for (int i = 0; i < planetNames.size(); i++) {
            planets.add(new Planet(planetNames.get(i)));
        }
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public String getName() {
        return name;
    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public String getCoordinates() {
        return "(" + xCord + "," + yCord + ")";
    }


    public String toString() {
        return getName() + " " + getCoordinates();
    }
}
