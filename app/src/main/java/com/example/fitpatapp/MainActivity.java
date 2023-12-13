package com.example.fitpatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve the username passed from the login activity
        String username = getIntent().getStringExtra("USERNAME");

        // Set the greeting message with the username
        TextView greetingTextView = findViewById(R.id.greetingTextView);

        greetingTextView.setText("Hi! "+ username + ", " + "You are looking exercise for? ");

        // Set up the button to navigate to the High Blood pressure activity
        CardView btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HighBloodPressure.class));
            }
        });

        // Set up the button to navigate to the BackPain activity
        CardView btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BackPain.class));
            }
        });

        // Set up the button to navigate to the Diabetes activity
        CardView btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Diabetes.class));
            }
        });

        // Set up the button to navigate to the Diabetes activity
        CardView btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Diabetes.class));
            }
        });

        // Set up the button to navigate to the Diabetes activity
        CardView btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HighBloodPressure.class));
            }
        });

        // Set up the button to navigate to the Diabetes activity
        CardView btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BackPain.class));
            }
        });
    }
}