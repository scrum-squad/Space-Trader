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
import com.scrumsquad.spacetrader.viewModel.ConfigurationViewModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationActivity extends AppCompatActivity{

    private ConfigurationViewModel viewModel;

    private final int STARTING_CREDITS = 1000;
    private final Ships STARTING_SHIP = Ships.GNAT;
    private final int SKILL_POINTS = 16;

    private String name;
    private Player player;
    private Difficulty difLevel;
    // This allows us to add more skills more easily
    private Skills[] playerSkills = Skills.values();
    private int skillPointsUsed;

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

    //
    private Button[] minusButtons;
    private final int NUM_MINUS_BUTTONS = 4;


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

        startGame.setClickable(false);
        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (skillPointsUsed != SKILL_POINTS) {
                    
                }
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
            //I think that this should be changed to something less hardcoded but idk how
            // find which button was pressed
            if (pressedId == R.id.player_pilot_plus) {
                incrementSkill(Skills.Pilot);
            } else if (pressedId == R.id.player_trader_plus) {
                incrementSkill(Skills.Trader);
            } else if (pressedId == R.id.player_engineer_plus) {
                incrementSkill(Skills.Engineer);
            } else if (pressedId == R.id.player_fighter_plus) {
                incrementSkill(Skills.Fighter);
            }
            if (pressedId == R.id.player_pilot_minus) {
                decrementSkill(Skills.Pilot);
            } else if (pressedId == R.id.player_trader_minus) {
                decrementSkill(Skills.Trader);
            } else if (pressedId == R.id.player_engineer_minus) {
                decrementSkill(Skills.Engineer);
            } else if (pressedId == R.id.player_fighter_minus) {
                decrementSkill(Skills.Fighter);
            }
            updateSkillLabels();
            updateRemainingSkillPoints();
        }

    }

    public void createButtonArray() {
        plusButtons = new Button[NUM_PLUS_BUTTONS];
        // I could add the buttons a different way but this is easiest for me to visualize

        // I want to ask a TA about a way to iterate through ui elements
        plusButtons[0] = findViewById(R.id.player_pilot_plus);
        plusButtons[1] = findViewById(R.id.player_trader_plus);
        plusButtons[2] = findViewById(R.id.player_engineer_plus);
        plusButtons[3] = findViewById(R.id.player_fighter_plus);
        for (int i = 0; i < plusButtons.length; i++) {
            plusButtons[i].setOnClickListener(new ButtonClickListener());
        }
        minusButtons = new Button[NUM_MINUS_BUTTONS];
        minusButtons[0] = findViewById(R.id.player_pilot_minus);
        minusButtons[1] = findViewById(R.id.player_trader_minus);
        minusButtons[2] = findViewById(R.id.player_engineer_minus);
        minusButtons[3] = findViewById(R.id.player_fighter_minus);
        for (int i = 0; i < minusButtons.length; i++) {
            minusButtons[i].setOnClickListener(new ButtonClickListener());
            minusButtons[i].setClickable(false);
        }
    }

    private void updateSkillLabels() {
        // This can definitely be more modular if we can figure out how to set these dang buttons
        // Might be worth looking more into resources
        pilotLabel = (TextView) findViewById(R.id.player_display_pilot);
        traderLabel = (TextView) findViewById(R.id.player_display_trader);
        engineerLabel = (TextView) findViewById(R.id.player_display_engineer);
        fighterLabel = (TextView) findViewById(R.id.player_display_fighter);
        pilotLabel.setText(playerSkills[0].getLevel() + "");
        traderLabel.setText(playerSkills[2].getLevel() + "");
        engineerLabel.setText(playerSkills[3].getLevel() + "");
        fighterLabel.setText(playerSkills[1].getLevel() + "");
    }
    private void updateRemainingSkillPoints() {
        remainingSkillPoints = (TextView) findViewById(R.id.player_total_skill_points);
        int usedSkills = 0;
        for (Skills skill: playerSkills) {
            usedSkills += skill.getLevel();
        }
        this.skillPointsUsed = usedSkills;
        // Calculates the number of allocated skill points
        // I think this needs to be visible to more of the app in order to set the
        // start journey button based on it but here is what I am thinking
        for (Button minus : minusButtons) {
                minus.setClickable(usedSkills > 0);
            }


        remainingSkillPoints.setText(SKILL_POINTS - usedSkills + " Points Left");
        if (SKILL_POINTS - usedSkills == 0) {
            startGame.setClickable(true);
            for (int i = 0; i < plusButtons.length; i++) {
                plusButtons[i].setClickable(false);
            }
        }

    }
    private void incrementSkill(Skills skill){
        skill.updateLevel(skill.getLevel() + 1);
    }

    private void decrementSkill(Skills skill) {
        skill.updateLevel(skill.getLevel() - 1);
    }

//    public void updatePilotLabel() {
//        pilotLabel = (TextView) findViewById(R.id.player_display_pilot);
//        pilotLabel.setText(pilotSkill.getLevel() + "");
//    }
//
//    public void updateTraderLabel() {
//        traderLabel = (TextView) findViewById(R.id.player_display_trader);
//        traderLabel.setText(traderSkill.getLevel() + "");
//    }
//
//    public void updateEngineerLabel() {
//        engineerLabel = (TextView) findViewById(R.id.player_display_engineer);
//        engineerLabel.setText(engineerSkill.getLevel() + "");
//    }
//
//    public void updateFighterLabel() {
//        fighterLabel = (TextView) findViewById(R.id.player_display_fighter);
//        fighterLabel.setText(fighterSkill.getLevel() + "");
//    }

}