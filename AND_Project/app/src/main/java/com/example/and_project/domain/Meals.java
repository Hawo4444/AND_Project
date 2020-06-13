package com.example.and_project.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Meals
{
    @SerializedName("food_name")
    public String foodName;
    @SerializedName("serving_weight_grams")
    public double gramsQuantity;
    @SerializedName("nf_calories")
    public double calories;
    @SerializedName("nf_total_fat")
    public double fat;
    @SerializedName("nf_total_carbohydrate")
    public double carbohydrate;
    @SerializedName("nf_protein")
    public double protein;
    @Expose
    @PrimaryKey
    @NonNull
    public String date;

    public Meals(String foodName, double gramsQuantity, double calories, double fat, double carbohydrate, double protein)
    {
        this.foodName = foodName;
        this.gramsQuantity = gramsQuantity;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public double getGramsQuantity()
    {
        return gramsQuantity;
    }

    public double getCalories()
    {
        return calories;
    }

    public double getFat()
    {
        return fat;
    }

    public double getCarbohydrate()
    {
        return carbohydrate;
    }

    public double getProtein()
    {
        return protein;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(@NonNull String date)
    {
        this.date = date;
    }
}
