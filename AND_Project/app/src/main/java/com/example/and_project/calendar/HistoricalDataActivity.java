package com.example.and_project.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.and_project.R;
import com.example.and_project.database.Steps;

import java.util.ArrayList;

public class HistoricalDataActivity extends AppCompatActivity
{
    private RecyclerView mMealsList;
    private HistoricalDataListAdapter mHistoricalDataListAdapter;
    private ArrayList<Steps> items = new ArrayList<>();
    private HistoricalDataViewModel historicalDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_data);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.step_counter_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMealsList = findViewById(R.id.steps_list);
        mMealsList.hasFixedSize();
        mMealsList.setLayoutManager(new LinearLayoutManager(this));

        mHistoricalDataListAdapter = new HistoricalDataListAdapter(items);
        mMealsList.setAdapter(mHistoricalDataListAdapter);

        historicalDataViewModel = new ViewModelProvider(this).get(HistoricalDataViewModel.class);
        //observe data
    }
}
