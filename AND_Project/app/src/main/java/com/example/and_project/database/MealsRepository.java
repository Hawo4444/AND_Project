package com.example.and_project.database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_project.main.MealDetails;
import com.example.and_project.main.RecyclerViewItem;

import java.util.ArrayList;

public class MealsRepository
{
    private static MealsRepository instance;
    private MealsDao mealsDao;

    private MealsRepository(Application application)
    {
        FeednessDatabase database = FeednessDatabase.getInstance(application);
        mealsDao = database.mealsDao();
    }

    public static MealsRepository getInstance(Application application)
    {
        if(instance == null)
        {
            instance = new MealsRepository(application);
        }
        return instance;
    }

    public MutableLiveData<ArrayList<RecyclerViewItem>> getMealsForDate()
    {
        return null;
    }

    public void insertMeal(MealDetails details)
    {

    }
}
