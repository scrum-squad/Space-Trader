package com.scrumsquad.spacetrader.model;

public enum Ships {
    GNAT  ("Gnat");

    private final String name;

    Ships(final String name) {
        this.name = name;
    }

    public String getName() { return name; }
}
