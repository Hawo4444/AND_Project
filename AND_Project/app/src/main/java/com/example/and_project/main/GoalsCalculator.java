package com.example.and_project.main;

import com.example.and_project.database.Goals;

public class GoalsCalculator
{
    public static Goals calculateGoals(int currentWeight, int goalWeight)
    {
        int goalCalories = getCaloriesGoal(currentWeight);
        if (goalWeight > currentWeight)
        {
            goalCalories+=300;
        }
        else if (goalWeight < currentWeight)
        {
            goalCalories-=300;
        }
        int goalCarbs = ((int) (goalCalories*0.5/4));
        int goalFats = ((int) (goalCalories*0.3/9));
        int goalProtein = ((int) (goalCalories*0.2/4));
        return new Goals(1, goalWeight, goalCalories, goalCarbs, goalFats, goalProtein); //id is 1 because only one set of goals needs to be stored in the database at a time
    }

    private static int getCaloriesGoal(int currentWeight)
    {
        return (int) (13.75 * currentWeight + 5 * 180 - 100);
    }
}
