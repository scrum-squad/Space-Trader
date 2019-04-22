package com.scrumsquad.spacetrader.model;

import java.util.Random;

public class Pirate {

    int power;

    public Pirate() {
        Random random = new Random();
        this.power = random.nextInt(100);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
