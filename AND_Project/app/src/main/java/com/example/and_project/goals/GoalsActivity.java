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
import android.widget.Toast;

import com.example.and_project.R;
import com.example.and_project.database.Goals;

public class GoalsActivity extends AppCompatActivity
{
    private GoalsViewModel goalsViewModel;

    private TextView goalWeight;
    private TextView goalCalories;
    private TextView goalCarbs;
    private TextView goalFats;
    private TextView goalProteins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.goals_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        goalWeight = findViewById(R.id.goal_weight_goals);
        goalCalories = findViewById(R.id.goal_calories_goals);
        goalCarbs = findViewById(R.id.goal_carbs_goals);
        goalFats = findViewById(R.id.goal_fats_goals);
        goalProteins = findViewById(R.id.goal_proteins_goals);

        goalsViewModel = new ViewModelProvider(this).get(GoalsViewModel.class);
        goalsViewModel.getGoals().observe(this, new Observer<Goals>()
        {
            @Override
            public void onChanged(Goals goals)
            {
                if (goals != null)
                {
                    goalWeight.setText(String.valueOf(goals.getGoalWeight()));
                    goalCalories.setText(String.valueOf(goals.getGoalCalories()));
                    goalCarbs.setText(String.valueOf(goals.getGoalCarbs()));
                    goalFats.setText(String.valueOf(goals.getGoalFats()));
                    goalProteins.setText(String.valueOf(goals.getGoalProtein()));
                }
                else
                {
                    Toast.makeText(getApplication(), "No goals set yet!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void confirmGoals(View view)
    {
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        prefs.edit().putString("profileName", "something here").apply();
    }
}
