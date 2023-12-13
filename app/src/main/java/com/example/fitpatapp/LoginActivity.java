package com.example.fitpatapp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username, password;
    Button btnLogin;
    DBHelper DB;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // login page

        // Initialize UI elements
        username = (TextInputEditText) findViewById(R.id.username1);
        password = (TextInputEditText) findViewById(R.id.password1);
        btnLogin = (Button) findViewById(R.id.btnsignin1);
        TextView textView = findViewById(R.id.resetPassword); // TextView for "Forgot Password"


        DB = new DBHelper(this);

        // Create a SpannableString for the "Forgot Password" text
        SpannableString forgotPasswordText = new SpannableString(getString(R.string.forget_password));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // Open ForgotPasswordActivity when "Forgot Password" text is clicked
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        };
        forgotPasswordText.setSpan(clickableSpan, 0, forgotPasswordText.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString as the text for the "Forgot Password" TextView
        textView.setText(forgotPasswordText);
        textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        // Login button click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        String usernameText = username.getText().toString();
                        intent.putExtra("USERNAME", usernameText); // Pass the username to the main activity
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
