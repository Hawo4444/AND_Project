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

import java.util.ArrayList;
import java.util.List;

public class CaloriesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<? extends RecyclerViewItem> rvItemsList;
    private OnAddListener mOnAddListener;

    public CaloriesListAdapter(ArrayList rvItemsList, OnAddListener onAddListener)
    {
        mOnAddListener = onAddListener;
        this.rvItemsList = rvItemsList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType)
        {
            case (RecyclerViewItem.TYPE_HEADER):
                view = inflater.inflate(R.layout.rv_item_meal_header, parent, false);
                return new HeaderViewHolder(view);
            case (RecyclerViewItem.TYPE_ADD_BUTTON):
                view = inflater.inflate(R.layout.rv_item_add_meal, parent, false);
                return new AddButtonViewHolder(view, mOnAddListener);
            case (RecyclerViewItem.TYPE_MEAL):
                view = inflater.inflate(R.layout.rv_item_meal, parent, false);
                return new MealViewHolder(view);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        switch (getItemViewType(position))
        {
            case (RecyclerViewItem.TYPE_HEADER):
                ((HeaderViewHolder) holder).bindView(position);
                break;
            case (RecyclerViewItem.TYPE_ADD_BUTTON):
                ((AddButtonViewHolder) holder).bindView(position);
                break;
            case (RecyclerViewItem.TYPE_MEAL):
                ((MealViewHolder) holder).bindView(position);
                break;
        }
    }

    public int getItemCount()
    {
        return rvItemsList.size();
    }

    public void setCaloriesPageList(List<? extends RecyclerViewItem> caloriesList)
    {
        rvItemsList = caloriesList;
        notifyDataSetChanged();
    }

    //View holders for the different items
    @Override
    public int getItemViewType(int position)
    {
        return rvItemsList.get(position).getType();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder
    {
        TextView mealHeader;

        HeaderViewHolder(View itemView)
        {
            super(itemView);

            mealHeader = itemView.findViewById(R.id.rv_meal_header);
        }

        void bindView(int position)
        {
            Header header = (Header) rvItemsList.get(position);
            mealHeader.setText(header.getName());
        }
    }

    class AddButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnAddListener onAddListener;

        AddButtonViewHolder(View itemView, OnAddListener onAddListener)
        {
            super(itemView);
            this.onAddListener = onAddListener;
            itemView.setOnClickListener(this);
        }

        void bindView(int position)
        {
            AddButton addButton = (AddButton) rvItemsList.get(position);
        }

        @Override
        public void onClick(View v)
        {
            mOnAddListener.onAddClick(getAdapterPosition());
        }
    }

    public interface OnAddListener
    {
        void onAddClick(int position);
    }

    class MealViewHolder extends RecyclerView.ViewHolder implements View.OnDragListener
    {
        TextView mealName;
        TextView mealCalories;

        MealViewHolder(View itemView)
        {
            super(itemView);

            mealName = itemView.findViewById(R.id.rv_meal_name);
            mealCalories = itemView.findViewById(R.id.rv_meal_calories);
        }

        void bindView(int position)
        {
            MealDetails mealDetails = (MealDetails) rvItemsList.get(position);
            mealName.setText(mealDetails.getName());
            mealCalories.setText(String.valueOf(mealDetails.getCalories()));
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

