package com.scrumsquad.spacetrader.model;

public enum Skills {
    Pilot("Pilot"),
    Fighter("Fighter"),
    Trader("Trader"),
    Engineer("Engineer");

    private int level;

    private final String name;

    Skills(String name) {
        this.level = 0;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void updateLevel(int newLevel) {
        level = newLevel;
    }

    public String toString() {
        return  this.name;
    }
    
}
