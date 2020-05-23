package com.example.and_project.main;

public class GoalsCalculator
{
    public static int[] calculateGoals(int currentWeight, int goalWeight)
    {
        int[] goals = new int[3];
        int goalCalories = getCaloriesGoal(currentWeight);
        if (goalWeight > currentWeight)
        {
            goalCalories+=300;
        }
        else if (goalWeight < currentWeight)
        {
            goalCalories-=300;
        }
        goals[0] = goalCalories;
        goals[1] = (int) (goalCalories*0.5/4);
        goals[2] = (int) (goalCalories*0.3/9);
        goals[3] = (int) (goalCalories*0.2/4);
        return goals;
    }

    private static int getCaloriesGoal(int currentWeight)
    {
        return (int) (13.75 * currentWeight + 5 * 180 - 100);
    }
}
