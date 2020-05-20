package com.example.and_project.database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Calendar;
import java.util.List;

public class StepsRepository
{
    private StepsDao stepsDao;
    private LiveData<List<Steps>> allStepsEntries;

    public StepsRepository(Application application)
    {
        FeednessDatabase database = FeednessDatabase.getInstance(application);
        stepsDao = database.stepsDao();
        allStepsEntries = stepsDao.getAllStepsEntries();
    }

    public void insert(Steps steps)
    {
        new InsertStepsAsyncTask(stepsDao).execute(steps);
    }

    public void update(Steps steps)
    {
        new UpdateStepsAsyncTask(stepsDao).execute(steps);
    }

    public Steps getStepsForDate(String date)
    {
        return stepsDao.getStepsForDate(date);
    }

    public LiveData<List<Steps>> getAllStepsEntries()
    {
        return allStepsEntries;
    }

    private static class InsertStepsAsyncTask extends AsyncTask<Steps, Void, Void>
    {
        private StepsDao stepsDao;

        private InsertStepsAsyncTask(StepsDao stepsDao)
        {
            this.stepsDao = stepsDao;
        }

        @Override
        protected Void doInBackground(Steps... steps)
        {
            stepsDao.insert(steps[0]);
            return null;
        }
    }

    private static class UpdateStepsAsyncTask extends AsyncTask<Steps, Void, Void>
    {
        private StepsDao stepsDao;

        private UpdateStepsAsyncTask(StepsDao stepsDao)
        {
            this.stepsDao = stepsDao;
        }

        @Override
        protected Void doInBackground(Steps... steps)
        {
            stepsDao.update(steps[0]);
            return null;
        }
    }

    /*private static class GetStepsForDateAsyncTask extends AsyncTask<String, Void, Steps>
    {
        private StepsDao stepsDao;

        private GetStepsForDateAsyncTask(StepsDao stepsDao)
        {
            this.stepsDao = stepsDao;
        }

        @Override
        protected Steps doInBackground(String... strings)
        {
            return stepsDao.getStepsForDate(strings[0]);
        }
    }*/
}
