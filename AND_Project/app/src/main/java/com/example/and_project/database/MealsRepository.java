package com.example.and_project.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.and_project.api.ApiEndpoint;
import com.example.and_project.domain.Meals;
import com.example.and_project.api.ServiceGenerator;
import com.example.and_project.domain.ApiBody;
import com.example.and_project.domain.MealsList;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Response;

public class MealsRepository
{
    private static MealsRepository instance;
    private MealsDao mealsDao;
    private ApiEndpoint apiEndpoint;

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

    public LiveData<List<Meals>> getMealsForDate(String date)
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

    public void insertMeal(Meals meal)
    {
        new InsertMealsAsyncTask(mealsDao).execute(meal);
    }

    private static class InsertMealsAsyncTask extends AsyncTask<Meals, Void, Void>
    {
        private MealsDao mealsDao;

        private InsertMealsAsyncTask(MealsDao mealsDao)
        {
            this.mealsDao = mealsDao;
        }

        @Override
        protected Void doInBackground(Meals... meals)
        {
            mealsDao.insert(meals[0]);
            return null;
        }
    }

    public void deleteMeal(Meals meal)
    {
        new DeleteMealsAsyncTask(mealsDao).execute(meal);
    }

    private static class DeleteMealsAsyncTask extends AsyncTask<Meals, Void, Void>
    {
        private MealsDao mealsDao;

        private DeleteMealsAsyncTask(MealsDao mealsDao)
        {
            this.mealsDao = mealsDao;
        }

        @Override
        protected Void doInBackground(Meals... meals)
        {
            mealsDao.delete(meals[0]);
            return null;
        }
    }

    private static class GetMealsForDateAsyncTask extends AsyncTask<String, Void, LiveData<List<Meals>>>
    {
        private MealsDao mealsDao;

        private GetMealsForDateAsyncTask(MealsDao mealsDao)
        {
            this.mealsDao = mealsDao;
        }

        @Override
        protected LiveData<List<Meals>> doInBackground(String... strings)
        {
            return mealsDao.getAllMealsForDate(strings[0]);
        }
    }

    public MealsList getInformationFromAPI(String foodName)
    {
        apiEndpoint = ServiceGenerator.getApiEndpoint();
        try
        {
            return new getMealInfoAsyncTask(apiEndpoint).execute((new ApiBody(foodName))).get();
        }
        catch (ExecutionException | InterruptedException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static class getMealInfoAsyncTask extends AsyncTask<ApiBody,Void,MealsList>
    {
        private ApiEndpoint asyncTaskApi;

        private getMealInfoAsyncTask(ApiEndpoint apiEndpoint)
        {
            asyncTaskApi = apiEndpoint;
        }

        @Override
        protected MealsList doInBackground(ApiBody... apiBodies)
        {
            Call<MealsList> call = asyncTaskApi.getFoodInformation(apiBodies[0]);
            Response<MealsList> response;
            try
            {
                response = call.execute();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
            return response.body();
        }
    }
}
