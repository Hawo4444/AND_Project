package com.example.and_project.goals;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.and_project.database.Goals;
import com.example.and_project.database.GoalsRepository;

public class GoalsViewModel extends AndroidViewModel
{
    private GoalsRepository repository;
    private LiveData<Goals> goals;

    public GoalsViewModel(@NonNull Application application)
    {
        super(application);
        repository = GoalsRepository.getInstance(application);
        goals = repository.getGoals();
    }

    public LiveData<Goals> getGoals()
    {
        return goals;
    }
}
