package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_login;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.login_button);
        btn_login.setOnClickListener(this);

        btn_register = (Button) findViewById(R.id.register_button);
        btn_register.setOnClickListener(this::register);

        //stetho
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();



        DBAdapter db = new DBAdapter(this);
        db.open();

        //Count rows in food
        int numberRows = db.count("food");

        if(numberRows == 0){
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllFood();
        }

/*        //count number of rows in user
        numberRows = db.count("users");
        if (numberRows > 10){
            Intent i = new Intent(MainActivity.this, registerScreen.class);
            startActivity(i);
        }*/


        // Insert foods for table
        db.close();



    }
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void register(View v) {
        Intent intent = new Intent(this, registerScreen.class);
        startActivity(intent);
    }
}