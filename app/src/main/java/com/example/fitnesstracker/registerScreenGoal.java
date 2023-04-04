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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class registerScreenGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen_goal);
        measurementType(findViewById(R.id.spinnerWeeklyGoal));

        Button btnSubmit = (Button) findViewById(R.id.buttonSignupSecond);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpSubmit2();
            }
        });

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

            Toast.makeText(this, "Your measurement system is set to: " + measurementSystem, Toast.LENGTH_SHORT).show();

            if(measurementSystem.equals("Metric")){
                Spinner weeklyGoalSpinner = (Spinner) findViewById(R.id.spinnerWeeklyGoal);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_weekly_goal_kg, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                weeklyGoalSpinner.setAdapter(adapter);

                TextView textViewTargetWeightMeasurement = (TextView) findViewById(R.id.textViewTargetWeightMeasurementType);
                textViewTargetWeightMeasurement.setText("kg");
                textViewTargetWeightMeasurement.setVisibility(View.VISIBLE);
            }
            else if (measurementSystem.equals("Imperial")){
                Spinner weeklyGoalSpinner = (Spinner) findViewById(R.id.spinnerWeeklyGoal);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_weekly_goal_lb, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                weeklyGoalSpinner.setAdapter(adapter);

                TextView textViewTargetWeightMeasurement = (TextView) findViewById(R.id.textViewTargetWeightMeasurementType);
                textViewTargetWeightMeasurement.setText("lb");
                textViewTargetWeightMeasurement.setVisibility(View.VISIBLE);
            }
            else{
                Log.d(TAG, "onCreate: Error");
            }
        }

        cursor.close();
        db.close();


    }

    public void signUpSubmit2(){
        //error checking
        int error = 0;

        //target weight error checking
        TextView textViewTargetWeight = (TextView) findViewById(R.id.textViewTargetWeight);
        EditText editTextTargetWeight = (EditText) findViewById(R.id.editTextTargetWeight);
        String targetWeight = editTextTargetWeight.getText().toString();

        double targetWeightDouble = 0.0;
        if (!targetWeight.isEmpty()){
            targetWeightDouble = Double.parseDouble(targetWeight);
        }

        if(targetWeight.isEmpty()){
            textViewTargetWeight.setTextColor(Color.RED);
            error = 1;
            editTextTargetWeight.setError("Please enter a target weight");
        }
        else{
            textViewTargetWeight.setTextColor(Color.BLACK);
        }

        //weekly weight goal error checking
        TextView textViewWeeklyGoal = (TextView) findViewById(R.id.textViewWeeklyGoal);
        Spinner spinnerWeeklyGoal = (Spinner) findViewById(R.id.spinnerWeeklyGoal);
        String weeklyGoal = spinnerWeeklyGoal.getSelectedItem().toString();
        boolean weeklyGoalSelected = weeklyGoalSelected(spinnerWeeklyGoal);

        if(weeklyGoalSelected){
            textViewWeeklyGoal.setTextColor(Color.BLACK);
        }
        else{
            textViewWeeklyGoal.setTextColor(Color.RED);
            error = 1;
            Toast.makeText(this, "Please select a weekly goal", Toast.LENGTH_SHORT).show();
        }































    }

    private boolean weeklyGoalSelected(Spinner spinnerWeeklyGoal) {
        int selectedPosition = spinnerWeeklyGoal.getSelectedItemPosition();
        if (selectedPosition == AdapterView.INVALID_POSITION) {
            return false;
        } else if (selectedPosition == 0) {
            return false;
        } else {
            return true;
        }
    }


}
