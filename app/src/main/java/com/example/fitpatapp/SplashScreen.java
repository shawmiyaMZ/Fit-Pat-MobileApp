package com.example.fitpatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(){

            @Override
            public void run() {
                try {

                    sleep(5000);
                    startActivity(new Intent(SplashScreen.this, LoginSplash.class));
                    finish();

                }catch (Exception e){

                }
            }
        }; thread.start();



    }
}