package com.example.and_project.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_project.database.MealsRepository;
import com.example.and_project.domain.Meals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SharedViewModel extends AndroidViewModel
{
    private MealsRepository repository;
    private LiveData<List<Meals>> items;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public SharedViewModel(@NonNull Application application)
    {
        super(application);
        repository = MealsRepository.getInstance(getApplication());

        items = repository.getMealsForDate(getCurrentDate());
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
        repository.deleteMeal(meal);
    }
}
