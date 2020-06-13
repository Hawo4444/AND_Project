package com.example.and_project.main;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.and_project.R;
import com.example.and_project.domain.Meals;

import java.util.ArrayList;
import java.util.List;

public class CaloriesFragment extends Fragment
{
    private RecyclerView mCaloriesList;
    private CaloriesListAdapter mCaloriesListAdapter;
    private ArrayList<Meals> items = new ArrayList<>();
    private SharedViewModel sharedViewModel;

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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        sharedViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Meals>>()
        {
            @Override
            public void onChanged(List<Meals> meals)
            {
                mCaloriesListAdapter.setCaloriesPageList(meals);
            }
        });
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
            sharedViewModel.deleteMeal(mCaloriesListAdapter.getMealAt(viewHolder.getAdapterPosition()));
            Toast.makeText(getContext(), "Meal Deleted", Toast.LENGTH_SHORT).show();
            mCaloriesListAdapter.notifyDataSetChanged();
        }
    };
}
