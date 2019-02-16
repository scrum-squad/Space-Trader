package com.scrumsquad.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.scrumsquad.spacetrader.R;
import com.scrumsquad.spacetrader.model.Difficulty;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.Ships;
import com.scrumsquad.spacetrader.model.Skills;

import java.util.Arrays;
import java.util.Map;

public class ConfigurationActivity extends AppCompatActivity{

    private final int STARTING_CREDITS = 1000;
    private final Ships STARTING_SHIP = Ships.GNAT;

    private String name;
    private Player player;
    private Difficulty difLevel;

    private TextView remainingSkillPoints;
    private EditText playerName;
    private Button startGame;
    private Spinner difSpinner;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        remainingSkillPoints = findViewById(R.id.player_total_skill_points);
        playerName = findViewById(R.id.player_name_input);
        startGame = findViewById(R.id.player_startGame);
        difSpinner = findViewById(R.id.spinner_difficulty_config);

        ArrayAdapter<Difficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                Arrays.asList(Difficulty.values()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difSpinner.setAdapter(adapter);

        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (Skills skill : Skills.values()) {
                    String id = "player_display_" + skill.name().toLowerCase();
                    int resID = getResources().getIdentifier(id, "id", getPackageName());
                    skill.updateLevel(Integer.parseInt(((TextView) findViewById(resID)).getText().toString()));
                }
                player = new Player(playerName.getText().toString(), STARTING_CREDITS, STARTING_SHIP);
                System.out.println("Name: " + playerName.getText().toString() +
                        " | Difficulty: " + ((Difficulty) difSpinner.getSelectedItem()).getLevel());
                //code here to save player and difficulty
            }
        });
    }

}