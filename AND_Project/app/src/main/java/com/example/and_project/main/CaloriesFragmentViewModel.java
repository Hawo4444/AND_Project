package com.example.and_project.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_project.database.MealsRepository;

import java.util.ArrayList;

public class CaloriesFragmentViewModel extends AndroidViewModel
{
    private MealsRepository repository;
    private MutableLiveData<ArrayList<RecyclerViewItem>> items;

    public CaloriesFragmentViewModel(@NonNull Application application)
    {
        super(application);
        repository = new MealsRepository(application);
        items = repository.getMealsForDate();
    }

    public LiveData<ArrayList<RecyclerViewItem>> getAllItems()
    {
        ArrayList<RecyclerViewItem> currentItems = items.getValue();
        currentItems.add(0, new Header("Today's Meals:"));
        currentItems.add(new AddButton());
        items.setValue(currentItems);
        return items;
    }

    public void addMeal(MealDetails details)
    {
        repository.insertMeal(details);
    }

    public void deleteMeal(MealDetails details)
    {
        //repository.
    }
}
