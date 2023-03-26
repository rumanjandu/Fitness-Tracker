package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class registerScreen extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dobButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        initDatePicker();
        dobButton = findViewById(R.id.dobPickerButton);
        dobButton.setText(getTodaysDate());

        Button buttonSignup = (Button) findViewById(R.id.buttonSignup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpSubmit();
            }
        });

    }

    public void signUpSubmit() {
        //error checking
        int error = 0;


        //email
        TextView textViewEmail = (TextView) findViewById(R.id.textViewEmail);


        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmailAddress);
        String email = editTextEmail.getText().toString();
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            error = 1;
            textViewEmail.setTextColor(Color.RED);
        }
        else {
            textViewEmail.setTextColor(Color.BLACK);
        }

        //dob
        String todaysDate = getTodaysDate();
        TextView textViewDOB = (TextView) findViewById(R.id.textViewDOB);
        if (dobButton.getText().toString().equals(todaysDate)){
            Toast.makeText(this, "Please enter a valid date of birth", Toast.LENGTH_SHORT).show();
            error = 1;
            textViewDOB.setTextColor(Color.RED);
        }
        else {
            textViewDOB.setTextColor(Color.BLACK);
        }

        //function to check if gender radio button is selected
        RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        TextView textViewGender = (TextView) findViewById(R.id.textViewGender);
        int selectedId = radioGroupGender.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            error = 1;
            textViewGender.setTextColor(Color.RED);
        }
        else {
            textViewGender.setTextColor(Color.BLACK);
        }

        //measurements
        Spinner spinnerMeasurements = (Spinner) findViewById(R.id.spinnerMeasurementSystem);
        TextView textViewMeasurements = (TextView) findViewById(R.id.textViewMeasurementSystem);
        boolean isMeasurementSystemSelected = isMeasurementSystemSelected(spinnerMeasurements);
        if (isMeasurementSystemSelected) {
            // a measurement system has been selected
            textViewMeasurements.setTextColor(Color.BLACK);
        } else {
            // a measurement system has not been selected
            textViewMeasurements.setTextColor(Color.RED);
            Toast.makeText(this, "Please select a measurement system", Toast.LENGTH_SHORT).show();
        }

        //height
        TextView textViewHeight = (TextView) findViewById(R.id.textViewHeight);
        EditText editTextHeightFeet = (EditText) findViewById(R.id.editTextHeightFeet);
        EditText editTextHeightInches = (EditText) findViewById(R.id.editTextHeightInches);
        String heightFeet = editTextHeightFeet.getText().toString();
        String heightInches = editTextHeightInches.getText().toString();
        if (heightFeet.isEmpty() || heightInches.isEmpty()) {
            editTextHeightFeet.setError("Height is required");
            editTextHeightInches.setError("Height is required");
            error = 1;
            textViewHeight.setTextColor(Color.RED);
        }
        else {
            textViewHeight.setTextColor(Color.BLACK);
        }

        //weight
        TextView textViewWeight = (TextView) findViewById(R.id.textViewWeight);
        EditText editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        String weight = editTextWeight.getText().toString();
        if (weight.isEmpty()) {
            editTextWeight.setError("Weight is required");
            error = 1;
            textViewWeight.setTextColor(Color.RED);
        }
        else {
            textViewWeight.setTextColor(Color.BLACK);
        }

        //activity level
        Spinner spinnerActivityLevel = (Spinner) findViewById(R.id.spinnerActivityLevel);
        TextView textViewActivityLevel = (TextView) findViewById(R.id.textViewActivityLevel);
        boolean isActivityLevelSelected = isActivityLevelSelected(spinnerActivityLevel);
        if (isActivityLevelSelected) {
            // an activity level has been selected
            textViewActivityLevel.setTextColor(Color.BLACK);
        } else {
            // an activity level has not been selected
            error = 1;
            textViewActivityLevel.setTextColor(Color.RED);
            Toast.makeText(this, "Please select an activity level", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isActivityLevelSelected(Spinner spinnerActivityLevel) {
        int selectedPosition = spinnerActivityLevel.getSelectedItemPosition();
        if (selectedPosition == AdapterView.INVALID_POSITION) {
            // nothing is selected
            return false;
        } else if (selectedPosition == 0) {
            // "Select an activity level" is selected
            return false;
        } else {
            // a valid activity level is selected
            return true;
        }
    }

    private boolean isMeasurementSystemSelected(Spinner spinnerMeasurements) {
        int selectedPosition = spinnerMeasurements.getSelectedItemPosition();
        if (selectedPosition == AdapterView.INVALID_POSITION) {
            // nothing is selected
            return false;
        } else if (selectedPosition == 0) {
            // "Select a measurement system" is selected
            return false;
        } else {
            // a valid measurement system is selected
            return true;
        }
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1) return "Jan";
        if (month == 2) return "Feb";
        if (month == 3) return "Mar";
        if (month == 4) return "Apr";
        if (month == 5) return "May";
        if (month == 6) return "Jun";
        if (month == 7) return "Jul";
        if (month == 8) return "Aug";
        if (month == 9) return "Sep";
        if (month == 10) return "Oct";
        if (month == 11) return "Nov";
        if (month == 12) return "Dec";
        return "Jan";
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                dobButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;



        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }


    public void openDOBPicker(View view) {
        datePickerDialog.show();
    }
}
