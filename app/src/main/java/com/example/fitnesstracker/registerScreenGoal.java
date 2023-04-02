package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class registerScreenGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen_goal);
    }

    public void spinnerMeaurementSelection() {
        Spinner spinnerMeasurements = (Spinner) findViewById(R.id.spinnerMeasurementSystem);
        String stringMeasurementSystem = spinnerMeasurements.getSelectedItem().toString();
        TextView textViewMeasurements = (TextView) findViewById(R.id.textViewMeasurementSystem);
        Spinner spinnerWeeklyGoal = (Spinner) findViewById(R.id.spinnerWeeklyGoal);

        if (stringMeasurementSystem.equals("Metric")) {
            //do nothing

        } else if (stringMeasurementSystem.equals("Imperial")) {
            //change spinner entries to different array

        } else {

        }
    }
}