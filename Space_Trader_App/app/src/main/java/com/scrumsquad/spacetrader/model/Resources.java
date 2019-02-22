package com.scrumsquad.spacetrader.model;

import java.util.Random;

public enum Resources {
    NOSPECIALRESOURCES,
    MINERALRICH,
    MINERALPOOR,
    DESERT,
    LOTSOFWATER,
    RICHSOIL,
    POORSOIL,
    RICHFAUNA,
    LIFELESS,
    WEIRDMUSHROOMS,
    LOTSOFHERBS,
    ARTISTIC,
    WARLIKE;
    //These enums do not need a get name because they are supposed to be all caps

    /**
     * picks a random Resource
     * @return selected Resource
     */
    public static Resources getRandomResource() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
