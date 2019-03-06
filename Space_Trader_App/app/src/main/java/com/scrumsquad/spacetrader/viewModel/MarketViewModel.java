package com.scrumsquad.spacetrader.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.scrumsquad.spacetrader.model.Difficulty;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.Ships;
import com.scrumsquad.spacetrader.model.Skills;

import java.sql.SQLOutput;

public class MarketViewModel extends ViewModel {

    private Player player = Game.getGame().getPlayer();

    /**
     * Uses the currentPlanet and the item to calculate the real price
     *
     * @return the calculated price
     */
    public int calculatePrice(MarketGoodItem item) {
        // basePrice + IPL * (Planet Tech Level - MTLP) + (basePrice * variance/100)
        Planet currentPlanet = Game.getGame().getCurrentPlanet();
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
                // Might throw a NPE might need to switch var positions
                ownCount++;
            }
        }
        return ownCount;
    }

    public void buyItem(MarketGoodItem item, int cost) {
        if( player.getShip().canAdd() && player.getCredits() >= cost) {
            // If the player has room and credits for the item
            player.getShip().addItem(item);
            player.setCredits(player.getCredits() - cost);
            System.out.println("Bought " + item.name());
        }
    }
//    public void sellItem(MarketGoodItem item, int value) {
//        if (amountOwned(item) > 0)
//    }
}
