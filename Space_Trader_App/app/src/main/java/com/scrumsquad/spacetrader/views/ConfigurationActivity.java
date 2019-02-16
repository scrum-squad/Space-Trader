package com.scrumsquad.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private Skills pilotSkill = Skills.Pilot;
    private Skills traderSkill = Skills.Trader;
    private Skills engineerSkill = Skills.Engineer;
    private Skills fighterSkill = Skills.Fighter;

    private TextView remainingSkillPoints;
    private TextView pilotLabel;
    private TextView traderLabel;
    private TextView engineerLabel;
    private TextView fighterLabel;
    private EditText playerName;
    private Button startGame;
    private Spinner difSpinner;

    // variables associated with plus buttons
    private Button[] plusButtons;
    private final int NUM_PLUS_BUTTONS = 4;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        // creates array of plus buttons
        createButtonArray();

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
                        " | Difficulty: " + ((Difficulty) difSpinner.getSelectedItem()).getLevel() +
                        " | Skills: " + Skills.Engineer.getLevel());
                //code here to save player and difficulty
            }
        });
    }

    public class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int pressedId = v.getId();
            // find which button was pressed
            if (pressedId == R.id.player_pilot_plus) {
                updatePilotLabel();
            } else if (pressedId == R.id.player_trader_plus) {
                updateTraderLabel();
            } else if (pressedId == R.id.player_engineer_plus) {
                updateEngineerLabel();
            } else if (pressedId == R.id.player_fighter_plus) {
                updateFighterLabel();
            }
        }

    }

    public void createButtonArray() {
        plusButtons = new Button[NUM_PLUS_BUTTONS];
        // I could add the buttons a different way but this is easiest for me to visualize
        plusButtons[0] = findViewById(R.id.player_pilot_plus);
        plusButtons[1] = findViewById(R.id.player_trader_plus);
        plusButtons[2] = findViewById(R.id.player_engineer_plus);
        plusButtons[3] = findViewById(R.id.player_fighter_plus);
        for (int i = 0; i < plusButtons.length; i++) {
            plusButtons[i].setOnClickListener(new ButtonClickListener());
        }
    }

    public void updatePilotLabel() {
        pilotSkill.updateLevel(pilotSkill.getLevel() + 1);
        pilotLabel = (TextView) findViewById(R.id.player_display_pilot);
        pilotLabel.setText(pilotSkill.getLevel() + "");
    }

    public void updateTraderLabel() {
        traderSkill.updateLevel(traderSkill.getLevel() + 1);
        traderLabel = (TextView) findViewById(R.id.player_display_trader);
        traderLabel.setText(traderSkill.getLevel() + "");
    }

    public void updateEngineerLabel() {
        engineerSkill.updateLevel(engineerSkill.getLevel() + 1);
        engineerLabel = (TextView) findViewById(R.id.player_display_engineer);
        engineerLabel.setText(engineerSkill.getLevel() + "");
    }

    public void updateFighterLabel() {
        fighterSkill.updateLevel(fighterSkill.getLevel() + 1);
        fighterLabel = (TextView) findViewById(R.id.player_display_fighter);
        fighterLabel.setText(fighterSkill.getLevel() + "");
    }

}