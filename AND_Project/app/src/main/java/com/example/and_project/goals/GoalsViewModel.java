package com.example.and_project.goals;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_project.database.Goals;
import com.example.and_project.database.GoalsRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.and_project.main.GoalsCalculator.calculateGoals;

public class GoalsViewModel extends AndroidViewModel
{
    private GoalsRepository repository;
    private LiveData<Goals> goals;
    private MutableLiveData<ArrayList<Integer>> finalGoals = new MutableLiveData<>();

    public GoalsViewModel(@NonNull Application application)
    {
        super(application);
        repository = GoalsRepository.getInstance(application);
        goals = repository.getGoals();
    }

    public MutableLiveData<ArrayList<Integer>> getGoals()
    {
        ArrayList<Integer> allGoals = new ArrayList<>();
        try {
            int goalWeight = (goals.getValue().getGoalWeight());

            allGoals.add(goalWeight);
            allGoals.addAll(calculateGoals(goals.getValue().getCurrentWeight(), goals.getValue().getGoalWeight()));
            finalGoals.setValue(allGoals);
            return finalGoals;
        }
        catch(Exception e)
        {
            allGoals.add(0);
            e.printStackTrace();
            finalGoals.setValue(allGoals);
            return finalGoals;
        }

    }
}
