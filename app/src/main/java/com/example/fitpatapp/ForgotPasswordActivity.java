package com.example.fitpatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText username, newPassword;
    Button btnResetPassword;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username = findViewById(R.id.username);
        newPassword = findViewById(R.id.new_password);
        btnResetPassword = findViewById(R.id.btn_reset_password);

        DB = new DBHelper(this);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String newPass = newPassword.getText().toString();

                if (user.equals("") || newPass.equals("")) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUser = DB.checkusername(user);
                    if (checkUser) {
                        boolean updatePassword = DB.updatePassword(user, newPass);
                        if (updatePassword) {
                            Toast.makeText(ForgotPasswordActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                            finish(); // Close the forgot password activity
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Username does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}