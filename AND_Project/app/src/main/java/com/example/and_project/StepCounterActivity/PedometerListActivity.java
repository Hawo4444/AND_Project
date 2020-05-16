package com.example.and_project.stepCounterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.and_project.R;

import java.util.ArrayList;

public class PedometerListActivity extends AppCompatActivity
{
    private ListView mSensorListView;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        mSensorListView = (ListView) findViewById(R.id.steps_list);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.calendar_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getDataForList();

        mListAdapter = new ListAdapter();
        mSensorListView.setAdapter(mListAdapter);

        Intent mStepsIntent = new Intent(getApplicationContext(), StepsService.class);
        startService(mStepsIntent);
    }

    static class DateStepsModel
    {
        public String mDate;
        public int mStepCount;
    }

    private StepsDBHelper mStepsDBHelper;
    private ArrayList mStepCountList;

    public void getDataForList()
    {
        mStepsDBHelper = new StepsDBHelper(this);
        mStepCountList = mStepsDBHelper.readStepsEntries();
    }

    private class ListAdapter extends BaseAdapter
    {
        private TextView mDateStepCountText;

        @Override
        public int getCount()
        {
            return mStepCountList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return mStepCountList.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.list_rows, parent, false);
            }
            mDateStepCountText = (TextView) convertView.findViewById(R.id.sensor_name);
            mDateStepCountText.setText(((DateStepsModel) mStepCountList.get(position)).mDate + " - Total Steps: " + String.valueOf(((DateStepsModel) mStepCountList.get(position)).mStepCount));

            return convertView;
        }
    }
}