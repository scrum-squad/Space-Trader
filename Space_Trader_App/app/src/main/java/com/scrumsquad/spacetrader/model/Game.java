package com.scrumsquad.spacetrader.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class Game implements Serializable{
    // this is a singleton so here's the only instance
    private static Game game;
    // other variables
    private Player player;
    private Difficulty diff;

    // hashMap has the solar system coordinates (key) and the system itself (value)
    private HashMap<String, SolarSystem> solarSystems;
    private ArrayList<String> coordinatesUsed;

    private Game() {
        Ships s = Ships.GNAT;
        player = new Player("", 0, s, null);
        diff = Difficulty.Normal;
        List<String> solarSystemNames = new ArrayList<>();
        solarSystemNames.add("nme");
        makeSolarSystems(solarSystemNames);
    }

    private Game(Player player1, Difficulty diff1, List<String> solarSystemNames) {
        player = player1;
        diff = diff1;
        makeSolarSystems(solarSystemNames);
    }

    public static void makeGame(Game g) {
        Game.game = g;
    }

    // basically just calls the constructor for the instance of Game
    public static void makeGame(Player player1, Difficulty diff1, List<String> solarSystemNames) {
        Game.game = new Game();
        Game.game.setPlayer(player1);
        Game.game.setDifficulty(diff1);
        Game.game.makeSolarSystems(solarSystemNames);
    }

    private void makeSolarSystems(List<String> solarSystemNames) {
        //make the solar systems here
        //each solar system needs to be at a different location
        //maybe a hashMap with the coordinates as the key and the solarSystem as the value

        // initialize our map and list
        solarSystems = new HashMap<>();
        coordinatesUsed = new ArrayList<>();

        // initialize solar systems with these planet names (1 planet per system)
        int index = 0;
        while (index < solarSystemNames.size()) {
            // makes a random coordinate with x between 0 and 150, y between 0 and 100 (based on M6 file)
            int xCoordinate = (int) (Math.random() * 151);
            int yCoordinate = (int) (Math.random() * 101);
            String coordinates = "(" + xCoordinate + "," + yCoordinate + ")";

            // only proceed if coordinates haven't been used, otherwise index will not get updated so you try again
            if (!coordinatesUsed.contains(coordinates)) {
                coordinatesUsed.add(coordinates);
                solarSystems.put(solarSystemNames.get(index), new SolarSystem(xCoordinate, yCoordinate, solarSystemNames.get(index)));
                index++;
            }
        }

        // assign the first planet created to be our starting planet
        player.setCurrentSystem(solarSystems.get(solarSystemNames.get(0)));
        player.setCurrentPlanet(solarSystems.get(solarSystemNames.get(0)).getPlanets().get(0));
    }

    /**
     * Returns the instance of the Game
     *
     * @return Game
     */
    public static Game getGame() {
        return Game.game;
    }

    public Player getPlayer() {
        return player;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public Planet getCurrentPlanet() {
        return player.getCurrentPlanet();
    }
    public ArrayList<String> getCoordinatesUsed() {
        return coordinatesUsed;
    }

    public HashMap<String, SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    public void setPlayer(Player player1) {
        player = player1;
    }

    public void setCoordinates(ArrayList<String> coordinatesUsed) {
        this.coordinatesUsed = coordinatesUsed;
    }

    public void setCurrentPlanet(Planet planet) {
        player.setCurrentPlanet(planet);
    }

    public void setDifficulty(Difficulty diff) {
        this.diff = diff;
    }

    public void setSolarSystems(HashMap<String, SolarSystem> solarSystems1) {
        solarSystems = solarSystems1;
    }

}
