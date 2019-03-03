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
import android.widget.TextView;

import com.scrumsquad.spacetrader.R;

public class MarketActivity extends AppCompatActivity {

    private Button leaveMarket;
    private ScrollView scrollView;
    private LinearLayout marketDisplay;

    public void onCreate(Bundle instanceSaved) {
        super.onCreate(instanceSaved);
        setContentView(R.layout.activity_marketplace);

        scrollView = findViewById(R.id.market_scroll);
        marketDisplay = findViewById(R.id.market_display);


        ItemView addedItem = new ItemView(this.getApplicationContext());
        addedItem.load(10);

        marketDisplay.addView(addedItem,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        leaveMarket = findViewById(R.id.market_leave_button);
        leaveMarket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent gotoMain = new Intent(view.getContext(), GameActivity.class);
                startActivity(gotoMain);
            }
        });
    }
}
