package com.scrumsquad.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.SolarSystem;
import com.scrumsquad.spacetrader.viewModel.GameViewModel;

import org.w3c.dom.Text;

import java.util.Arrays;

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
    private Button travelButton;
    private Button refuelButton;

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
        travelButton = findViewById(R.id.travel_button);
        refuelButton = findViewById(R.id.refuel_button);

        viewModel = new GameViewModel();

        enterMarket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent swapMarket = new Intent (view.getContext(), MarketActivity.class);
                startActivity(swapMarket);
            }
        });

        // Don't understand this yet
        //travelLocations.setSelection(viewModel.);

        travelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SolarSystem destination = (SolarSystem) travelLocations.getSelectedItem();
                viewModel.travelToDestination(destination);
                setup(destination);
            }
        });

        refuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setTotalFuel(viewModel.getMAX_FUEL());
                setup(viewModel.getCurrentSystem());
            }
        });

        setup(viewModel.getCurrentSystem());
    }

    public void setup(SolarSystem system) {
        Planet planet = system.getPlanets()[0];
        planetName.setText("Planet: " + planet.getName());
        techLevel.setText(planet.getTechLevel().toString());
        coordinates.setText("Coordiates: " + system.getCoordinates());
        resourceLevel.setText(planet.getResources().toString());
        fuelLevel.setProgress((int) ((double)viewModel.getTotalFuel() / (double) viewModel.getMAX_FUEL()));

        ArrayAdapter<SolarSystem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                viewModel.getPossibleDestinations());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelLocations.setAdapter(adapter);
    }
}
