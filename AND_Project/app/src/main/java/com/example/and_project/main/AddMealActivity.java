package com.example.and_project.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.and_project.R;
import com.example.and_project.api.FoodInformationFromAPI;
import com.example.and_project.database.MealsRepository;

public class AddMealActivity extends AppCompatActivity
{
    EditText editText;
    private MealsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_meal_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repository = MealsRepository.getInstance(getApplication());
        editText = findViewById(R.id.new_meal_name);
    }

    public void confirmMeal(View view)
    {
        repository.getInformationFromAPI(editText.getText().toString());
    }
}
