package com.example.fitpatapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class SkippingHBP extends AppCompatActivity {

    private int counter;
    private Button button;
    private Button pauseButton;
    private TextView textView;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private long timeRemaining = 120000; // Initial duration of the countdown in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skipping_hbp);

        // back arrow on action bar to go back //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // transparent action bar functionality
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            actionBar.setDisplayShowTitleEnabled(false);
        }


        button = findViewById(R.id.button); // start timer button
        pauseButton = findViewById(R.id.pauseButton); // pause button
        textView = findViewById(R.id.textView); // timer text



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTimerRunning) {
                    startTimer();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });
    }

    private void startTimer() {
        counter = (int) (timeRemaining / 1000); // Initialize the counter to 100 seconds
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                counter = (int) (millisUntilFinished / 1000); // Calculate the remaining seconds
                int minutes = counter / 60;
                int seconds = counter % 60;
                String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                textView.setText(time);
            }

            public void onFinish() {
                textView.setText("FINISH!!");
                pauseButton.setVisibility(View.GONE);
                isTimerRunning = false;
            }
        };

        countDownTimer.start();
        isTimerRunning = true;
        pauseButton.setVisibility(View.VISIBLE);
        button.setText("Pause"); // Change the text of the button to "Pause"
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            pauseButton.setVisibility(View.GONE);
            isTimerRunning = false;
            button.setText("Resume"); // Change the text of the button to "Resume"
        } else {
            startTimer(); // Resume the timer from where it was stopped
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isTimerRunning) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isTimerRunning) {
            startTimer();
        }
    }
}