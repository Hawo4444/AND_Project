package com.example.and_project.main;

public class MealDetails implements RecyclerViewItem
{
    private String mName;
    private int mCalories;

    MealDetails(String name, int calories)
    {
        mName = name;
        mCalories = calories;
    }

    public String getName()
    {
        return mName;
    }

    public int getCalories()
    {
        return mCalories;
    }

    @Override
    public int getType()
    {
        return RecyclerViewItem.TYPE_MEAL;
    }
}
