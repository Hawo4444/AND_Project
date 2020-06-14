package com.example.and_project.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_project.R;
import com.example.and_project.domain.Meals;

public class CaloriesListAdapter extends ListAdapter<Meals, CaloriesListAdapter.ViewHolder>
{
    public CaloriesListAdapter()
    {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Meals> DIFF_CALLBACK = new DiffUtil.ItemCallback<Meals>()
    {
        @Override
        public boolean areItemsTheSame(@NonNull Meals oldItem, @NonNull Meals newItem)
        {
            return oldItem.getDate().equals(newItem.getDate());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meals oldItem, @NonNull Meals newItem)
        {
            return oldItem.getFoodName().equals(newItem.getFoodName()) &&
                    oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getCalories() == newItem.getCalories() &&
                    oldItem.getCarbohydrate() == newItem.getCarbohydrate() &&
                    oldItem.getFat() == newItem.getFat() &&
                    oldItem.getProtein() == newItem.getProtein() &&
                    oldItem.getGramsQuantity() == newItem.getGramsQuantity();
        }
    };

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaloriesListAdapter.ViewHolder holder, int position)
    {
        holder.mealName.setText(getItem(position).getFoodName());
        holder.mealAmount.setText((getItem(position).getGramsQuantity() + "g"));
        holder.mealCalories.setText((Math.round(getItem(position).getCalories()) + " kcal"));
    }

    public Meals getMealAt(int position)
    {
        return getItem(position);
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

