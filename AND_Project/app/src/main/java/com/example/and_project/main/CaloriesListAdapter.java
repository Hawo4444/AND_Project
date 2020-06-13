package com.example.and_project.main;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_project.R;
import com.example.and_project.domain.Meals;

import java.util.ArrayList;
import java.util.List;

public class CaloriesListAdapter extends RecyclerView.Adapter<CaloriesListAdapter.ViewHolder>
{
    private List<Meals> rvItemsList;

    public CaloriesListAdapter(ArrayList rvItemsList)
    {
        this.rvItemsList = rvItemsList;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaloriesListAdapter.ViewHolder holder, int position)
    {
        holder.mealName.setText(rvItemsList.get(position).getFoodName());
        holder.mealAmount.setText((rvItemsList.get(position).getGramsQuantity() + "g"));
        holder.mealCalories.setText((Math.round(rvItemsList.get(position).getCalories()) + " kcal"));
    }

    public int getItemCount()
    {
        return rvItemsList.size();
    }

    public void setCaloriesPageList(List<Meals> caloriesList)
    {
        rvItemsList = caloriesList;
        notifyDataSetChanged();
    }

    public Meals getMealAt(int position)
    {
        return rvItemsList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView mealName;
        TextView mealAmount;
        TextView mealCalories;


        ViewHolder(View itemView)
        {
            super(itemView);

            mealName = itemView.findViewById(R.id.rv_meal_name);
            mealAmount = itemView.findViewById(R.id.rv_meal_amount);
            mealCalories = itemView.findViewById(R.id.rv_meal_calories);
        }
    }
}

