package com.scrumsquad.spacetrader.viewModel;

import android.arch.lifecycle.ViewModel;

import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.Player;

public class MarketViewModel extends ViewModel {

    private final Game game = Game.getGame();
    private final Player player = game.getPlayer();
    private final Planet currentPlanet = game.getCurrentPlanet();

    /**
     * Uses the currentPlanet and the item to calculate the real price
     *  Generates price from economic model.
     * @return the calculated price
     */
    public int calculatePrice(MarketGoodItem item) {
        // basePrice + IPL * (Planet Tech Level - MTLP) + (basePrice * variance/100)
        // add base price
        int price = item.getBasePrice();
        // add IPL * planet tech - MTLP
        price += item.getPriceIncrease() * (currentPlanet.getTechLevel().ordinal() - item.getMinTechProduce());
        // add basePrice * variance / 100
        price += item.getBasePrice() * ((int) (Math.random() * (item.getVariability() + 1)) / 100);
        return price;
    }

    public int amountOwned(MarketGoodItem item) {
        int ownCount = 0;
        for (MarketGoodItem inInv: player.getShip().getInventory()) {
            if (item.equals(inInv)) {
                ownCount++;
            }
        }
        return ownCount;
    }

    public void buyItem(MarketGoodItem item, int cost) {
        if(player.getShip().canAdd() && (player.getCredits() >= cost)) {
            // If the player has room and credits for the item
            player.getShip().addItem(item);
            player.setCredits(player.getCredits() - cost);
        }
    }
    public void sellItem(MarketGoodItem item, int value) {
        if (amountOwned(item) > 0) {
            // If the player owns the item
            player.getShip().removeItem(item);
            player.setCredits(player.getCredits() + value);
        }
    }

    public int playerCredits() {
        return player.getCredits();
    }
}
