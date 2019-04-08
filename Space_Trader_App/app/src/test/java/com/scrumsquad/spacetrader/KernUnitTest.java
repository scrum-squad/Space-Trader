package com.scrumsquad.spacetrader;

import com.scrumsquad.spacetrader.model.Difficulty;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.SolarSystem;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class KernUnitTest extends junit.framework.TestCase {
    private HashMap<String, SolarSystem> solarSystems;
    private ArrayList<String> coordinatesUsed = new ArrayList<>();
    private Game game;
    private Player dummyPlayer = new Player();
    private Difficulty diff = Difficulty.Easy;

    /**
     * Testing the makeSolarSystems method
     */
    @Test(timeout = 200)
    public void testMakeSolarSystems() {
        //list of possible Solar System names
        String[] solarSystemNames = {"Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee",
                "Baratas", "Brax", "Bretel", "Calondia", "Campor", "Capelle", "Carzon", "Castor",
                "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Davlos", "Deneb"};

        ArrayList<String> chosenSystems = new ArrayList<>();
        for (int i = 0; i < solarSystemNames.length; i++) {
            int shouldAdd = (int) (Math.random() * 2);
            if (shouldAdd == 1) {
                chosenSystems.add(solarSystemNames[i]);
            }
        }

        Game.makeGame(dummyPlayer, diff, chosenSystems);
        game = Game.getGame();
        HashMap<String, SolarSystem> generatedSystemsHashMap = game.getSolarSystems();
        Collection<SolarSystem> systemsGenerated = generatedSystemsHashMap.values();
        ArrayList<String> systemNames = new ArrayList<>();
        ArrayList<SolarSystem> solarSystemList = new ArrayList<>();
        for (SolarSystem system : systemsGenerated) {
            systemNames.add(system.getName());
            solarSystemList.add(system);
        }

        for (int i = 0; i < chosenSystems.size(); i++) {
            assertTrue("Solar System has too many or too few planets",
                    systemNames.contains(chosenSystems.get(i)));
            assertTrue("System coordinates have already been used",
                    !(coordinatesUsed.contains(solarSystemList.get(i).getCoordinates())));
            coordinatesUsed.add(solarSystemList.get(i).getCoordinates());
        }

        /**
         * Empty solar system list edge case
         */
        ArrayList<String> emptySystems = new ArrayList<>();
        assertTrue("Cannot generate a game with no systems",
                emptySystems.size() == 0);
    }
}