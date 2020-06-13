package com.example.and_project.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_project.database.Goals;
import com.example.and_project.database.GoalsRepository;
import com.example.and_project.database.MealsRepository;
import com.example.and_project.domain.Meals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SharedViewModel extends AndroidViewModel
{
    private MealsRepository mealsRepository;
    private GoalsRepository goalsRepository;
    private LiveData<List<Meals>> items;
    private LiveData<Goals> goals;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public SharedViewModel(@NonNull Application application)
    {
        super(application);
        mealsRepository = MealsRepository.getInstance(getApplication());
        goalsRepository = GoalsRepository.getInstance(getApplication());

        items = mealsRepository.getMealsForDate(getCurrentDate());
        goals = goalsRepository.getGoals();
    }

    private String getCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public LiveData<List<Meals>> getAllItems()
    {
        return items;
    }

    public void deleteMeal(Meals meal)
    {
        mealsRepository.deleteMeal(meal);
    }

    public LiveData<Goals> getGoals()
    {
        return goals;
    }
}
