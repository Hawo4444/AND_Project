package com.example.and_project.stepCounter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_project.database.Steps;
import com.example.and_project.database.StepsRepository;

import java.util.List;

public class StepCounterViewModel extends AndroidViewModel
{
    private StepsRepository repository;
    private LiveData<List<Steps>> stepsEntries;

    public StepCounterViewModel(@NonNull Application application)
    {
        super(application);
        repository = StepsRepository.getInstance(application);
        stepsEntries = repository.getAllStepsEntries();
    }

    public LiveData<List<Steps>> getAllStepsEntries()
    {
        return stepsEntries;
    }
}
