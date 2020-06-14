package com.example.and_project.main;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.and_project.R;
import com.example.and_project.domain.Goals;
import com.example.and_project.domain.Meals;

import java.util.List;

public class MacrosFragment extends Fragment
{
    private SharedViewModel sharedViewModel;
    TextView calories;
    TextView carbs;
    TextView fats;
    TextView proteins;

    TextView caloriesAmount;
    TextView carbsAmount;
    TextView fatsAmount;
    TextView proteinsAmount;

    public MacrosFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_macros, container, false);

        calories = view.findViewById(R.id.calories);
        carbs = view.findViewById(R.id.carb_macro);
        fats = view.findViewById(R.id.fat_macro);
        proteins = view.findViewById(R.id.protein_macro);

        caloriesAmount = view.findViewById(R.id.calories_amount);
        carbsAmount = view.findViewById(R.id.carb_macro_amount);
        fatsAmount = view.findViewById(R.id.fat_macro_amount);
        proteinsAmount = view.findViewById(R.id.protein_macro_amount);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Meals>>()
        {
            int caloriesVar, carbsVar, fatsVar, proteinsVar = 0;
            @Override
            public void onChanged(List<Meals> meals)
            {
                caloriesVar = 0;
                carbsVar = 0;
                fatsVar = 0;
                proteinsVar = 0;
                for(int i = 0; i < meals.size(); i++)
                {
                    Meals meal = meals.get(i);
                    caloriesVar += meal.calories;
                    carbsVar += meal.carbohydrate;
                    fatsVar += meal.fat;
                    proteinsVar += meal.protein;
                }
                calories.setText(String.valueOf(caloriesVar));
                carbs.setText(String.valueOf(carbsVar));
                fats.setText(String.valueOf(fatsVar));
                proteins.setText(String.valueOf(proteinsVar));
            }
        });

        sharedViewModel.getGoals().observe(getViewLifecycleOwner(), new Observer<Goals>()
        {
            @Override
            public void onChanged(Goals goals)
            {
                if (goals != null)
                {
                    caloriesAmount.setText(getString(R.string.dash_calories, goals.getGoalCalories()));
                    carbsAmount.setText(getString(R.string.dash_macros, goals.getGoalCarbs()));
                    fatsAmount.setText(getString(R.string.dash_macros, goals.getGoalFats()));
                    proteinsAmount.setText(getString(R.string.dash_macros, goals.getGoalProtein()));
                }
                else
                {
                    caloriesAmount.setText(R.string.empty_calories);
                    carbsAmount.setText(R.string.empty_macros);
                    fatsAmount.setText(R.string.empty_macros);
                    proteinsAmount.setText(R.string.empty_macros);
                }
            }
        });
    }

}
