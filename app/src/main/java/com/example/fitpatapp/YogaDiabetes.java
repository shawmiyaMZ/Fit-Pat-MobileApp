package com.example.fitpatapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class YogaDiabetes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_diabetes);


        // back arrow on action bar to go back //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // transparent action bar functionality
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            actionBar.setDisplayShowTitleEnabled(false);
        }




    }
}