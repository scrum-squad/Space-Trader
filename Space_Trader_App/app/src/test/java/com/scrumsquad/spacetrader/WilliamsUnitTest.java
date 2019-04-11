package com.scrumsquad.spacetrader;

import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.model.Ship;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WilliamsUnitTest {

    @Test
    public void testRemoveItem() {
        Ship testShip = new Ship();
        List<MarketGoodItem> sampleInventory = new ArrayList<>();
        sampleInventory.add(MarketGoodItem.WATER);
        sampleInventory.add(MarketGoodItem.MEDICINE);
        sampleInventory.add(MarketGoodItem.MACHINES);
        sampleInventory.add(MarketGoodItem.NARCOTICS);

        for (MarketGoodItem item : sampleInventory) {
            testShip.addItem(item);
        }

        // Method returns null if given item that is not in Ship's inventory
        Assert.assertNull("Ship's removeItem does not return null if item"
                + " is not within Ship's inventory", testShip.removeItem(MarketGoodItem.FIREARMS));

        // Method returns instance of MarketItemGood when item is found in inventory
        Assert.assertEquals("Ship's removeItem does not return instance of item removed",
                MarketGoodItem.MEDICINE, testShip.removeItem(MarketGoodItem.MEDICINE));

        // After removing, instance in inventory is nullified
        Assert.assertFalse("Ship's inventory does not properly remove item after removeItem()",
                testShip.getInventory().contains(MarketGoodItem.MEDICINE));
    }
}
