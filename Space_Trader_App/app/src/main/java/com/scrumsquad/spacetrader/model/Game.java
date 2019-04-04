package com.scrumsquad.spacetrader.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game implements Serializable{
    // this is a singleton so here's the only instance
    private static Game game;
    // other variables
    private Player player;
    private Difficulty diff;

    // hashmap has the solar system coordinates (key) and the system itself (value)
    private HashMap<String, SolarSystem> solarSystems;
    private ArrayList<String> coordinatesUsed;


    private Game(Player player1, Difficulty diff1, List<String> solarSystemNames) {
        this.player = player1;
        this.diff = diff1;
        this.makeSolarSystems(solarSystemNames);
    }

    // basically just calls the constructor for the instance of Game
    public static void makeGame(Player player1, Difficulty diff1, List<String> solarSystemNames) {
        game = new Game(player1, diff1, solarSystemNames);
    }

    public void setPlayer(Player player1) {
        this.player = player1;
    }

    private void makeSolarSystems(List<String> solarSystemNames) {
        //make the solar systems here
        //each solar system needs to be at a different location
        //maybe a hashmap with the coordinates as the key and the solarSystem as the value
        System.out.println(solarSystemNames);

        // initialize our map and list
        solarSystems = new HashMap<>();
        coordinatesUsed = new ArrayList<>();

        // initialize solar systems with these planet names (1 planet per system)
        int index = 0;
        while (index < solarSystemNames.size()) {
            // makes a random coordinate with x between 0 and 150, y between 0 and 100 (based on M6 file)
            int xCoord = (int) (Math.random() * 151);
            int yCoord = (int) (Math.random() * 101);
            String coordinates = "(" + xCoord + "," + yCoord + ")";

            // only proceed if coordinates haven't been used, otherwise index will not get updated so you try again
            if (!coordinatesUsed.contains(coordinates)) {
                coordinatesUsed.add(coordinates);
                solarSystems.put(solarSystemNames.get(index), new SolarSystem(xCoord, yCoord, solarSystemNames.get(index)));
                index++;
            }
        }

        System.out.println(solarSystems);
        System.out.println(solarSystems.get(solarSystemNames.get(0)).getPlanets());

        // assign the first planet created to be our starting planet
        player.setCurrentSystem(solarSystems.get(solarSystemNames.get(0)));
        player.setCurrentPlanet(solarSystems.get(solarSystemNames.get(0)).getPlanets().get(0));

        /*
            FOR NOW WE WILL BE JUST PRINTING OUT THE RESULTS AT THE END OF THIS METHOD
         */
        System.out.println("========================");
        for (int i = 0; i < solarSystemNames.size(); i++) {
            SolarSystem currSystem = solarSystems.get(solarSystemNames.get(i));
            List<Planet> currPlanets = currSystem.getPlanets();
            // for now the only name that matters is the solar system name since each system only has one planet
            System.out.println("System Name: " + currSystem.getName());
            System.out.println("Coordinates: " + currSystem.getCoordinates());
            for (int x = 0; x < currPlanets.size(); x++) {
                System.out.println("Resources: " + currPlanets.get(x).getResources());
                System.out.println("Tech Level: " + currPlanets.get(x).getTechLevel());
            }
            System.out.println("========================");
        }
    }

    /**
     * Returns the instance of the Game
     *
     * @return Game
     */
    public static Game getGame() {
        return game;
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
}
