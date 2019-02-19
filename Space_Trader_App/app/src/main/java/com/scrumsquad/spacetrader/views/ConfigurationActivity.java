package com.scrumsquad.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.arch.lifecycle.ViewModelProvider;

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

    private String name;
    private Player player;
    private Difficulty difLevel;

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
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
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
        difSpinner.setSelection(viewModel.getDiff().ordinal());

        startGame.setClickable(false);
        startGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                viewModel.setName(playerName.getText().toString());
                viewModel.setDiff((Difficulty) difSpinner.getSelectedItem());
                viewModel.generateCharacter();
            }
        });
    }

    public class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int pressedId = v.getId();
            //I think that this should be changed to something less hardcoded but idk how
            // find which button was pressed
            System.out.println(pressedId);
            if (pressedId == R.id.player_pilot_plus) {
                System.out.println("Inc Pilot" + Skills.Pilot.name());
                viewModel.incrementSkill(Skills.Pilot);
            } else if (pressedId == R.id.player_trader_plus) {
                viewModel.incrementSkill(Skills.Trader);
            } else if (pressedId == R.id.player_engineer_plus) {
                viewModel.incrementSkill(Skills.Engineer);
            } else if (pressedId == R.id.player_fighter_plus) {
                viewModel.incrementSkill(Skills.Fighter);
            }
            if (pressedId == R.id.player_pilot_minus) {
                viewModel.decrementSkill(Skills.Pilot);
            } else if (pressedId == R.id.player_trader_minus) {
                viewModel.decrementSkill(Skills.Trader);
            } else if (pressedId == R.id.player_engineer_minus) {
                viewModel.decrementSkill(Skills.Engineer);
            } else if (pressedId == R.id.player_fighter_minus) {
                viewModel.decrementSkill(Skills.Fighter);
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
        pilotLabel.setText(viewModel.getSkillLevel(0) + "");
        traderLabel.setText(viewModel.getSkillLevel(2) + "");
        engineerLabel.setText(viewModel.getSkillLevel(3) + "");
        fighterLabel.setText(viewModel.getSkillLevel(1) + "");
        disableMinus();
        disablePlus();
    }
    private void disableMinus() {
        minusButtons[0].setClickable(viewModel.getSkillLevel(0) > 0);
        minusButtons[3].setClickable(viewModel.getSkillLevel(1) > 0);
        minusButtons[1].setClickable(viewModel.getSkillLevel(2) > 0);
        minusButtons[2].setClickable(viewModel.getSkillLevel(3) > 0);
    }

    private void disablePlus() {
        for (Button button: plusButtons) {
            button.setClickable(viewModel.remainingSkillPoints() > 0);
        }
    }
    private void updateRemainingSkillPoints() {
        remainingSkillPoints = (TextView) findViewById(R.id.player_total_skill_points);
        int skillPointsLeft = viewModel.remainingSkillPoints();
        remainingSkillPoints.setText(skillPointsLeft + " Points Left");
        if (skillPointsLeft == 0) {
            startGame.setClickable(true);
            disablePlus();
        }
    }


}