package com.example.and_project.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.and_project.api.ApiEndpoint;
import com.example.and_project.api.FoodInformationFromAPI;
import com.example.and_project.api.ServiceGenerator;
import com.example.and_project.domain.ApiBody;
import com.google.gson.Gson;

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

    public void insertMeal(Meals details)
    {

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

    public FoodInformationFromAPI getInformationFromAPI(String foodName)
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

    public class getMealInfoAsyncTask extends AsyncTask<ApiBody,Void,FoodInformationFromAPI>
    {
        private ApiEndpoint asyncTaskApi;

        private getMealInfoAsyncTask(ApiEndpoint apiEndpoint)
        {
            asyncTaskApi = apiEndpoint;
        }

        @Override
        protected FoodInformationFromAPI doInBackground(ApiBody... apiBodies)
        {
            Call<FoodInformationFromAPI> call = asyncTaskApi.getFoodInformation(apiBodies[0]);
            Log.i("REQUEST URL",call.request().url().toString());
            Log.i("REQUEST BODY",String.valueOf(call.request().body()));
            Log.i("REQUEST BODY",String.valueOf(call.request().headers().toString()));
            Response<FoodInformationFromAPI> response;
            try
            {
                response = call.execute();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
            Log.i("RESPONSE STATUS CODE",String.valueOf(response.code()));
            Log.i("RESPONSE BODY",String.valueOf(response.raw().body().toString()));
            Log.i("RESPONSE REQUEST",String.valueOf(response.raw().request().toString()));

            return response.body();
        }
    }
}
