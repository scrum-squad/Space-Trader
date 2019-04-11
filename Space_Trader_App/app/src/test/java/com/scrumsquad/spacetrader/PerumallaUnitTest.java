package com.scrumsquad.spacetrader;

import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.model.TechLevel;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PerumallaUnitTest {
    /*
        testing the validItem method in the marketGood enum
     */
    @Test
    public void testValidItems() {
        List<MarketGoodItem> list = new ArrayList<>();

        list.add(MarketGoodItem.FURS);
        Assert.assertEquals("Pre-Agricultural Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.PRE_AGRICULTURE));

        list.add(MarketGoodItem.FOOD);
        Assert.assertEquals("Agricultural Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.AGRICULTURE));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        Assert.assertEquals("Medieval Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.MEDIEVAL));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        list.add(MarketGoodItem.ORE);
        Assert.assertEquals("Renaissance Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.RENAISSANCE));
        Assert.assertEquals("Early-Industrial Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.EARLY_INDUSTRIAL));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        list.add(MarketGoodItem.ORE);
        list.add(MarketGoodItem.FIREARMS);
        list.add(MarketGoodItem.MACHINES);
        list.add(MarketGoodItem.NARCOTICS);
        Assert.assertEquals("Industrial Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.INDUSTRIAL));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        list.add(MarketGoodItem.ORE);
        list.add(MarketGoodItem.GAMES);
        list.add(MarketGoodItem.FIREARMS);
        list.add(MarketGoodItem.MEDICINE);
        list.add(MarketGoodItem.MACHINES);
        list.add(MarketGoodItem.NARCOTICS);
        Assert.assertEquals("Post-Industrial Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.POST_INDUSTRIAL));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        list.add(MarketGoodItem.ORE);
        list.add(MarketGoodItem.GAMES);
        list.add(MarketGoodItem.FIREARMS);
        list.add(MarketGoodItem.MEDICINE);
        list.add(MarketGoodItem.MACHINES);
        list.add(MarketGoodItem.NARCOTICS);
        list.add(MarketGoodItem.ROBOTS);
        Assert.assertEquals("Hi-Tech level has invalid items", list, MarketGoodItem.validItems(TechLevel.HI_TECH));
    }
}
