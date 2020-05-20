package com.example.and_project.stepCounter;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class StepsService extends Service implements SensorEventListener
{
    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private StepCounterViewModel stepCounterViewModel;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
            System.out.println(mStepDetectorSensor);
        }
        else
        {
            Toast.makeText(this, "No step detector available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        System.out.println("Start sticky");
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Bundle bundle = intent.getExtras();
        stepCounterViewModel = bundle.getBinder();
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        StepCounterViewModel
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}