package com.example.fitpatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class BmiCalculator extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private EditText heightText;
    private EditText weightText;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        heightText = findViewById(R.id.height);
        weightText = findViewById(R.id.weight);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
/// Calculate Button BMI///
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
/// Bottom Navigation/////
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.bmi);
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

    }
// Calculate BMI method///
    public void calculateBMI() {
        String heightStr = heightText.getText().toString();
        String weightStr = weightText.getText().toString();

        if (TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
            Toast.makeText(this, "Please enter both height and weight values.", Toast.LENGTH_SHORT).show();
            return;
        }

        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);
        double heightM = height / 100;

        if (heightM <= 0 || weight <= 0) {
            Toast.makeText(this, "Please enter valid height and weight values.", Toast.LENGTH_SHORT).show();
            return;
        }

        double BMI = weight / (heightM * heightM);
        DecimalFormat df = new DecimalFormat("#.#");
        double BMI_trimmed = Double.parseDouble(df.format(BMI));

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Please select a gender.", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedGenderButton = findViewById(selectedGenderId);
        String gender = selectedGenderButton.getText().toString();

        TextView BMIResult = findViewById(R.id.result);
        BMIResult.setText(Double.toString(BMI_trimmed));

        TextView BMICategory = findViewById(R.id.bmiCat);
        String BMI_Cat = getBMICategory(BMI, gender);
        BMICategory.setText(BMI_Cat);
    }
// getting result method//
    public String getBMICategory(double BMI, String gender) {
        if (gender.equals("Male")) {
            if (BMI < 15) {
                return "Very severely underweight (Male)";
            } else if (BMI >= 15 && BMI < 16) {
                return "Severely underweight (Male)";
            } else if (BMI >= 16 && BMI < 18.5) {
                return "Underweight (Male)";
            } else if (BMI >= 18.5 && BMI < 25) {
                return "Normal (Male)";
            } else if (BMI >= 25 && BMI < 30) {
                return "Overweight (Male)";
            } else if (BMI >= 30 && BMI < 35) {
                return "Obese Class 1 - Moderately Obese (Male)";
            } else if (BMI >= 35 && BMI < 40) {
                return "Obese Class 2 - Severely Obese (Male)";
            } else {
                return "Obese Class 3 - Very Severely Obese (Male)";
            }
        } else if (gender.equals("Female")) {
            if (BMI < 15) {
                return "Very severely underweight (Female)";
            } else if (BMI >= 15 && BMI < 16) {
                return "Severely underweight (Female)";
            } else if (BMI >= 16 && BMI < 18.5) {
                return "Underweight (Female)";
            } else if (BMI >= 18.5 && BMI < 25) {
                return "Normal (Female)";
            } else if (BMI >= 25 && BMI < 30) {
                return "Overweight (Female)";
            } else if (BMI >= 30 && BMI < 35) {
                return "Obese Class 1 - Moderately Obese (Female)";
            } else if (BMI >= 35 && BMI < 40) {
                return "Obese Class 2 - Severely Obese (Female)";
            } else {
                return "Obese Class 3 - Very Severely Obese";

            }

        }
        return gender;
    }
}