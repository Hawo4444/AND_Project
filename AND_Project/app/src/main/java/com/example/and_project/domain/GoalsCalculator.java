package com.example.and_project.domain;

public class GoalsCalculator {
    public static Goals calculateGoals(int currentWeight, int goalWeight) {
        int goalCalories = getCaloriesGoal(currentWeight);
        if (goalWeight > currentWeight) {
            goalCalories += 300;
        } else if (goalWeight < currentWeight) {
            goalCalories -= 300;
        }
        int goalCarbs = getCarbsGoal(goalCalories);
        int goalFats = getFatsGoal(goalCalories);
        int goalProtein = getProteinsGoal(goalCalories);
        return new Goals(1, goalWeight, goalCalories, goalCarbs, goalFats, goalProtein); //id is 1 because only one set of goals needs to be stored in the database at a time
    }

    private static int getCaloriesGoal(int currentWeight)
    {
        return (int) (13.75 * currentWeight + 5 * 180 - 100);
    }

    private static int getCarbsGoal(int goalCalories)
    {
        return ((int) (goalCalories * 0.5 / 4));
    }
    
    private static int getFatsGoal(int goalCalories)
    {
        return ((int) (goalCalories * 0.3 / 9));
    }

    private static int getProteinsGoal(int goalCalories)
    {
        return ((int) (goalCalories * 0.2 / 4));
    }
}
