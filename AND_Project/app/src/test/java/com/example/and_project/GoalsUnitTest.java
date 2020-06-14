package com.example.and_project;

import com.example.and_project.domain.Goals;
import com.example.and_project.domain.GoalsCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoalsUnitTest
{
    private final int lowerWeight = 40;
    private final int higherWeight = 50;

    //Goals for gaining weight
    @Test
    public void caloriesGoalGainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(lowerWeight, higherWeight);
        assertEquals(1650, goals.getGoalCalories());
    }

    @Test
    public void carbsGoalGainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(lowerWeight, higherWeight);
        assertEquals(206, goals.getGoalCarbs());
    }

    @Test
    public void fatsGoalGainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(lowerWeight, higherWeight);
        assertEquals(55, goals.getGoalFats());
    }

    @Test
    public void proteinsGoalGainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(lowerWeight, higherWeight);
        assertEquals(82, goals.getGoalProtein());
    }

    //Goals for losing weight
    @Test
    public void caloriesGoalLoseWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, lowerWeight);
        assertEquals(1187, goals.getGoalCalories());
    }

    @Test
    public void carbsGoalLoseWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, lowerWeight);
        assertEquals(148, goals.getGoalCarbs());
    }

    @Test
    public void fatsGoalLoseWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, lowerWeight);
        assertEquals(39, goals.getGoalFats());
    }

    @Test
    public void proteinsGoalLoseWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, lowerWeight);
        assertEquals(59, goals.getGoalProtein());
    }

    //Goals for maintaining weight
    @Test
    public void caloriesGoalMaintainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, higherWeight);
        assertEquals(1487, goals.getGoalCalories());
    }

    @Test
    public void carbsGoalMaintainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, higherWeight);
        assertEquals(185, goals.getGoalCarbs());
    }

    @Test
    public void fatsGoalMaintainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, higherWeight);
        assertEquals(49, goals.getGoalFats());
    }

    @Test
    public void proteinsGoalMaintainWeightTest()
    {
        Goals goals = GoalsCalculator.calculateGoals(higherWeight, higherWeight);
        assertEquals(74, goals.getGoalProtein());
    }
}