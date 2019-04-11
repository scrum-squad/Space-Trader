package com.scrumsquad.spacetrader.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;


import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.viewModel.MarketViewModel;

public class ItemView extends LinearLayout {

    private TextView name;
    private TextView price;
    private TextView amountOwn;

    private Button bBuy;
    private Button bSell;

    private final MarketViewModel viewModel = new MarketViewModel();

    public ItemView(Context context) {
        super(context);
        init();




    }

    //Initializes ItemView
    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);

        LayoutInflater.from(getContext()).inflate(R.layout.market_item_view, this);

        name = findViewById(R.id.market_item_name);
        price = findViewById(R.id.market_item_price);
        amountOwn = findViewById(R.id.market_item_owned);
        bBuy = findViewById(R.id.market_item_buy);
        bSell = findViewById(R.id.market_item_sell);
    }

    //This method loads the item view with given good's info
    public void load( final MarketGoodItem m, final int cost, int owned, final TextView remainingCredits) {
        String itemName = m.name().toLowerCase();
        itemName = itemName.substring(0, 1).toUpperCase() + itemName.substring(1);
        name.setText(itemName);
        price.setText("$" + cost);
        amountOwn.setText("Owned: " + owned);
        bBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.buyItem(m, cost);
                amountOwn.setText("Owned: " + viewModel.amountOwned(m));
                // Take name, price, and value from amountSelect
                // Update funds, amountOwn, amountBuyable
                remainingCredits.setText("Your Credits: " + viewModel.playerCredits());
            }
        });
        bSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.sellItem(m, cost);
                //Here I am assuming that the cost of a good to sell is == to the cost to buy
                amountOwn.setText("Owned: " + viewModel.amountOwned(m));
                // Take name, price, and value from amountSelect
                // Update funds, amountOwn, amountBuyable
                remainingCredits.setText("Your Credits: " + viewModel.playerCredits());
            }
        });
    }

}
