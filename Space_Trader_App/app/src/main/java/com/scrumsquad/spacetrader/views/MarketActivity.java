
package com.scrumsquad.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.viewModel.MarketViewModel;

import com.scrumsquad.spacetrader.R;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("SameParameterValue")
public class MarketActivity extends AppCompatActivity {

    private MarketViewModel viewModel;

    private TableLayout marketDisplay;
    private TextView startingCredits;

    @Override
    public void onCreate(Bundle instanceSaved) {
        super.onCreate(instanceSaved);
        setContentView(R.layout.activity_marketplace);
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);


        ScrollView scrollView = findViewById(R.id.market_scroll);
        marketDisplay = findViewById(R.id.market_display);
        startingCredits = findViewById(R.id.market_display_funds);
        startingCredits.setText("Your Credits: " + viewModel.playerCredits());

        //Debug tool: Test functionality of loading the market place
        loadMarket(3);

        Button leaveMarket = findViewById(R.id.market_leave_button);
        leaveMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearMarket();
                Intent gotoMain = new Intent(view.getContext(), GameActivity.class);
                startActivity(gotoMain);
            }
        });
    }

    private void loadMarket(int limit) {
        //Run through param data structure and add each item
        Collection<MarketGoodItem> marketInventory = MarketGoodItem.validItems(Game.getGame().getCurrentPlanet().getTechLevel());
        for (MarketGoodItem m : marketInventory) {
            TableRow added = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            added.setLayoutParams(lp);

            //Create new ItemView object
            ItemView item = new ItemView(getApplicationContext());
            //Loads data
            item.load(m, viewModel.calculatePrice(m), viewModel.amountOwned(m), startingCredits);

            added.addView(item);
            marketDisplay.addView(added);
        }
    }

    private void clearMarket() {
        marketDisplay.removeAllViewsInLayout();
    }
}