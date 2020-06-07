package com.example.and_project.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class GoalsRepository
{
    private static GoalsRepository instance;
    private GoalsDao goalsDao;
    private LiveData<Goals> goals;

    private GoalsRepository(Application application)
    {
        FeednessDatabase database = FeednessDatabase.getInstance(application);
        goalsDao = database.goalsDao();
        goals = goalsDao.getGoals();
    }

    public static GoalsRepository getInstance(Application application)
    {
        if(instance == null)
        {
            instance = new GoalsRepository(application);
        }
        return instance;
    }

    public void insert(Goals goals)
    {
        new InsertGoalsAsyncTask(goalsDao).execute(goals);
    }

    public LiveData<Goals> getGoals()
    {
        return goals;
    }

    private static class InsertGoalsAsyncTask extends AsyncTask<Goals, Void, Void>
    {
        private GoalsDao goalsDao;

        private InsertGoalsAsyncTask(GoalsDao goalsDao)
        {
            this.goalsDao = goalsDao;
        }

        @Override
        protected Void doInBackground(Goals... goals)
        {
            goalsDao.insert(goals[0]);
            return null;
        }
    }
}
