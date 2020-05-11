package com.example.and_project.MainActivity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.and_project.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CaloriesFragment extends Fragment
{

    RecyclerView caloriesList;
    CaloriesListAdapter caloriesListAdapter;

    public CaloriesFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calories, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()); //check argument

        caloriesList = view.findViewById(R.id.calories_rv);
        caloriesList.hasFixedSize();
        caloriesList.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(caloriesList.getContext(),
                layoutManager.getOrientation());
        caloriesList.addItemDecoration(mDividerItemDecoration);

        ArrayList<RecyclerViewItem> items = new ArrayList<>();
        items.add(new Header("Breakfast"));
        items.add(new AddButton());
        items.add(new Header("Lunch"));
        items.add(new AddButton());
        items.add(new Header("Dinner"));
        items.add(new AddButton());
        items.add(new Header("Snacks"));
        items.add(new AddButton());

        caloriesListAdapter = new CaloriesListAdapter(items);
        caloriesList.setAdapter(caloriesListAdapter);

        return view;
    }

}
