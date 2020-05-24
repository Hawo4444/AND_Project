package com.example.and_project.main;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_project.R;
import com.example.and_project.database.Meals;

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
        holder.mealName.setText(rvItemsList.get(position).getMeal());
        holder.mealCalories.setText(String.valueOf(rvItemsList.get(position).getCalories()));
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnDragListener
    {
        TextView mealName;
        TextView mealCalories;

        ViewHolder(View itemView)
        {
            super(itemView);

            mealName = itemView.findViewById(R.id.rv_meal_name);
            mealCalories = itemView.findViewById(R.id.rv_meal_calories);
        }

        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            //remove view
            return false;
        }
    }

    public interface OnDragListener
    {
        void onMealDragged(int position);
    }
}

