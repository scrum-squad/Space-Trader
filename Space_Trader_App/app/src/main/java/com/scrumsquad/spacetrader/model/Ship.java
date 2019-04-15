package com.scrumsquad.spacetrader.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final String name;
    private final List<MarketGoodItem> inventory;
    private int fuel;
    private final int MAX_FUEL;
    private final int itemCap;

    public Ship() {
        Ships type = Ships.GNAT;
//        MarketGoodItem[] list = new MarketGoodItem[type.getCargoCap()];
//        this.inventory = new ArrayList(Arrays.asList(list));
        inventory = new ArrayList<>();
        itemCap = type.getCargoCap();
        name = type.toString();
        fuel = type.getFuelCap();
        MAX_FUEL = type.getFuelCap();
    }

    public Ship(Ships type) {
//        MarketGoodItem[] list = new MarketGoodItem[type.getCargoCap()];
//        this.inventory = new ArrayList(Arrays.asList(list));
        inventory = new ArrayList<>();
        itemCap = type.getCargoCap();
        name = type.toString();
        fuel = type.getFuelCap();
        MAX_FUEL = type.getFuelCap();
    }

    public List<MarketGoodItem> getInventory() {
        return inventory;
    }

    public boolean canAdd() {
//        System.out.println("major: " + inventory.size());
//        for (int i = 0; i <= inventory.size() - 1; i++) {
//            System.out.println("key: " + inventory.get(i));
//            if (inventory.get(i) == null) {
//                return true;
//            }
//        }
//        return false;
        return inventory.size() < itemCap;

    }

    public void addItem(MarketGoodItem bought) {
        if(canAdd()) {
//            // This is O(2N) rn which isn't great
//            for (int i = 0; i <= inventory.size() - 1; i++) {
//                if (inventory.get(i) == null) {
//                    inventory.set(i, bought);
//                    System.out.println("Added " + bought.name() + " to ship inventory");
//                    return;
//                }
//            }
            if (bought != null) {
                inventory.add(bought);
            }
        }
    }

    public MarketGoodItem removeItem(MarketGoodItem sold) {
//        for (int i = 0; i <= inventory.size() - 1; i++) {
//            if (sold.equals(inventory.get(i))) {
//                MarketGoodItem soldItem = inventory.get(i);
//                inventory.set(i, null);
//                System.out.println("Removed " + soldItem.name() + " from ship inventory");
//                return soldItem;
//            }
//        }
//        // Item is not in inventory
//        System.out.println(sold.name() + " is not in ship inventory");
//        return null;

        for (int i = 0; i < inventory.size(); i++) {
            if (sold.equals(inventory.get(i))) {
                return inventory.remove(i);
            }
        }

        return null;
    }

    public static ArrayList<Integer> getValidShip(Ships ship) {
        ArrayList<Integer> output = new ArrayList<>();
        output.add(ship.getFuelCap());
        output.add(ship.getCargoCap());
        return output;

    }

    public int getCurrentFuel() {
        return fuel;
    }

    public int getMAX_FUEL() {
        return MAX_FUEL;
    }

    public void subtractUsedFuel(int amountUsed) {
        fuel -= amountUsed;
    }
}
