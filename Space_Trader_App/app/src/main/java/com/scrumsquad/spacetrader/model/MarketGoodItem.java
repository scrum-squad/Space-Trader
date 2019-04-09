package com.scrumsquad.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

public enum MarketGoodItem {
    WATER(0, 0, 2, 30, 3, 4, "DROUGHT",	"LOTSOFWATER", "DESERT", 30, 50),
    FURS(0, 0, 0, 250, 10, 10, "COLD",	"RICHFAUNA", "LIFELESS", 230, 280),
    FOOD(1, 0, 1, 100, 5, 5, "CROPFAIL", "RICHSOIL", "POORSOIL", 90, 160),
    ORE(2, 2, 3, 350, 20, 10, "WAR", "MINERALRICH", "MINERALPOOR", 350, 420),
    GAMES(3, 1, 6, 250, -10, 5, "BOREDOM", "ARTISTIC", "never", 160, 270),
    FIREARMS(3, 1, 5, 1250, -75, 100, "WAR", "WARLIKE", "never", 600, 1100),
    MEDICINE(4, 1, 6, 650, -20, 10, "PLAGUE", "LOTSOFHERBS", "never", 400, 700),
    MACHINES(4, 3, 5, 900, -30, 5, "LACKOFWORKERS",	"never", "never", 600, 800),
    NARCOTICS(5, 0, 5, 3500, -125, 150, "BOREDOME",	"WEIRDMUSHROOMS", "never", 2000, 3000),
    ROBOTS(6, 4, 7, 5000, -150, 100, "LACKOFWORKERS", "never", "never", 3500, 5000);

    private final int minTechProduce;
    private final int minTechUse;
    private final int techLvlMostProduction;
    private final int basePrice;
    private final int priceIncrease;
    private final int variability;
    private final int minPriceOffPlanet;
    private final int maxPriceOffPlanet;
    private final String increaseEvent;
    private final String conditionReduce;
    private final String conditionExpensive;

    MarketGoodItem(int minTechProduce, int minTechUse, int techLvlMostProduction, int basePrice,
                          int priceIncrease, int variability, String increaseEvent,	String conditionReduce,
                          String conditionExpensive,	int minPriceOffPlanet, int maxPriceOffPlanet) {
        this.minTechProduce = minTechProduce;
        this.minTechUse = minTechUse;
        this.techLvlMostProduction = techLvlMostProduction;
        this.basePrice = basePrice;
        this.priceIncrease = priceIncrease;
        this.variability = variability;
        this.minPriceOffPlanet = minPriceOffPlanet;
        this.maxPriceOffPlanet = maxPriceOffPlanet;
        this.increaseEvent = increaseEvent;
        this.conditionReduce = conditionReduce;
        this.conditionExpensive = conditionExpensive;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getMaxPriceOffPlanet() {
        return maxPriceOffPlanet;
    }

    public int getMinPriceOffPlanet() {
        return minPriceOffPlanet;
    }

    public int getMinTechProduce() {
        return minTechProduce;
    }

    public int getMinTechUse() {
        return minTechUse;
    }

    public int getPriceIncrease() {
        return priceIncrease;
    }

    public int getTechLvlMostProduction() {
        return techLvlMostProduction;
    }

    public int getVariability() {
        return variability;
    }

    public String getConditionExpensive() {
        return conditionExpensive;
    }

    public String getConditionReduce() {
        return conditionReduce;
    }

    public String getIncreaseEvent() {
        return increaseEvent;
    }


    public static List<MarketGoodItem> validItems(TechLevel techLevel) {
        List<MarketGoodItem> marketInventory = new ArrayList<MarketGoodItem>();
        for (MarketGoodItem m : MarketGoodItem.values()) {
            if (techLevel.getLevel() >= m.getTechLvlMostProduction()){
                marketInventory.add(m);
            }
        }

        return marketInventory;
    }
}
