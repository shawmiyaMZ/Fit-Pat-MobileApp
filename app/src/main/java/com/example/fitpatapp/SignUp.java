package com.example.fitpatapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    TextInputEditText username, password, repassword;

    final String[] selectedGender = {""}; // this variable will hold the selected gender value
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        repassword = (TextInputEditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);

        TextView textView = findViewById(R.id.login); // I have an account text

        ImageView maleImageView = findViewById(R.id.male_image_view);
        ImageView femaleImageView = findViewById(R.id.female_image_view);
        int selectedColorMle = getResources().getColor(R.color.blue);
        int selectedColorFmle = getResources().getColor(R.color.pink);
        int unselectedColor = getResources().getColor(R.color.black);
        DB = new DBHelper(this);

        // Create a SpannableString for the "I hav an account" text
        SpannableString IHaveAccount = new SpannableString(getString(R.string.have_account));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                // Open LoginActivity when "I hav an account"" text is clicked
                startActivity(new Intent(SignUp.this, LoginActivity.class));
            }
        };
        IHaveAccount.setSpan(clickableSpan, 0, IHaveAccount.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString as the text for the "I hav an account"" TextView
        textView.setText(IHaveAccount);
        textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        // Add a TextWatcher to the username EditText
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Check if the input is empty
                if (editable.toString().isEmpty()) {
                    // Set an error message on the username TextInputLayout
                    username.setError("Username is required");
                } else {
                    // Clear the error message
                    username.setError(null);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Check if the input is empty
                if (editable.toString().isEmpty()) {
                    // Set an error message on the username TextInputLayout
                    password.setError("Username is required");
                } else {
                    // Clear the error message
                    password.setError(null);
                }
            }
        });


        repassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Check if the input is empty
                if (editable.toString().isEmpty()) {
                    // Set an error message on the username TextInputLayout
                    repassword.setError("Username is required");
                } else {
                    // Clear the error message
                    repassword.setError(null);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();


                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (selectedGender[0].isEmpty()) {
                        Toast.makeText(SignUp.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass, selectedGender[0]);
                            if(insert==true){
                                Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                String usernameText = username.getText().toString();
                                intent.putExtra("USERNAME", usernameText); // Pass the username to the main activity
                                startActivity(intent);
                                finish();
                            }


                            else{
                                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }

                if(user.isEmpty()) {
                    username.setError("Username is required");
                    return;
                }

                if(pass.isEmpty()){
                    password.setError("Password is required");
                    return;
                }

                if(repass.isEmpty()){
                    repassword.setError("Please re-type password");
                    return;
                }
            }
        });




        maleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender[0] = "male";
                maleImageView.setImageResource(R.drawable.male);
                femaleImageView.setImageResource(R.drawable.female);
                maleImageView.setColorFilter(selectedColorMle);
                femaleImageView.setColorFilter(unselectedColor);
            }
        });

        femaleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedGender[0] = "female";
                maleImageView.setImageResource(R.drawable.male);
                femaleImageView.setImageResource(R.drawable.female);
                maleImageView.setColorFilter(unselectedColor);
                femaleImageView.setColorFilter(selectedColorFmle);

            }
        });

        textView.setOnClickListener(new View.OnClickListener () {

            public void onClick(View view) {

                startActivity(new Intent(SignUp.this, LoginActivity.class));
            }
        });
    }
}
