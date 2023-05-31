package com.example.fitnesstracker;

import android.content.Intent;
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
    private DBAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_v2);

        mEmail = findViewById(R.id.email);

        mPassword = findViewById(R.id.password);

        mLoginButton = findViewById(R.id.loginscreen_button);

        mRegisterLink = findViewById(R.id.register_link);

        dbAdapter = new DBAdapter(this);

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                boolean isValidLogin = dbAdapter.checkUser(email, password);
                mLoginButton.setEnabled(true);
                if (isValidLogin){
                    Intent intent = new Intent(LoginActivity.this, Home_Page.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
