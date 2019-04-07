package com.scrumsquad.spacetrader;

import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.model.Ship;
import com.scrumsquad.spacetrader.model.Ships;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MattUnitTest extends junit.framework.TestCase {
    @Test
    public void testCanAdd() {
        //creates a test ship of the first ship valued in ships to ensure stability if order changes
        Ship testShip = new Ship(Ships.values()[0]);
        int cargoCap = Ships.values()[0].getCargoCap();
        MarketGoodItem[] items = MarketGoodItem.values();

        //tests whether items say they can be added throughout the adding process with random items
        for (int i = 0; i < cargoCap; i++) {
            int itemSelector = (int) Math.random()*items.length;
            assertTrue("Can add returned false but should be true", testShip.canAdd());
            testShip.addItem(items[itemSelector]);
        }

        //tests to see that items cannot be added once the inventory is full
        assertFalse("Can add returned true but should be false", testShip.canAdd());

    }

    public void testAdd() {
        //creates a test ship of the first ship valued in ships to ensure stability if order changes
        Ship testShip = new Ship(Ships.values()[0]);
        int cargoCap = Ships.values()[0].getCargoCap();
        MarketGoodItem[] items = MarketGoodItem.values();
        List<MarketGoodItem> inventory = new ArrayList<>();

        //tests to see if every item is added and done so in the correct order
        for (int i = 0; i < cargoCap; i++) {
            int itemSelector = (int) Math.random()*items.length;
            testShip.addItem(items[itemSelector]);
            inventory.add(items[itemSelector]);
            assertEquals("Item was not added correctly", inventory, testShip.getInventory());
        }
    }
}