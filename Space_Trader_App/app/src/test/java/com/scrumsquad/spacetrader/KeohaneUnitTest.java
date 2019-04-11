package com.scrumsquad.spacetrader;

import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.SolarSystem;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class KeohaneUnitTest{

    @Test(timeout = 200)
    public void testMakePlanets() {
        /*
            I will be testing the makePlanets() method from the SolarSystem class.
            I'll be making a fuzzer in order to cover a variety of possibilities for testing.
            This method doesn't have many edge cases so I'll test that too.
         */

        // a list of 21 possible names
        String[] planetNames = {"Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas", "Brax", "Bretel", "Calondia",
                "Campor", "Capelle", "Carzon", "Castor", "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Janus", "Japori"};

        // this test will iterate 100 times, each time should be unique
        SolarSystem mySolarSystem;
        for (int x = 0; x < 10000; x++) {
            ArrayList<String> chosenNames = new ArrayList<>();

            for (String planetName : planetNames) {
                // 50-50 shot of the name being added
                int shouldAdd = (int) (Math.random() * 2);
                if (shouldAdd == 1) {
                    chosenNames.add(planetName);
                }
            }

            // call the method with this new list (called by constructor)
            mySolarSystem = new SolarSystem(0, 0, chosenNames);

            // get its list of planets
            List<Planet> createdPlanets = mySolarSystem.getPlanets();

            // compare the planets now in the S.S. with the chosen names
            for (int i = 0; i < chosenNames.size(); i++) {
                Assert.assertEquals("planet name either missing or shouldn't have been added", createdPlanets.get(i).getName(), chosenNames.get(i));
            }
        }

        // test one final edge case (this should not create an error)
        ArrayList<String> emptyNameList = new ArrayList<>();
        mySolarSystem = new SolarSystem(0, 0, emptyNameList);
        List<Planet> emptyPlanetList = mySolarSystem.getPlanets();
        Assert.assertEquals("planet list should be empty", 0, emptyPlanetList.size());
    }

}