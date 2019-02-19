package com.scrumsquad.spacetrader.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.scrumsquad.spacetrader.model.Difficulty;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.Ships;
import com.scrumsquad.spacetrader.model.Skills;

public class ConfigurationViewModel extends ViewModel {


    private final int STARTING_CREDITS = 1000;
    private final Ships STARTING_SHIP = Ships.GNAT;
    private final int SKILL_POINTS = 16;

    // Things associated with the player char
    private Skills[] playerSkills = Skills.values();
    private String playerName;
    private Game currentGame;
    private Difficulty diff = Difficulty.Normal;






    /**
     * This should generate the view model of our config screen
     *
     * @param application
     */
//    public ConfigurationViewModel(@NonNull Application application) {
//        super(application);
//    }

    public void incrementSkill(Skills skill){
        int ind = skill.ordinal();
        playerSkills[ind].updateLevel(playerSkills[ind].getLevel() + 1);
    }
    public void decrementSkill(Skills skill) {
        int ind = skill.ordinal();
        playerSkills[ind].updateLevel(playerSkills[ind].getLevel() - 1);
    }

    public void generateCharacter() {
        Player player1 = new Player(playerName, STARTING_CREDITS, STARTING_SHIP, playerSkills);
        currentGame = new Game(player1, diff);


    }

    public int remainingSkillPoints() {
        int usedSkills = 0;
        for (Skills skill: playerSkills) {
            usedSkills += skill.getLevel();
        }
        return SKILL_POINTS - usedSkills;
    }
    public int getSkillLevel(int ind) {
        return playerSkills[ind].getLevel();
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    public Difficulty getDiff() {
        return this.diff;
    }

    public void setName(String name) {
        this.playerName = name;
    }
    public String getName() {
        return this.playerName;
    }



}
