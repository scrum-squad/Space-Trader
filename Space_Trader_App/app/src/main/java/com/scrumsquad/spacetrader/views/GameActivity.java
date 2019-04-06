package com.scrumsquad.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.SolarSystem;
import com.scrumsquad.spacetrader.viewModel.GameViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private GameViewModel viewModel;

    private ImageButton enterMarket;

    // The following can be copied for the TravelTile
    private TextView planetName;
    private TextView techLevel;
    private TextView coordinates;
    private TextView resourceLevel;
    private TextView travelText;
    // Interactive Pieces
    private ProgressBar fuelLevel;
    private Spinner travelLocations;
    private ImageButton travelButton;
    private ImageButton refuelButton;
    private ImageButton saveButton;

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
        saveButton = findViewById(R.id.save_button);

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
                Random rand = new Random();
                if (rand.nextInt(3) == 2) {
                    Toast notif;
                    CharSequence text = "You left the door open and a snake snuck on to the ship and you sold it for 100 credits";
                    notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    viewModel.getPlayer().setCredits(viewModel.getPlayer().getCredits() + 100);
                    notif.show();
                }
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game game = Game.getGame();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("User");
                myRef.setValue(game);
            }
        });

        setup(viewModel.getCurrentSystem());
    }

    public void setup(SolarSystem system) {
        Planet planet = system.getPlanets().get(0);
        planetName.setText("Planet: " + planet.getName());
        techLevel.setText(planet.getTechLevel().toString());
        coordinates.setText("Coordiates: " + system.getCoordinates());
        resourceLevel.setText(planet.getResources().toString());
        fuelLevel.setProgress(100 * viewModel.getTotalFuel() / viewModel.getMAX_FUEL());

        ArrayAdapter<SolarSystem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                viewModel.getPossibleDestinations());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelLocations.setAdapter(adapter);
    }
}
