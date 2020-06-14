package com.example.and_project.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.example.and_project.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    CalendarView mCalendarView;
    String pickedDate;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.calendar_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pickedDate = sdf.format(Calendar.getInstance().getTime());

        mCalendarView = findViewById(R.id.calendar_view);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                month += 1;
                if(dayOfMonth < 10)
                {
                    pickedDate = "0" + dayOfMonth + "/";
                }
                else
                {
                    pickedDate = dayOfMonth + "/";
                }

                if(month < 10)
                {
                    pickedDate += "0" + month + "/";
                }
                else
                {
                    pickedDate += month + "/";
                }

                pickedDate += year;
                System.out.println(pickedDate);
            }
        });
    }

    public void getDataForDate(View view)
    {
        Intent intent = new Intent(this, HistoricalDataActivity.class);
        intent.putExtra("DATE", pickedDate);
        startActivity(intent);
    }
}
