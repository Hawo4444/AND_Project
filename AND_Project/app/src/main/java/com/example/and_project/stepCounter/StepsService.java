package com.example.and_project.stepCounter;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.and_project.database.Steps;
import com.example.and_project.database.StepsRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StepsService extends Service implements SensorEventListener
{
    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;

    private int stepsCountForToday;
    private String date;
    SimpleDateFormat formatter;

    private StepsRepository repository;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            Toast.makeText(this, "No Step Detector Available", Toast.LENGTH_SHORT).show();
        }

        date = checkDate();

        repository = StepsRepository.getInstance(getApplication());
        if(repository.getStepsForDate(date) == null)
        {
            stepsCountForToday = 0;
        }
        else
        {
            stepsCountForToday = repository.getStepsForDate(date).getSteps();
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
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        String dateNow = checkDate();

        if(date.equals(dateNow))
        {
            stepsCountForToday++;
            repository.insert(new Steps(date, stepsCountForToday));
        }
        else
        {
            date = dateNow;
            stepsCountForToday = 0;
            repository.insert(new Steps(date, stepsCountForToday));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        //Automatically generated method
    }

    private String checkDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return date = sdf.format(cal.getTime());
    }
}