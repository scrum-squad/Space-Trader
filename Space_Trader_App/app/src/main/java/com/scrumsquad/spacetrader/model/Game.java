package com.scrumsquad.spacetrader.model;

public class Game {
    private Player player;
    private Difficulty diff;

    public Game(Player player1, Difficulty diff1) {
        this.player = player1;
        this.diff = diff1;
    }

    public void setPlayer(Player player1) {
        this.player = player1;
    }
}
