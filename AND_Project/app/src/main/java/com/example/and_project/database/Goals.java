package com.example.and_project.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Goals
{
    @PrimaryKey
    private int id;

    private int goalWeight;

    private int goalCalories;

    private int goalCarbs;

    private int goalFats;

    private int goalProtein;

    public Goals(int id, int goalWeight, int goalCalories, int goalCarbs, int goalFats, int goalProtein)
    {
        this.id = id;
        this.goalWeight = goalWeight;
        this.goalCalories = goalCalories;
        this.goalCarbs = goalCarbs;
        this.goalFats = goalFats;
        this.goalProtein = goalProtein;
    }

    public int getId() {
        return id;
    }

    public int getGoalWeight() {
        return goalWeight;
    }

    public int getGoalCalories() {
        return goalCalories;
    }

    public int getGoalCarbs() {
        return goalCarbs;
    }

    public int getGoalFats() {
        return goalFats;
    }

    public int getGoalProtein() {
        return goalProtein;
    }
}
