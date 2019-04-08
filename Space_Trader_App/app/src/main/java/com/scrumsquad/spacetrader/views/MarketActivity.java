package com.scrumsquad.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.MarketGoodItem;
import com.scrumsquad.spacetrader.model.TechLevel;
import com.scrumsquad.spacetrader.viewModel.MarketViewModel;

import com.scrumsquad.spacetrader.R;

import java.util.ArrayList;
import java.util.List;

public class MarketActivity extends AppCompatActivity {

    private MarketViewModel viewModel;

    private Button leaveMarket;
    private ScrollView scrollView;
    private TableLayout marketDisplay;
    public TextView startingCredits;

    public void onCreate(Bundle instanceSaved) {
        super.onCreate(instanceSaved);
        setContentView(R.layout.activity_marketplace);
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);


        scrollView = findViewById(R.id.market_scroll);
        marketDisplay = findViewById(R.id.market_display);
        startingCredits = (TextView) findViewById(R.id.market_display_funds);
        startingCredits.setText("Your Credits: " + viewModel.playerCredits());

        //Debug tool: Test functionality of loading the market place
        loadMarket(3);

        leaveMarket = findViewById(R.id.market_leave_button);
        leaveMarket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                clearMarket();
                Intent gotoMain = new Intent(view.getContext(), GameActivity.class);
                startActivity(gotoMain);
            }
        });
    }

    public void loadMarket(int limit) {
        List<MarketGoodItem> marketInventory = MarketGoodItem.validItems(Game.getGame().getCurrentPlanet().getTechLevel());
        for (MarketGoodItem m : marketInventory) {
            TableRow added = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            added.setLayoutParams(lp);

            //Create new ItemView object
            ItemView item = new ItemView(this.getApplicationContext());
            //Loads data
            System.out.println(viewModel.amountOwned(m));
            item.load(m, viewModel.calculatePrice(m), viewModel.amountOwned(m), startingCredits);

            added.addView(item);
            marketDisplay.addView(added);
        }
    }

    public void clearMarket() {
        marketDisplay.removeAllViewsInLayout();
    }
}
