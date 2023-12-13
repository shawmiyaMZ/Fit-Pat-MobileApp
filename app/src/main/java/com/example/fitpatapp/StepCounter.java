package com.example.fitpatapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StepCounter extends AppCompatActivity implements SensorEventListener {

    BottomNavigationView bottomNavigationView; // bottom nav

    private SensorManager sensorManager;
    private boolean running = false;
    private float totalSteps = 0f;
    private float previousTotalSteps = 0f;

    // TextView steps;
    //SensorManager sensorManager;
    //boolean run = false;


    private CircularProgressBar progressBar;
    private final int goalSteps = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        // Step counter
        progressBar = findViewById(R.id.circularProgressBar);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        loadData();
        resetSteps();

        //Bottom Nav
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.step);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bmi:
                        startActivity(new Intent(getApplicationContext(),BmiCalculator.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.step:

                        return true;
                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(),RouteTrackerActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        running = true;

        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        //run = false;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        TextView tv_stepsTaken = findViewById(R.id.tv_stepsTaken);

        if (running) {
            totalSteps = event.values[0];

            int currentSteps = (int) (totalSteps - previousTotalSteps);

            tv_stepsTaken.setText(String.valueOf(currentSteps));

            // Inside onSensorChanged method
            int progress = (int) ((currentSteps / (float) goalSteps) * 100);
            progressBar.setProgress(progress);
        }

    }

    public void resetSteps() {
        // Long tap to reset the step
        TextView tv_stepsTaken = findViewById(R.id.tv_stepsTaken);

        tv_stepsTaken.setOnClickListener(v -> Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show());

        tv_stepsTaken.setOnLongClickListener(v -> {
            previousTotalSteps = totalSteps;

            tv_stepsTaken.setText("0");
            progressBar.setProgress(0);


            saveData();

            return true;
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", previousTotalSteps);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        float savedNumber = sharedPreferences.getFloat("key1", 0f);

        Log.d("MainActivity", String.valueOf(savedNumber));

        previousTotalSteps = savedNumber;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}