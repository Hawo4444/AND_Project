package com.example.and_project.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Goals
{
    @PrimaryKey
    private int id;

    private int currentWeight;

    private int goalWeight;

    public Goals(int id, int currentWeight, int goalWeight)
    {
        this.id = id;
        this.currentWeight = currentWeight;
        this.goalWeight = goalWeight;
    }

    public int getCurrentWeight()
    {
        return currentWeight;
    }

    public int getGoalWeight()
    {
        return goalWeight;
    }

    public int getId()
    {
        return id;
    }
}
