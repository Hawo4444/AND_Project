package com.example.and_project.stepCounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_project.R;
import com.example.and_project.domain.Steps;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataReadRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepCounterActivity extends AppCompatActivity
{
    private RecyclerView mStepsList;
    private StepCounterListAdapter mStepCounterListAdapter;
    private ArrayList<Steps> items = new ArrayList<>();
    private StepCounterViewModel stepCounterViewModel;

    private static final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1;
    private static final String TAG = "Permissions";
    FitnessOptions fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);

        if (!GoogleSignIn.hasPermissions(account, fitnessOptions))
        {
            GoogleSignIn.requestPermissions(this, GOOGLE_FIT_PERMISSIONS_REQUEST_CODE, account, fitnessOptions);
        }
        else
        {
            accessGoogleFit();
            Intent mStepsIntent = new Intent(getApplicationContext(), StepsService.class);
            startService(mStepsIntent);
        }

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.step_counter_activity_header);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStepsList = findViewById(R.id.steps_list);
        mStepsList.hasFixedSize();
        mStepsList.setLayoutManager(new LinearLayoutManager(this));

        mStepCounterListAdapter = new StepCounterListAdapter(items);
        mStepsList.setAdapter(mStepCounterListAdapter);

        stepCounterViewModel = new ViewModelProvider(this).get(StepCounterViewModel.class);
        stepCounterViewModel.getAllStepsEntries().observe(this, new Observer<List<Steps>>()
        {
            @Override
            public void onChanged(List<Steps> steps)
            {
                mStepCounterListAdapter.setSteps(steps);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE)
            {
                accessGoogleFit();
            }
        }
    }

    private void accessGoogleFit()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.YEAR, -1);
        long startTime = cal.getTimeInMillis();

        DataReadRequest readRequest = new DataReadRequest.Builder()
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                .bucketByTime(1, TimeUnit.DAYS)
                .build();

        GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);

        Fitness.getHistoryClient(this, account).readData(readRequest).addOnSuccessListener(response ->
                {
                    // Use response data here

                    Log.d(TAG, "OnSuccess()");
                })
                .addOnFailureListener(e ->
                {
                    Log.d(TAG, "OnFailure()", e);
                });
    }
}