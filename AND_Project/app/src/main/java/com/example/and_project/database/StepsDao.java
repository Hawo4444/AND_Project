package com.example.and_project.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StepsDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Steps steps);

    @Update
    void update(Steps steps);

    @Query("SELECT * FROM Steps")
    LiveData<List<Steps>> getAllStepsEntries();

    @Query("SELECT * FROM Steps WHERE Date=:date")
    Steps getStepsForDate(String date);
}
