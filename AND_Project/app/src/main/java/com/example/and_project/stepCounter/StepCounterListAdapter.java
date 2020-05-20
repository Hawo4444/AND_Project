package com.example.and_project.stepCounter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_project.R;
import com.example.and_project.database.Steps;

import java.util.ArrayList;
import java.util.List;

public class StepCounterListAdapter extends RecyclerView.Adapter<StepCounterListAdapter.ViewHolder>
{
    private List<Steps> rvItemsList;

    public StepCounterListAdapter(ArrayList rvItemsList)
    {
        this.rvItemsList = rvItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_steps_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.date.setText(rvItemsList.get(position).getDate());
        holder.amount.setText(String.valueOf(rvItemsList.get(position).getSteps()));
    }

    @Override
    public int getItemCount()
    {
        return rvItemsList.size();
    }

    public void setSteps(List<Steps> steps)
    {
        rvItemsList = steps;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView date;
        TextView amount;

        ViewHolder(View itemView)
        {
            super((itemView));
            date = itemView.findViewById(R.id.rv_steps_date);
            amount = itemView.findViewById(R.id.rv_steps_amount);
        }
    }
}
