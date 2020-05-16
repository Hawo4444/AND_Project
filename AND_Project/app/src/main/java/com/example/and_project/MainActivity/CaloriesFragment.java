package com.example.and_project.mainActivity;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
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
public class CaloriesFragment extends Fragment implements CaloriesListAdapter.OnAddListener
{
    RecyclerView mCaloriesList;
    CaloriesListAdapter mCaloriesListAdapter;
    ArrayList<RecyclerViewItem> items = new ArrayList<>();;

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

        mCaloriesList = view.findViewById(R.id.calories_rv);
        mCaloriesList.hasFixedSize();
        mCaloriesList.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mCaloriesList.getContext(),
                layoutManager.getOrientation());
        mCaloriesList.addItemDecoration(mDividerItemDecoration);

        items.add(new Header("Breakfast"));
        items.add(new MealDetails("Chicken", 150));
        items.add(new AddButton());
        items.add(new Header("Lunch"));
        items.add(new AddButton());
        items.add(new Header("Dinner"));
        items.add(new AddButton());
        items.add(new Header("Snacks"));
        items.add(new AddButton());

        mCaloriesListAdapter = new CaloriesListAdapter(items, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mCaloriesList);
        mCaloriesList.setAdapter(mCaloriesListAdapter);

        return view;
    }

    @Override
    public void onAddClick(int position)
    {
        items.get(position);
        Intent intent = new Intent(getContext(), AddMealActivity.class); //to be created
        startActivity(intent); //use position?
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
            if (viewHolder instanceof CaloriesListAdapter.MealViewHolder)
            {
                items.remove(viewHolder);
                mCaloriesListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public int 	getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof CaloriesListAdapter.HeaderViewHolder || viewHolder instanceof CaloriesListAdapter.AddButtonViewHolder) return 0;
            return super.getSwipeDirs(recyclerView, viewHolder);
        }
    };
}
