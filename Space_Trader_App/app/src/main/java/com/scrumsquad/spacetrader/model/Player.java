package com.scrumsquad.spacetrader.model;

import java.util.List;

public class Player {
    private String name;
    private int credits;
    private Ship ship;
    private List<Skills> skills;
    private SolarSystem currentSystem;
    private Planet currentPlanet;

    public Player(String name, int credits, Ships ship, List<Skills> skill) {
        this.name = name;
        this.credits = credits;
        this.ship = new Ship(ship);
        this.skills = skill;
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
}
