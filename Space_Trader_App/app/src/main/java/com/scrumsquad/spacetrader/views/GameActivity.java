package com.scrumsquad.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.viewModel.GameViewModel;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {

    private GameViewModel viewModel;

    private Button enterMarket = findViewById(R.id.game_goto_market);

    // The following can be copied for the TravelTile
    final TextView planetName = findViewById(R.id.planetName);
    final TextView techLevel = findViewById(R.id.planetTech);
    final TextView coordinates = findViewById(R.id.planetCord);
    final TextView resourceLevel = findViewById(R.id.planetResource);
    final TextView travelText = findViewById(R.id.travelLabel);
    // Interactive Pieces
    final ProgressBar fuelLevel = findViewById(R.id.fuelProgress);
    final Spinner travelLocations = findViewById(R.id.travelOptions);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        enterMarket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent swapMarket = new Intent (view.getContext(), MarketActivity.class);
                startActivity(swapMarket);
            }
        });




        ArrayAdapter<> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList(viewModel.generatePossibleDestinations()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelLocations.setAdapter(adapter);
        travelLocations.setSelection(viewModel.getDiff().ordinal());


    }
}
