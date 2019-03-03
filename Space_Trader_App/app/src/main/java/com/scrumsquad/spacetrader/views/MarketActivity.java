package com.scrumsquad.spacetrader.views;

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

import com.scrumsquad.spacetrader.R;

public class MarketActivity extends AppCompatActivity {

    private Button leaveMarket;
    private ScrollView scrollView;
    private TableLayout marketDisplay;

    public void onCreate(Bundle instanceSaved) {
        super.onCreate(instanceSaved);
        setContentView(R.layout.activity_marketplace);

        scrollView = findViewById(R.id.market_scroll);
        marketDisplay = findViewById(R.id.market_display);

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
        //Run through param data structure and add each item
        for (int i = 0; i < limit; i++) {
            TableRow added = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            added.setLayoutParams(lp);

            //Create new ItemView object
            ItemView item = new ItemView(this.getApplicationContext());
            //Loads data
            item.load(10);

            added.addView(item);
            marketDisplay.addView(added);
        }
    }

    public void clearMarket() {
        marketDisplay.removeAllViewsInLayout();
    }
}
