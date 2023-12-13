package com.example.fitpatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Diabetes extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button cycling_btn, walk_btn,yoga_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);
        cycling_btn = (Button) findViewById(R.id.cycling_btn); // Cycling button
        yoga_btn = (Button) findViewById(R.id.yogadiabetes_btn);// Yoga button
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

        cycling_btn.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(Diabetes.this, RouteTrackerActivity.class));
            }
        });

        walk_btn.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(Diabetes.this, StepCounter.class));
            }
        });

        yoga_btn.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(Diabetes.this, YogaDiabetes.class));
            }
        });

    }
}