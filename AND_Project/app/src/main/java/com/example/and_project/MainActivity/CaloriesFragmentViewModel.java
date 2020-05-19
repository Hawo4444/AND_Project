package com.example.and_project.mainActivity;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CaloriesFragmentViewModel extends ViewModel
{
    private ArrayList<RecyclerViewItem> items;

    public CaloriesFragmentViewModel()
    {
        items = new ArrayList<>();
    }

    public ArrayList<RecyclerViewItem> getItems()
    {
        return items;
    }

    public void addMeal(RecyclerView view)
    {

    }
}
