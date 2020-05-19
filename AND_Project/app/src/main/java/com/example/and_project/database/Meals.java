package com.example.and_project.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Meals
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;

    private String meal;

    private int calories;

    private String mealType;

    public Meals(String date, String meal, int calories, String mealType)
    {
        this.date = date;
        this.meal = meal;
        this.calories = calories;
        this.mealType = mealType;
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

    public String getMealType()
    {
        return mealType;
    }
}
