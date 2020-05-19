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

    public void insert()
    {
        Calendar mCalendar = Calendar.getInstance();
        String date = String.valueOf(mCalendar.get(Calendar.MONTH)) + "/" + String.valueOf(mCalendar.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(mCalendar.get(Calendar.YEAR));
        new InsertStepsAsyncTask(stepsDao).execute(new Steps(date, 1));
    }

    public void update(Steps steps)
    {
        new UpdateStepsAsyncTask(stepsDao).execute(steps);
    }

    public LiveData<List<Steps>> getAllStepsEntries()
    {
        return allStepsEntries;
    }

    public Steps getStepsForDate()
    {
        Calendar mCalendar = Calendar.getInstance();
        String date = String.valueOf(mCalendar.get(Calendar.MONTH)) + "/" + String.valueOf(mCalendar.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(mCalendar.get(Calendar.YEAR));
        return stepsDao.getStepsForDate(date);
    }

    public void createStepsEntry()
    {
        try
        {
            Steps currentSteps = getStepsForDate();
            if(currentSteps != null)
            {
                currentSteps.setSteps(currentSteps.getSteps()+1);
                update(currentSteps);
                Log.d("count", currentSteps.getSteps()+"");
            }
            else
            {
                insert();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

    private static class GetStepsForDateAsyncTask extends AsyncTask<String, Void, Steps>
    {
        private StepsDao stepsDao;

        private GetStepsForDateAsyncTask(StepsDao stepsDao)
        {
            this.stepsDao = stepsDao;
        }

        @Override
        protected Steps doInBackground(String... strings)
        {
            stepsDao.getStepsForDate(strings[0]);
            return null;
        }
    }
}
