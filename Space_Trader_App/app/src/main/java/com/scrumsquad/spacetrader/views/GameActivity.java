package com.scrumsquad.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.Game;

public class GameActivity extends AppCompatActivity {

    private Button enterMarket;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        enterMarket = findViewById(R.id.game_goto_market);
        enterMarket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent swapMarket = new Intent (view.getContext(), MarketActivity.class);
                startActivity(swapMarket);
            }
        });
    }
}
