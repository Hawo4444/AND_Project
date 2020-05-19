package com.example.and_project.stepCounterActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.and_project.database.StepsRepository;
import com.example.and_project.mainActivity.MainActivity;

import static androidx.core.app.ActivityCompat.requestPermissions;

public class StepsService extends Service implements SensorEventListener
{
    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    //private StepsDBHelper mStepsDBHelper;
    private StepsRepository repository;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            System.out.println(checkPermission(this));
            mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
            System.out.println(mStepDetectorSensor);
        }
        else
        {
            Toast.makeText(this, "No step detector available", Toast.LENGTH_SHORT).show();
        }

        repository = new StepsRepository(getApplication());
    }

    public static boolean checkPermission(final Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED;
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
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        //mStepsDBHelper.createStepsEntry();
        repository.createStepsEntry();
        System.out.println("Step");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}