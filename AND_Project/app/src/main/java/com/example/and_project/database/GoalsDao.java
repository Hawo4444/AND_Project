package com.example.and_project.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface GoalsDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Goals goals);

    @Query("SELECT * FROM Goals WHERE id=1")
    LiveData<Goals> getGoals();
}