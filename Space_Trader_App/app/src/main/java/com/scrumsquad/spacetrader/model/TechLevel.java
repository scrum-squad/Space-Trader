package com.scrumsquad.spacetrader.model;

import java.util.Random;

public enum TechLevel {
    PRE_AGRICULTURE ("Pre-Agriculture", 0),
    AGRICULTURE ("Agriculture", 1),
    MEDIEVAL ("Medieval", 2),
    RENAISSANCE ("Renaissance", 3),
    EARLY_INDUSTRIAL ("Early-Industrial", 4),
    INDUSTRIAL ("Industrial", 5),
    POST_INDUSTRIAL ("Post-Industrial", 6),
    HI_TECH ("Hi-Tech", 7);

    private final String formattedName;

    private final int level;

    TechLevel(String name, int level) {
        formattedName = name;
        this.level = level;
    }

    @SuppressWarnings("unused")
    public String getLevelName() { return formattedName; }

    /**
     * picks a random TechLevel
     * @return selected TechLevel
     */
    public static TechLevel getRandomTechLevel() {
        Random random = new Random();
        return TechLevel.values()[random.nextInt(TechLevel.values().length)];
    }

    /**
     * get level
     *
     * @return tech level
     */
    public int getLevel() {
        return level;
    }

}
