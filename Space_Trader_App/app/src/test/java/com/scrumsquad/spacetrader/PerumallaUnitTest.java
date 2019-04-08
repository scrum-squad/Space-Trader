package com.scrumsquad.spacetrader;

import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.model.TechLevel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PerumallaUnitTest extends junit.framework.TestCase{
    /*
        testing the validitem method in the marketgood enum
     */
    @Test
    public void testValidItems() {
        System.out.println(MarketGoodItem.validItems(TechLevel.PRE_AGRICULTURE));
        System.out.println(MarketGoodItem.validItems(TechLevel.AGRICULTURE));
        System.out.println(MarketGoodItem.validItems(TechLevel.MEDIEVAL));
        System.out.println(MarketGoodItem.validItems(TechLevel.RENAISSANCE));
        System.out.println(MarketGoodItem.validItems(TechLevel.EARLY_INDUSTRIAL));
        System.out.println(MarketGoodItem.validItems(TechLevel.INDUSTRIAL));
        System.out.println(MarketGoodItem.validItems(TechLevel.POST_INDUSTRIAL));
        System.out.println(MarketGoodItem.validItems(TechLevel.HI_TECH));

        List<MarketGoodItem> list = new ArrayList<MarketGoodItem>();

        list.add(MarketGoodItem.FURS);
        assertEquals(list, MarketGoodItem.validItems(TechLevel.PRE_AGRICULTURE));

        list.add(MarketGoodItem.FOOD);
        assertEquals(list, MarketGoodItem.validItems(TechLevel.AGRICULTURE));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        assertEquals(list, MarketGoodItem.validItems(TechLevel.MEDIEVAL));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        list.add(MarketGoodItem.ORE);
        assertEquals(list, MarketGoodItem.validItems(TechLevel.RENAISSANCE));
        assertEquals(list, MarketGoodItem.validItems(TechLevel.EARLY_INDUSTRIAL));

        list.clear();
        list.add(MarketGoodItem.WATER);
        list.add(MarketGoodItem.FURS);
        list.add(MarketGoodItem.FOOD);
        list.add(MarketGoodItem.ORE);
        list.add(MarketGoodItem.FIREARMS);
        list.add(MarketGoodItem.MACHINES);
        list.add(MarketGoodItem.NARCOTICS);
        assertEquals(list, MarketGoodItem.validItems(TechLevel.INDUSTRIAL));

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
        assertEquals(list, MarketGoodItem.validItems(TechLevel.POST_INDUSTRIAL));

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
        assertEquals(list, MarketGoodItem.validItems(TechLevel.HI_TECH));
    }
}
