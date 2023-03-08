package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class registerScreen extends AppCompatActivity {

    private EditText etName, etEmail, etDateOfBirth, etHeight, etWeight, etPassword, etAge;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etDateOfBirth = findViewById(R.id.et_date_of_birth);
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        etPassword = findViewById(R.id.et_password);
        etAge = findViewById(R.id.et_age);

        rgGender = findViewById(R.id.rg_gender);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get values from input fields
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String dateOfBirth = etDateOfBirth.getText().toString().trim();
                int height = Integer.parseInt(etHeight.getText().toString().trim());
                int weight = Integer.parseInt(etWeight.getText().toString().trim());
                String password = etPassword.getText().toString().trim();
                int age = Integer.parseInt(etAge.getText().toString().trim());
                String gender = "";

                // Get selected gender
                int selectedGenderId = rgGender.getCheckedRadioButtonId();
                if (selectedGenderId == rbMale.getId()) {
                    gender = "Male";
                } else if (selectedGenderId == rbFemale.getId()) {
                    gender = "Female";
                }

                // Perform registration logic here
                // ...

                // Show a toast message to indicate successful registration
                String message = "Successfully registered " + name + "!";
                Toast.makeText(registerScreen.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
