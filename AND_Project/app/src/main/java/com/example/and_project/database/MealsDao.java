package com.example.and_project.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealsDao
{
    @Insert
    void insert(Meals meals);

    @Delete
    void delete(Meals meals);

    @Query("SELECT * FROM Meals WHERE Date=:date")
    LiveData<List<Meals>> getAllMealsForDate(String date);
}