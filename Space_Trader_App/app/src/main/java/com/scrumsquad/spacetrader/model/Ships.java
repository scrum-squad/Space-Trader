package com.scrumsquad.spacetrader.model;

public enum Ships {
    GNAT  ("Gnat", 15);

    private final String name;
    private final MarketGoodItem[] inventory;

    Ships(final String name, final int capacity) {
        this.inventory = new MarketGoodItem[capacity];
        this.name = name;
    }

    public String getName() { return name; }

    public MarketGoodItem[] getInventory() {
        return inventory;
    }

    public boolean canAdd() {
        for (int i = 0; i <= inventory.length - 1; i++) {
            if (inventory[i] == null) {
                return true;
            }
        }
        return false;
    }

    public void addItem(MarketGoodItem bought) {
        if(canAdd()) {
            // This is O(2N) rn which isn't great
            for (int i = 0; i <= inventory.length - 1; i++) {
                if (inventory[i] == null) {
                    inventory[i] = bought;
                    System.out.println("Added " + bought.name() + " to ship inventory");
                    return;
                }
            }
        }
    }

    public MarketGoodItem removeItem(MarketGoodItem sold) {
        for (int i = 0; i <= inventory.length - 1; i++) {
            if (inventory[i].equals(sold)) {
                MarketGoodItem soldItem = inventory[i];
                inventory[i] = null;
                System.out.println("Removed " + soldItem.name() + " from ship inventory");
                return soldItem;
            }
        }
        // Item is not in inventory
        System.out.println(sold.name() + " is not in ship inventory");
        return null;
    }
}
