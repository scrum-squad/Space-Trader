package com.scrumsquad.spacetrader.model;

@SuppressWarnings("unused")
public enum Difficulty {
    Beginner ("Beginner"),
    Easy ("Easy"),
    Normal ("Normal"),
    Hard ("Hard"),
    Impossible ("Impossible");

    private final String name;

    Difficulty(String name) {
        this.name = name;
    }

    public String getLevel() { return name; }
}
