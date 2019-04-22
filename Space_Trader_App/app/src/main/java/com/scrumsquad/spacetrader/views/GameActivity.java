package com.scrumsquad.spacetrader.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Pirate;
import com.scrumsquad.spacetrader.model.Planet;
import com.scrumsquad.spacetrader.model.SolarSystem;
import com.scrumsquad.spacetrader.viewModel.GameViewModel;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private GameViewModel viewModel;

    // The following can be copied for the TravelTile
    private TextView planetName;
    private TextView techLevel;
    private TextView coordinates;
    private TextView resourceLevel;
    // Interactive Pieces
    private ProgressBar fuelLevel;
    private Spinner travelLocations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ImageButton enterMarket = findViewById(R.id.game_goto_market);
        planetName = findViewById(R.id.planetName);
        techLevel = findViewById(R.id.planetTech);
        coordinates = findViewById(R.id.planetCord);
        resourceLevel = findViewById(R.id.planetResource);
        TextView travelText = findViewById(R.id.travelLabel);
        fuelLevel = findViewById(R.id.fuelProgress);
        travelLocations = findViewById(R.id.travelOptions);
        Button travelButton = findViewById(R.id.travel_button);
        ImageButton refuelButton = findViewById(R.id.refuel_button);
        ImageButton saveButton = findViewById(R.id.save_button);

        viewModel = new GameViewModel();

        enterMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent swapMarket = new Intent (view.getContext(), MarketActivity.class);
                startActivity(swapMarket);
            }
        });

        // Don't understand this yet
        //travelLocations.setSelection(viewModel.);

        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SolarSystem destination = (SolarSystem) travelLocations.getSelectedItem();
                viewModel.travelToDestination(destination);
                Random rand = new Random();
                int ran = rand.nextInt(5);
                if (ran == 2) {
                    Toast notif;
                    CharSequence text = "You left the door open and a snake snuck on to the ship and you sold it for 100 credits";
                    notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    viewModel.getPlayer().setCredits(viewModel.getPlayer().getCredits() + 100);
                    notif.show();
                }
                if (ran == 0) {
                    pirateAttack(new Pirate());
                }
                if (ran == 1) {
                    mercAttack();
                }
                if (ran == 5) {
                    popoAttack();
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

    private void setup(SolarSystem system) {
        Planet planet = system.getPlanets().get(0);
        planetName.setText("Planet: " + planet.getName());
        techLevel.setText(planet.getTechLevel().toString());
        coordinates.setText("Coordinates: " + system.getCoordinates());
        resourceLevel.setText(planet.getResources().toString());
        fuelLevel.setProgress((viewModel.getTotalFuel() / viewModel.getMAX_FUEL()) * 100);

        ArrayAdapter<SolarSystem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                viewModel.getPossibleDestinations());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelLocations.setAdapter(adapter);
    }

    public void pirateAttack(final Pirate pirate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Pirates have found you! Would you like to run or fight?")
                .setTitle("OH NO! PIRATES");

        builder.setPositiveButton("Fight!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast notif;
                CharSequence text = "Pirate defeated with your weapon!";
                notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                notif.show();
            }
        });
        builder.setNegativeButton("Run!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast notif;
                CharSequence text = "You ran away and lost some credit on the way.";
                notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                viewModel.getPlayer().setCredits(viewModel.getPlayer().getCredits() >= 10 ? viewModel.getPlayer().getCredits() - 10 : 0);
                notif.show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void mercAttack() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Mercenaries have found you! Would you like to try running or pay?")
                .setTitle("OH NO! MERCEMARIES");

        builder.setPositiveButton("Pay!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast notif;
                CharSequence text = "Mercenaries paid off!";
                viewModel.getPlayer().setCredits(viewModel.getPlayer().getCredits() >= 20 ? viewModel.getPlayer().getCredits() - 20 : 0);
                notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                notif.show();
            }
        });
        builder.setNegativeButton("Run!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast notif;
                Random random = new Random();
                if (random.nextInt(4) == 0) {
                    CharSequence text = "You tried running away and lost some credit on the way, and could NOT get away, try again.";
                    notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    notif.show();
                    mercAttack();
                } else {
                    CharSequence text = "You tried running away and lost some credit on the way.";
                    notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    viewModel.getPlayer().setCredits(viewModel.getPlayer().getCredits() >= 10 ? viewModel.getPlayer().getCredits() - 10 : 0);
                    notif.show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void popoAttack() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The Police have found you speeding!")
                .setTitle("OH NO! THE POPO");

        builder.setPositiveButton("Pay!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast notif;
                CharSequence text = "Fines paid off!";
                viewModel.getPlayer().setCredits(viewModel.getPlayer().getCredits() >= 20 ? viewModel.getPlayer().getCredits() - 20 : 0);
                notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                notif.show();
            }
        });
        builder.setNegativeButton("Bribe!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast notif;
                Random random = new Random();
                if (random.nextInt(4) == 0) {
                    CharSequence text = "You tried bribing the police away, and could NOT, try again.";
                    notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    notif.show();
                    popoAttack();
                } else {
                    CharSequence text = "You tried running away and lost some credit on the way.";
                    notif = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    viewModel.getPlayer().setCredits(viewModel.getPlayer().getCredits() >= 10 ? viewModel.getPlayer().getCredits() - 10 : 0);
                    notif.show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
