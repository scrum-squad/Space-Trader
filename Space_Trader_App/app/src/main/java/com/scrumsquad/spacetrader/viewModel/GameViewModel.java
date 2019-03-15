package com.scrumsquad.spacetrader.viewModel;

import android.arch.lifecycle.ViewModel;

import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.Ship;
import com.scrumsquad.spacetrader.model.SolarSystem;

import java.util.ArrayList;

public class GameViewModel extends ViewModel {
    private Game game;
    private Player player = game.getPlayer();

    public ArrayList<SolarSystem> generatePossibleDestinations() {
        Ship ship = player.getShip();
        ArrayList<SolarSystem> destinations = new ArrayList<>();
        int range = ship.getRange();
        ArrayList<SolarSystem> systems = (ArrayList<SolarSystem>) game.getSolarSystems().values();
        for (SolarSystem system: systems) {
            if (distanceCalculation(system) <= range) {
                destinations.add(system);
            }
        }
    }

    private int distanceCalculation(SolarSystem system) {
        system.
    }
}
