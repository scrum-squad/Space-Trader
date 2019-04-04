package com.scrumsquad.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private String name;
    private List<MarketGoodItem> inventory;
    private int fuel;
    private final int MAX_FUEL;

    public Ship(Ships type) {
        this.inventory = new ArrayList(type.getCargoCap());
        this.name = type.toString();
        this.fuel = type.getFuelCap();
        this.MAX_FUEL = type.getFuelCap();
    }

    public List<MarketGoodItem> getInventory() {
        return inventory;
    }

    public boolean canAdd() {
        for (int i = 0; i <= inventory.size() - 1; i++) {
            if (inventory.get(i) == null) {
                return true;
            }
        }
        return false;
    }

    public void addItem(MarketGoodItem bought) {
        if(canAdd()) {
            // This is O(2N) rn which isn't great
            for (int i = 0; i <= inventory.size() - 1; i++) {
                if (inventory.get(i) == null) {
                    inventory.set(i, bought);
                    System.out.println("Added " + bought.name() + " to ship inventory");
                    return;
                }
            }
        }
    }

    public MarketGoodItem removeItem(MarketGoodItem sold) {
        for (int i = 0; i <= inventory.size() - 1; i++) {
            if (sold.equals(inventory.get(i))) {
                MarketGoodItem soldItem = inventory.get(i);
                inventory.set(i, null);
                System.out.println("Removed " + soldItem.name() + " from ship inventory");
                return soldItem;
            }
        }
        // Item is not in inventory
        System.out.println(sold.name() + " is not in ship inventory");
        return null;
    }

    public int getCurrentFuel() {
        return this.fuel;
    }

    public int getMAX_FUEL() {
        return MAX_FUEL;
    }

    public void subtractUsedFuel(int amountUsed) {
        this.fuel -= amountUsed;
    }
}
