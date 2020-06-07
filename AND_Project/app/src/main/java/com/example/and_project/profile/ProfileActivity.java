package com.example.and_project.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.and_project.R;
import com.example.and_project.database.Goals;
import com.example.and_project.database.GoalsRepository;
import com.example.and_project.database.Steps;
import com.example.and_project.main.MainActivity;

import java.util.List;

import static com.example.and_project.main.GoalsCalculator.calculateGoals;

public class ProfileActivity extends AppCompatActivity
{
    private EditText currentWeight;
    private EditText goalWeight;

    private GoalsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.profile_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentWeight = findViewById(R.id.current_weight);
        goalWeight = findViewById(R.id.goal_weight);

        repository = GoalsRepository.getInstance(getApplication());
    }

    public void confirmGoals(View view)
    {
        repository.insert(calculateGoals(Integer.parseInt(currentWeight.getText().toString()), Integer.parseInt(goalWeight.getText().toString())));
        Toast.makeText(this, "Goals confirmed!", Toast.LENGTH_SHORT).show();
    }
}
