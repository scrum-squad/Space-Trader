package com.scrumsquad.spacetrader.viewModel;

import android.arch.lifecycle.ViewModel;

import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Pirate;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.SolarSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameViewModel extends ViewModel {

    private final Player player = Game.getGame().getPlayer();
    private int totalFuel = player.getShip().getCurrentFuel();
    private int MAX_FUEL = player.getShip().getMAX_FUEL();
    private SolarSystem currentSystem = player.getCurrentSystem();

    // Declares the travel units available per a single unit of fuel
    private final int distPerFuel = 10;

    private int distanceTo(SolarSystem target) {
        double dist = Math.sqrt(Math.pow(target.getxCord() + currentSystem.getxCord(), 2) +
                Math.pow(target.getyCord() + currentSystem.getyCord(), 2));

        return (int) Math.ceil(dist);
    }

    public List<SolarSystem> getPossibleDestinations() {
        Iterable<SolarSystem> systems = new ArrayList<>(Game.getGame().getSolarSystems().values());
        List<SolarSystem> destinations = new ArrayList<>();

        for (SolarSystem system : systems) {
            if (!currentSystem.equals(system)) {
                int dist = distanceTo(system);
                int fuelUsed = dist / distPerFuel;

                if ((totalFuel - fuelUsed) >= 0) {
                    destinations.add(system);
                }
            }
        }

        return destinations;
    }

    public void travelToDestination(SolarSystem destination) {
        // Calculate fuel used
        int dist = distanceTo(destination);
        int fuelUsed = dist / distPerFuel;


        // Update current system and planet
        // TODO: Select from varying planets, current selects default at index = 0
        player.setCurrentSystem(destination);
        player.setCurrentPlanet(destination.getPlanets().get(0));


        // Update viewModel
        player.getShip().subtractUsedFuel(fuelUsed);
        totalFuel = player.getShip().getCurrentFuel();
        currentSystem = player.getCurrentSystem();
        MAX_FUEL = player.getShip().getMAX_FUEL();

    }

    public SolarSystem getCurrentSystem() {
        return currentSystem;
    }

    public int getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(int totalFuel) {
        this.totalFuel = totalFuel;
    }

    public int getMAX_FUEL() {
        return MAX_FUEL;
    }

    public Player getPlayer() {
        return player;
    }

}
