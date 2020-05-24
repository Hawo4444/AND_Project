package com.example.and_project.main;

import java.util.ArrayList;

public class GoalsCalculator
{
    public static ArrayList<Integer> calculateGoals(int currentWeight, int goalWeight)
    {
        ArrayList<Integer> goals = new ArrayList<>();
        int goalCalories = getCaloriesGoal(currentWeight);
        if (goalWeight > currentWeight)
        {
            goalCalories+=300;
        }
        else if (goalWeight < currentWeight)
        {
            goalCalories-=300;
        }
        goals.add(goalCalories);
        goals.add((int) (goalCalories*0.5/4));
        goals.add((int) (goalCalories*0.3/9));
        goals.add((int) (goalCalories*0.2/4));
        return goals;
    }

    private static int getCaloriesGoal(int currentWeight)
    {
        return (int) (13.75 * currentWeight + 5 * 180 - 100);
    }
}
