package com.example.fitpatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HighBloodPressure extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    Button yoga_btn, skipping_btn, walk_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_blood_pressure);

        yoga_btn = (Button) findViewById(R.id.yoga); // do yoga button
        skipping_btn = (Button) findViewById(R.id.Skipping_btn);// skipping button
        walk_btn = (Button) findViewById(R.id.walk_btn);// use step counter button


        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.dashboard:

                        return true;
                    case R.id.bmi:
                        startActivity(new Intent(getApplicationContext(), BmiCalculator.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.step:
                        startActivity(new Intent(getApplicationContext(),StepCounter.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(),RouteTrackerActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });




        yoga_btn.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(HighBloodPressure.this, YogaActivityHBP.class));
            }
        });

        skipping_btn.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(HighBloodPressure.this, SkippingHBP.class));
            }
        });

        walk_btn.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(HighBloodPressure.this, StepCounter.class));
            }
        });

    }
}