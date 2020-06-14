package com.example.and_project.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.and_project.R;
import com.example.and_project.domain.Meals;
import com.example.and_project.database.MealsRepository;
import com.example.and_project.domain.MealsList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AddMealActivity extends AppCompatActivity
{
    EditText editText;
    TextView errorText;
    private MealsRepository repository;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");

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
        errorText = findViewById(R.id.error_text);
    }

    public void confirmMeal(View view)
    {
        MealsList mealsList = repository.getInformationFromAPI(editText.getText().toString());
        try
        {
            Meals meal = mealsList.getMeal(0);

            if(meal == null)
            {
                setErrorText();
            }
            else
            {
                Calendar cal = Calendar.getInstance();
                String date = sdf.format(cal.getTime());
                meal.setDate(date);
                clearErrorText();
                repository.insertMeal(meal);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
        catch (Exception e)
        {
            setErrorText();
            e.printStackTrace();
        }
    }

    private void setErrorText()
    {
        errorText.setText(R.string.add_meal_error);
    }

    private void clearErrorText()
    {
        errorText.setText("");
    }
}
