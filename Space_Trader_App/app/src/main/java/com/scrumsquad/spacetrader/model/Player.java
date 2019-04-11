package com.scrumsquad.spacetrader.model;

import java.util.List;

public class Player {
    private final String name;
    private int credits;
    private final Ship ship;
    private SolarSystem currentSystem;
    private Planet currentPlanet;
    private List<Skills> skills;

    public Player() {
        name = "";
        credits = 0;
        ship = new Ship(Ships.GNAT);
    }

    public Player(String name, int credits, Ships ship, List<Skills> skill) {
        this.name = name;
        this.credits = credits;
        this.ship = new Ship(ship);
        skills = skill;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Ship getShip() {
        return ship;
    }

    public String getName() {
        return name;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet planet) {
        currentPlanet = planet;
    }

    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    public void setCurrentSystem(SolarSystem currentSystem) {
        this.currentSystem = currentSystem;
    }

    public List<Skills> getSkills() {
        return skills;
    }
}
