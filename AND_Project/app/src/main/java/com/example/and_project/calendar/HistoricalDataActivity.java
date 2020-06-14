package com.example.and_project.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.and_project.R;
import com.example.and_project.database.MealsRepository;
import com.example.and_project.domain.Meals;

import java.util.ArrayList;
import java.util.List;

public class HistoricalDataActivity extends AppCompatActivity
{
    private RecyclerView mMealsList;
    private HistoricalDataListAdapter mHistoricalDataListAdapter;
    private ArrayList<Meals> items = new ArrayList<>();
    private MealsRepository repository;

    TextView mPickedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_data);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.historical_data_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPickedDate = findViewById(R.id.data_for_date);
        mMealsList = findViewById(R.id.historical_data_rv);
        mMealsList.hasFixedSize();
        mMealsList.setLayoutManager(new LinearLayoutManager(this));

        mHistoricalDataListAdapter = new HistoricalDataListAdapter(items);
        mMealsList.setAdapter(mHistoricalDataListAdapter);

        repository = MealsRepository.getInstance(getApplication());

        Intent startIntent = getIntent();
        String date = startIntent.getStringExtra("DATE");
        mPickedDate.setText(date);

        List<Meals> meals =  repository.getMealsForDate(date);
        if(meals != null)
        {
            mHistoricalDataListAdapter.setMeals(meals);
        }
    }
}
