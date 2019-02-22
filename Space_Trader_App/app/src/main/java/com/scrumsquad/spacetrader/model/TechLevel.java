package com.scrumsquad.spacetrader.model;

import java.util.Random;

public enum TechLevel {
    PRE_AGRICULTURE ("Pre-Agriculture"),
    AGRICULTURE ("Agriculture"),
    MEDIEVAL ("Medieval"),
    RENAISSANCE ("Renaissance"),
    EARLY_INDUSTRIAL ("Early-Industrial"),
    INDUSTRIAL ("Industrial"),
    POST_INDUSTRIAL ("Post-Industrial"),
    HI_TECH ("Hi-Tech");

    private final String formattedName;

    TechLevel(final String name) {
        this.formattedName = name;
    }

    public String getLevelName() { return formattedName; }

    /**
     * picks a random TechLevel
     * @return selected TechLevel
     */
    public static TechLevel getRandomTechLevel() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

}
