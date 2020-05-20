package com.example.and_project.stepCounter;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_project.database.Steps;
import com.example.and_project.database.StepsRepository;

import java.util.Calendar;
import java.util.List;

public class StepCounterViewModel extends AndroidViewModel
{
    private int stepsCount = 0;
    private StepsRepository repository;
    private LiveData<List<Steps>> stepsEntries;

    public StepCounterViewModel(@NonNull Application application)
    {
        super(application);
        repository = new StepsRepository(application);
        stepsEntries = repository.getAllStepsEntries();
    }

    public LiveData<List<Steps>> getAllStepsEntries()
    {
        return repository.getAllStepsEntries();
    }

    public void createStepsEntry()
    {
        Calendar mCalendar = Calendar.getInstance();
        String date = mCalendar.get(Calendar.MONTH) + "/" + mCalendar.get(Calendar.DAY_OF_MONTH) + "/" + mCalendar.get(Calendar.YEAR);
        if (repository.getStepsForDate(date) == null)
        {
            stepsCount = 1;
            repository.insert(new Steps(date, stepsCount));
        }
        else
        {
            stepsCount++;
            repository.update(new Steps(date,stepsCount));
        }
    }
}
