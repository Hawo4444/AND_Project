package com.example.and_project.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_project.main.MealDetails;
import com.example.and_project.main.RecyclerViewItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public LiveData<List<RecyclerViewItem>> getMealsForDate(String date)
    {
        try {
            return new GetMealsForDateAsyncTask(mealsDao).execute(date).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertMeal(MealDetails details)
    {

    }

    private static class GetMealsForDateAsyncTask extends AsyncTask<String, Void, LiveData<List<RecyclerViewItem>>>
    {
        private MealsDao mealsDao;

        private GetMealsForDateAsyncTask(MealsDao mealsDao)
        {
            this.mealsDao = mealsDao;
        }

        @Override
        protected LiveData<List<RecyclerViewItem>> doInBackground(String... strings)
        {
            return mealsDao.getAllMealsForDate(strings[0]);
        }
    }
}
