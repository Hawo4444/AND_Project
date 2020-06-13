package com.example.and_project.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.example.and_project.R;

public class CalendarActivity extends AppCompatActivity {

    CalendarView mCalendarView;
    String pickedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.calendar_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCalendarView = findViewById(R.id.calendar_view);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                pickedDate = dayOfMonth + "/" + month + 1 + "/" + year; //month +1
            }
        });
    }

    public void getDataForDate(View view)
    {
        //intent to start a new activity showcasing historical data from the database
        //System.out.println(getTimeStamp());
        Intent intent = new Intent(this, HistoricalDataActivity.class); //put extras - date
        startActivity(intent);
    }
}
