package com.example.fitnesstracker;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class registerScreenGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen_goal);
        measurementType(findViewById(R.id.spinnerWeeklyGoal));

    }

    public void measurementType(View view){
        DBAdapter.DatabaseHelper dbHelper = new DBAdapter.DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                "user_measurement_system"
        };

        Cursor cursor = db.query(
                "users",
                projection,
                null,
                null,
                null,
                null,
                "user_id DESC",
                "1"
        );

        while (cursor.moveToNext()){
            String measurementSystem = cursor.getString(cursor.getColumnIndexOrThrow("user_measurement_system"));

            Log.d(TAG, "onCreate: " + measurementSystem);

            Toast.makeText(this, measurementSystem, Toast.LENGTH_SHORT).show();

            if(measurementSystem.equals("Metric")){
                Spinner weeklyGoalSpinner = (Spinner) findViewById(R.id.spinnerWeeklyGoal);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_weekly_goal_kg, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                weeklyGoalSpinner.setAdapter(adapter);
            }
            else if (measurementSystem.equals("Imperial")){
                Spinner weeklyGoalSpinner = (Spinner) findViewById(R.id.spinnerWeeklyGoal);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_weekly_goal_lb, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                weeklyGoalSpinner.setAdapter(adapter);
            }
            else{
                Log.d(TAG, "onCreate: Error");
            }
        }

        cursor.close();
        db.close();


    }
}
