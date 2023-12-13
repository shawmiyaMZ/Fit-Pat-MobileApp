package com.example.fitpatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginSplash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsplash);


        TextView tv = findViewById(R.id.login);
        Button signup = findViewById(R.id.signup);

        // Button to go to Login page
        tv.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(LoginSplash.this, LoginActivity.class));
            }
        });

        // Button to go to Sign Up page
        signup.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(LoginSplash.this, SignUp.class));
            }
        });

    }
}