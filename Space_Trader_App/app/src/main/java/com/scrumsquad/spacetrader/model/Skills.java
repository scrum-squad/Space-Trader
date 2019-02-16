package com.scrumsquad.spacetrader.model;

public enum Skills {
    Pilot,
    Fighter,
    Trader,
    Engineer;

    private int level;

    Skills() {
        level = 0;
    }

    public int getLevel() {
        return level;
    }

    public void updateLevel(int newLevel) {
        level = newLevel;
    }
    
}
