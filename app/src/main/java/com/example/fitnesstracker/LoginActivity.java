package com.example.fitnesstracker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private TextView mRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_v2);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.loginscreen_button);
        mRegisterLink = findViewById(R.id.register_link);


        // Set up a TextWatcher for the password field
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if both fields are not empty and enable the login button
                if (!TextUtils.isEmpty(mEmail.getText().toString().trim())
                        && !TextUtils.isEmpty(mPassword.getText().toString().trim())) {
                    mLoginButton.setEnabled(true);
                } else {
                    mLoginButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                User test = new User(email, password, 60, 10, 18);
                DBAdapter db = new DBAdapter(LoginActivity.this);
                boolean isUserInDatabase =
                        db.checkUserExists(email);

                if(!isUserInDatabase) {
                    db.insertUser(test);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Already exists", Toast.LENGTH_SHORT).show();
                }

                /*boolean isUserInDatabase = false;
                //User loggedInUser = getUserFromDatabase(email, password);

                if(isUserInDatabase) {
                    //Replace MainActivity.class with HomeActivity.class (make sure android manifest is up to date)
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //Show Toast incorrect emails
                }*/
            }
        });

        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                //startActivity(intent);
            }
        });
    }
}
