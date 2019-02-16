package com.scrumsquad.spacetrader.model;

public enum Difficulty {
    Beginner ("Beginner"),
    Easy ("Easy"),
    Normal ("Norma"),
    Hard ("Hard"),
    Impossible ("Impossible");

    private final String name;

    Difficulty(final String name) {
        this.name = name;
    }

    public String getLevel() { return name; }
}
