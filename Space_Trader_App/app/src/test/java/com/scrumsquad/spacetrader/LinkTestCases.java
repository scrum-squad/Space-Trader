package com.scrumsquad.spacetrader;

import android.widget.ArrayAdapter;

import com.scrumsquad.spacetrader.model.Difficulty;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.Ship;
import com.scrumsquad.spacetrader.model.Ships;
import com.scrumsquad.spacetrader.model.Skills;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LinkTestCases extends junit.framework.TestCase {
    // The following test method will check to make sure all ships in the Ship enum are valid

    private final int STARTING_CREDITS = 1000;
    private final Ships STARTING_SHIP = Ships.GNAT;
    private final int SKILL_POINTS = 16;
    @Test
    public void testValidItems() {
        ArrayList<Integer> tester = new ArrayList<>();
        for (Ships ship : Ships.values()) {
            // This method will check each ship in the enum
            tester.add(ship.getFuelCap());
            tester.add(ship.getCargoCap());
            //Above should have added characteristics
            ArrayList<Integer> method = Ship.getValidShip(ship);
            // Checks the get valid ship method
            String message = String.format("The Ship, %s is an invalid ship", ship.name());
            assertEquals(message, tester, method);
            tester.clear();
        }
        // Test the player instantiated with game
        // Test should be GNAT

        List<Skills> playerSkills = new ArrayList(Arrays.asList(Skills.values()));
        String playerName = "Test";
        Player player1 = new Player(playerName, STARTING_CREDITS, STARTING_SHIP, playerSkills);
        String message1 = "The Player is not being generated correctly";
        assertEquals(message1, Ship.getValidShip(Ships.GNAT), Ship.getValidShip(STARTING_SHIP));
        // If the above is valid the player ship is correct
    }


}
