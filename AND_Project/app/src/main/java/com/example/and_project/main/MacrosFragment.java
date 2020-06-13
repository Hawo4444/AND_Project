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
import com.example.and_project.domain.Meals;

import java.util.List;

public class MacrosFragment extends Fragment
{
    private SharedViewModel sharedViewModel;
    TextView calories;
    TextView carbs;
    TextView fats;
    TextView proteins;

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Meals>>()
        {
            int caloriesAmount, carbsAmount, fatsAmount, proteinsAmount = 0;
            @Override
            public void onChanged(List<Meals> meals)
            {
                caloriesAmount = 0;
                carbsAmount = 0;
                fatsAmount = 0;
                proteinsAmount = 0;
                for(int i = 0; i < meals.size(); i++)
                {
                    Meals meal = meals.get(i);
                    caloriesAmount += meal.calories;
                    carbsAmount += meal.carbohydrate;
                    fatsAmount += meal.fat;
                    proteinsAmount += meal.protein;
                }
                calories.setText(String.valueOf(caloriesAmount));
                carbs.setText(String.valueOf(carbsAmount));
                fats.setText(String.valueOf(fatsAmount));
                proteins.setText(String.valueOf(proteinsAmount));
            }
        });
    }

}
