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
    private SharedViewModel mSharedViewModel;
    private TextView mCaloriesTextView;
    private TextView mCarbsTextView;
    private TextView mFatsTextView;
    private TextView mProteinsTextView;

    private TextView mCaloriesGoalTextView;
    private TextView mCarbsGoalTextView;
    private TextView mFatsGoalTextView;
    private TextView mProteinsGoalTextView;

    public MacrosFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_macros, container, false);

        mCaloriesTextView = view.findViewById(R.id.calories);
        mCarbsTextView = view.findViewById(R.id.carb_macro);
        mFatsTextView = view.findViewById(R.id.fat_macro);
        mProteinsTextView = view.findViewById(R.id.protein_macro);

        mCaloriesGoalTextView = view.findViewById(R.id.calories_amount);
        mCarbsGoalTextView = view.findViewById(R.id.carb_macro_amount);
        mFatsGoalTextView = view.findViewById(R.id.fat_macro_amount);
        mProteinsGoalTextView = view.findViewById(R.id.protein_macro_amount);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mSharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        mSharedViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Meals>>()
        {
            @Override
            public void onChanged(List<Meals> meals)
            {
                int caloriesAmount = (int) meals.stream().mapToDouble(Meals::getCalories).sum();
                mCaloriesTextView.setText(String.valueOf(caloriesAmount));

                int carbsAmount = (int) meals.stream().mapToDouble(Meals::getCarbohydrate).sum();
                mCarbsTextView.setText(String.valueOf(carbsAmount));

                int fatsAmount = (int) meals.stream().mapToDouble(Meals::getFat).sum();
                mFatsTextView.setText(String.valueOf(fatsAmount));

                int proteinsAmount = (int) meals.stream().mapToDouble(Meals::getProtein).sum();
                mProteinsTextView.setText(String.valueOf(proteinsAmount));
            }
        });

        mSharedViewModel.getGoals().observe(getViewLifecycleOwner(), new Observer<Goals>()
        {
            @Override
            public void onChanged(Goals goals)
            {
                if (goals != null)
                {
                    mCaloriesGoalTextView.setText(getString(R.string.dash_calories, goals.getGoalCalories()));
                    mCarbsGoalTextView.setText(getString(R.string.dash_macros, goals.getGoalCarbs()));
                    mFatsGoalTextView.setText(getString(R.string.dash_macros, goals.getGoalFats()));
                    mProteinsGoalTextView.setText(getString(R.string.dash_macros, goals.getGoalProtein()));
                }
                else
                {
                    mCaloriesGoalTextView.setText(R.string.empty_calories);
                    mCarbsGoalTextView.setText(R.string.empty_macros);
                    mFatsGoalTextView.setText(R.string.empty_macros);
                    mProteinsGoalTextView.setText(R.string.empty_macros);
                }
            }
        });
    }

}
