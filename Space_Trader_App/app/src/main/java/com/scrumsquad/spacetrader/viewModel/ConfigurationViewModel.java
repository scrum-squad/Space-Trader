package com.scrumsquad.spacetrader.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.scrumsquad.spacetrader.model.Skills;

public class ConfigurationViewModel extends AndroidViewModel{

    /**
     * This should generate the view model of our config screen
     *
     * @param application
     */
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    public void incrementSkill(Skills skill){
        skill.updateLevel(skill.getLevel() + 1);
    }
    public void decrementSkill(Skills skill) {
        skill.updateLevel(skill.getLevel() - 1);
    }



}
