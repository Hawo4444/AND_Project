package com.example.and_project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Meals.class, Steps.class}, version = 1)
public abstract class FeednessDatabase extends RoomDatabase
{
    private static FeednessDatabase INSTANCE;

    public abstract MealsDao mealsDao();

    public abstract StepsDao stepsDao();

    public static synchronized FeednessDatabase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FeednessDatabase.class, "feedness_database").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
