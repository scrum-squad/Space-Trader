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

    private Button enterMarket;

    // The following can be copied for the TravelTile
    private TextView planetName;
    private TextView techLevel;
    private TextView coordinates;
    private TextView resourceLevel;
    private TextView travelText;
    // Interactive Pieces
    private ProgressBar fuelLevel;
    private Spinner travelLocations;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        enterMarket = findViewById(R.id.game_goto_market);
        planetName = findViewById(R.id.planetName);
        techLevel = findViewById(R.id.planetTech);
        coordinates = findViewById(R.id.planetCord);
        resourceLevel = findViewById(R.id.planetResource);
        travelText = findViewById(R.id.travelLabel);
        fuelLevel = findViewById(R.id.fuelProgress);
        travelLocations = findViewById(R.id.travelOptions);

        enterMarket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent swapMarket = new Intent (view.getContext(), MarketActivity.class);
                startActivity(swapMarket);
            }
        });


        //commented out for now because it doesn't populate
        /**
        ArrayAdapter<> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList(viewModel.generatePossibleDestinations()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelLocations.setAdapter(adapter);
        travelLocations.setSelection(viewModel.getDiff().ordinal());
        */

    }
}
