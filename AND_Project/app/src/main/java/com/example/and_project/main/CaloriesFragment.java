package com.example.and_project.main;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.and_project.R;
import com.example.and_project.database.Meals;

import java.util.ArrayList;
import java.util.List;

public class CaloriesFragment extends Fragment
{
    private RecyclerView mCaloriesList;
    private CaloriesListAdapter mCaloriesListAdapter;
    private ArrayList<Meals> items = new ArrayList<>();
    private CaloriesFragmentViewModel caloriesFragmentViewModel;

    public CaloriesFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calories, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mCaloriesList = view.findViewById(R.id.calories_rv);
        mCaloriesList.hasFixedSize();
        mCaloriesList.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mCaloriesList.getContext(), layoutManager.getOrientation());
        mCaloriesList.addItemDecoration(mDividerItemDecoration);

        mCaloriesListAdapter = new CaloriesListAdapter(items);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mCaloriesList);
        mCaloriesList.setAdapter(mCaloriesListAdapter);

        caloriesFragmentViewModel = new ViewModelProvider(this).get(CaloriesFragmentViewModel.class);
        caloriesFragmentViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Meals>>()
        {
            @Override
            public void onChanged(List<Meals> meals)
            {
                mCaloriesListAdapter.setCaloriesPageList(meals);
            }
        });

        return view;
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT)
    {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
        {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            items.remove(viewHolder);
            mCaloriesListAdapter.notifyDataSetChanged();
        }
    };
}
