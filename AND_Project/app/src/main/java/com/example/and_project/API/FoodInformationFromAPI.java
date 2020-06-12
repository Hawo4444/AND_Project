package com.example.and_project.api;

import com.google.gson.annotations.SerializedName;

public class FoodInformationFromAPI
{
    @SerializedName("food_name")
    public String foodName;
    @SerializedName("serving_qty")
    public double servingQuantity;
    @SerializedName("serving_unit")
    public String servingUnit;
    @SerializedName("nf_calories")
    public double calories;
    @SerializedName("nf_total_fat")
    public double fat;
    @SerializedName("nf_total_carbohydrate")
    public double carbohydrate;
    @SerializedName("nf_total_protein")
    public double protein;

    public FoodInformationFromAPI(String foodName, double servingQuantity, String servingUnit, double calories, double fat, double carbohydrate, double protein)
    {
        this.foodName = foodName;
        this.servingQuantity = servingQuantity;
        this.servingUnit = servingUnit;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
    }
}
