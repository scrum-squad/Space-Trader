package com.scrumsquad.spacetrader.model;

public class Player {
    private String name;
    private int credits;
    private Ships ship;
    private int skillPoints;
    private Skills[] playerSkills;

    public Player(String name, int credits, Ships ship) {
        this.name = name;
        this.credits = credits;
        this.ship = ship;
        playerSkills = Skills.values();
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
