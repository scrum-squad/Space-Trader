package com.scrumsquad.spacetrader.views;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;


import com.scrumsquad.spacetrader.R;

public class ItemView extends LinearLayout {

    private TextView name;
    private TextView price;
    private TextView amountOwn;
    private TextView amountBuyable;

    private EditText amountSelect;

    private Button bBuy;
    private Button bSell;

    public ItemView(Context context) {
        super(context);
        init();

        bBuy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println(name.getText().toString());
                // ViewModel
                // Take name, price, and value from amountSelect
                // Update funds, amountOwn, amountBuyable
            }
        });

        bSell.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // ViewModel
                // Take name, price, and value from amountSelect
                // Update funds, amountOwn, amountBuyable
            }
        });
    }

    //Initializes ItemView
    private void init() {
        setOrientation(HORIZONTAL);

        LayoutInflater.from(getContext()).inflate(R.layout.market_item_view, this);

        name = findViewById(R.id.market_item_name);
        price = findViewById(R.id.market_item_price);
        amountOwn = findViewById(R.id.market_item_owned);
        amountBuyable = findViewById(R.id.market_item_available);
        amountSelect = findViewById(R.id.market_item_amount);
        bBuy = findViewById(R.id.market_item_buy);
        bSell = findViewById(R.id.market_item_sell);
    }

    //This method loads the item view with given good's info
    public void load(int i) {
        name.setText("Sample Item");
        price.setText("" + i);
    }

}
