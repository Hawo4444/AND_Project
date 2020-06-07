package com.example.and_project.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Meals
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String date;

    @NonNull
    private String meal;

    //@SerializedName("150")
    private int calories;

    private int carbohydrates;

    private int fats;

    private int proteins;

    public Meals(String date, String meal, int calories, int carbohydrates, int fats, int proteins)
    {
        this.date = date;
        this.meal = meal;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.proteins = proteins;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getDate()
    {
        return date;
    }

    public String getMeal()
    {
        return meal;
    }

    public int getCalories()
    {
        return calories;
    }

    public int getCarbohydrates()
    {
        return carbohydrates;
    }

    public int getFats()
    {
        return fats;
    }

    public int getProteins()
    {
        return proteins;
    }
}
