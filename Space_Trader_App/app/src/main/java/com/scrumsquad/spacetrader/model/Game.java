package com.scrumsquad.spacetrader.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Player player;
    private Difficulty diff;
    //private ArrayList<SolarSystem> solarSystems;
    //private HashMap<Integer, Integer> solarSystems;

    public Game(Player player1, Difficulty diff1, String[] solarSystemNames) {
        this.player = player1;
        this.diff = diff1;
        makeSolarSystems(solarSystemNames);
    }

    public void setPlayer(Player player1) {
        this.player = player1;
    }

    private void makeSolarSystems(String[] solarSystemNames) {
        //make the solar systems here
        //each solar system needs to be at a different location
        //maybe a hashmap with the coordinates as the key and the solarSystem as the value
    }
}
