package com.example.and_project.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_project.R;
import com.example.and_project.database.Meals;

import java.util.ArrayList;
import java.util.List;

public class HistoricalDataListAdapter extends RecyclerView.Adapter<HistoricalDataListAdapter.ViewHolder>
{
    private List<Meals> rvMealsList;

    public HistoricalDataListAdapter(ArrayList rvMealsList)
    {
        this.rvMealsList = rvMealsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.meal.setText(rvMealsList.get(position).getMeal());
        holder.calories.setText(String.valueOf(rvMealsList.get(position).getCalories()));
    }

    @Override
    public int getItemCount()
    {
        return rvMealsList.size();
    }

    public void setSteps(List<Meals> steps)
    {
        rvMealsList = steps;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView meal;
        TextView calories;

        ViewHolder(View itemView)
        {
            super((itemView));
            meal = itemView.findViewById(R.id.rv_meal_name);
            calories = itemView.findViewById(R.id.rv_meal_calories);
        }
    }
}
