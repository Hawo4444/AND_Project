package com.example.and_project.goals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.and_project.R;

import java.util.ArrayList;

public class GoalsActivity extends AppCompatActivity
{
    private GoalsViewModel goalsViewModel;

    private TextView goalWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.goals_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        goalWeight = findViewById(R.id.goal_weight_goals);

        goalsViewModel = new ViewModelProvider(this).get(GoalsViewModel.class);
        goalsViewModel.getGoals().observe(this, new Observer<ArrayList<Integer>>() {
            @Override
            public void onChanged(ArrayList<Integer> integers)
            {
                goalWeight.setText(integers.get(0).toString());
            }
        });
    }

    public void confirmGoals(View view)
    {
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        prefs.edit().putString("profileName", "something here").apply();
    }
}
