package com.scrumsquad.spacetrader.model;

public class Player {
    private String name;
    private int credits;
    private Ships ship;
    private Skills[] skills;

    public Player(String name, int credits, Ships ship, Skills[] skill) {
        this.name = name;
        this.credits = credits;
        this.ship = ship;
        this.skills = skill;
    }

    public int getCredits() {
        return credits;
    }

    public Ships getShip() {
        return ship;
    }

    public String getName() {
        return name;
    }
}
