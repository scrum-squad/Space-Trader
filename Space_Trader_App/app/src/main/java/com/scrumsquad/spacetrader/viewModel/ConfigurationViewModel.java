package com.scrumsquad.spacetrader.viewModel;

import android.arch.lifecycle.ViewModel;

import com.scrumsquad.spacetrader.model.Difficulty;
import com.scrumsquad.spacetrader.model.Game;
import com.scrumsquad.spacetrader.model.Player;
import com.scrumsquad.spacetrader.model.Ships;
import com.scrumsquad.spacetrader.model.Skills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigurationViewModel extends ViewModel {


    private final int STARTING_CREDITS = 1000;
    private final Ships STARTING_SHIP = Ships.GNAT;
    private final int SKILL_POINTS = 16;

    // Things associated with the player char
    private final List<Skills> playerSkills = new ArrayList(Arrays.asList(Skills.values()));
    private String playerName;
    private Game currentGame;
    private Difficulty diff = Difficulty.Normal;


    public void incrementSkill(Skills skill){
        int ind = skill.ordinal();
        playerSkills.get(ind).updateLevel(playerSkills.get(ind).getLevel() + 1);
    }
    public void decrementSkill(Skills skill) {
        int ind = skill.ordinal();
        playerSkills.get(ind).updateLevel(playerSkills.get(ind).getLevel() - 1);
    }

    public void generateCharacter() {
        Player player1 = new Player(playerName, STARTING_CREDITS, STARTING_SHIP, playerSkills);
        String[] planetNamess = {"Acamar", "Adahn", "Aldea", "Andevian", "Antedi", "Balosnee", "Baratas", "Brax", "Bretel", "Calondia",
                "Campor", "Capelle", "Carzon", "Castor", "Cestus", "Cheron", "Courteney", "Daled", "Damast", "Janus", "Japori"};
        List<String> planetNames = new ArrayList<>(Arrays.asList(planetNamess));
        Game.makeGame(player1, diff, planetNames);
    }

    public int remainingSkillPoints() {
        int usedSkills = 0;
        for (Skills skill: playerSkills) {
            usedSkills += skill.getLevel();
        }
        return SKILL_POINTS - usedSkills;
    }


    public int getSkillLevel(int ind) {
        return playerSkills.get(ind).getLevel();
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
