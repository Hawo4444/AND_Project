package com.example.and_project.calendar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_project.database.Meals;
import com.example.and_project.database.MealsRepository;

import java.util.List;

public class HistoricalDataViewModel extends AndroidViewModel
{
    private MealsRepository repository;
    private LiveData<List<Meals>> mealsEntriesForDate;

    public HistoricalDataViewModel(@NonNull Application application)
    {
        super(application);
        repository = MealsRepository.getInstance(application);
        //mealsEntriesForDate = repository.getMealsForDate(); //pass today's date here
    }

    public LiveData<List<Meals>> getMealsForDate()
    {
        //return stepsEntries;
        return null;
    }
}
