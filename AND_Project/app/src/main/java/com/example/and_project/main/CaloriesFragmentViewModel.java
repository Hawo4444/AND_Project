package com.example.and_project.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_project.database.Meals;
import com.example.and_project.database.MealsRepository;

import java.util.List;

//import static com.example.and_project.domain.Calendar.getTimeStamp;


public class CaloriesFragmentViewModel extends AndroidViewModel
{
    private MealsRepository repository;
    private LiveData<List<Meals>> items;

    public CaloriesFragmentViewModel(@NonNull Application application)
    {
        super(application);
        repository = MealsRepository.getInstance(getApplication());
        items = repository.getMealsForDate("24/5/2020");
    }

    public LiveData<List<Meals>> getAllItems()
    {
        return items;
    }

    public void addMeal(Meals details)
    {
        //repository.insertMeal(details);
    }

    public void deleteMeal(Meals details)
    {
        //repository.
    }
}
