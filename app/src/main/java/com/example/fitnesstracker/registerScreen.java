package com.example.fitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
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
        measurementChanged();

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
        } else {
            textViewEmail.setTextColor(Color.BLACK);
        }

        //dob
        String todaysDate = getTodaysDate();
        TextView textViewDOB = (TextView) findViewById(R.id.textViewDOB);
        String dob = dobButton.getText().toString();
        if (dob.equals(todaysDate)) {
            Toast.makeText(this, "Please enter a valid date of birth", Toast.LENGTH_SHORT).show();
            error = 1;
            textViewDOB.setTextColor(Color.RED);
        } else {
            textViewDOB.setTextColor(Color.BLACK);
        }

        //function to check if gender radio button is selected
        TextView textViewGender = (TextView) findViewById(R.id.textViewGender);
        RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        String selectedGenderValue = "";

        if (selectedId == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            error = 1;
            textViewGender.setTextColor(Color.RED);
        } else if (selectedId != -1) {
            textViewGender.setTextColor(Color.BLACK);
            RadioButton selectedGenderRadioButton = (RadioButton) findViewById(selectedId);
            selectedGenderValue = selectedGenderRadioButton.getText().toString();
        }


        //measurements
        Spinner spinnerMeasurements = (Spinner) findViewById(R.id.spinnerMeasurementSystem);
        String stringMeasurementSystem = spinnerMeasurements.getSelectedItem().toString();
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

        //height (feet)
        TextView textViewHeight = findViewById(R.id.textViewHeight);
        EditText editTextHeightFeet = findViewById(R.id.editTextHeight);
        EditText editTextHeightInches = findViewById(R.id.editTextHeightInches);
        String heightFeet = editTextHeightFeet.getText().toString();
        String heightInches = editTextHeightInches.getText().toString();
        if (heightFeet.isEmpty() && heightInches.isEmpty()) {
            editTextHeightFeet.setError("Height is required");
            error = 1;
            textViewHeight.setTextColor(Color.RED);
        } else {
            textViewHeight.setTextColor(Color.BLACK);
        }

        //height (cm)
        EditText editTextHeightCM = findViewById(R.id.editTextHeight);
        String heightCM = editTextHeightCM.getText().toString();
        if (heightCM.isEmpty()) {
            editTextHeightCM.setError("Height is required");
            error = 1;
            textViewHeight.setTextColor(Color.RED);
        } else {
            textViewHeight.setTextColor(Color.BLACK);
        }

        // Get height in feet and inches
        String heightFeetString = editTextHeightFeet.getText().toString();
        String heightInchesString = editTextHeightInches.getText().toString();

        double heightCMDouble = 0;
        if (heightFeetString.isEmpty() && heightInchesString.isEmpty()) {
            editTextHeightFeet.setError("Height is required");
            error = 1;
            textViewHeight.setTextColor(Color.RED);
        } else {
            // Convert height from feet and inches to centimeters
            double heightFeetDouble = Double.parseDouble(heightFeetString);
            double heightInchesDouble = Double.parseDouble(heightInchesString);
            heightCMDouble = (heightFeetDouble * 12 + heightInchesDouble) * 2.54;
            String heightCmString = String.format("%.2f", heightCMDouble);

            // Set the converted height in centimeters to the editTextHeightCM
            //editTextHeightCM.setText(heightCmString);

            // Clear any errors and set text color to black
            editTextHeightFeet.setError(null);
            editTextHeightInches.setError(null);
            textViewHeight.setTextColor(Color.BLACK);
        }

        // Validate height in centimeters
        String heightCMString = editTextHeightCM.getText().toString();
        if (heightCMString.isEmpty()) {
            editTextHeightCM.setError("Height is required");
            error = 1;
            textViewHeight.setTextColor(Color.RED);
        } else {
            textViewHeight.setTextColor(Color.BLACK);
        }


        //weight
        TextView textViewWeight = (TextView) findViewById(R.id.textViewWeight);
        EditText editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        String weight = editTextWeight.getText().toString();
        Double weightDouble = Double.parseDouble(weight);
        if (weight.isEmpty()) {
            editTextWeight.setError("Weight is required");
            error = 1;
            textViewWeight.setTextColor(Color.RED);
        } else {
            textViewWeight.setTextColor(Color.BLACK);
        }

        //converting weight from pounds to kilograms
        if (stringMeasurementSystem.equals("Imperial")) {
            // convert weight from lb to kg
            weightDouble = weightDouble / 2.20462;
            DecimalFormat df = new DecimalFormat("#.##");
            weightDouble = Double.valueOf(df.format(weightDouble));
        }



        //activity level
        Spinner spinnerActivityLevel = findViewById(R.id.spinnerActivityLevel);
        String activityLevelString = spinnerActivityLevel.getSelectedItem().toString();
        TextView textViewActivityLevel = findViewById(R.id.textViewActivityLevel);
        int intActivityLevel = spinnerActivityLevel.getSelectedItemPosition();
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

        //submit button will only work if there are no errors and all fields are filled in correctly
        if (error == 0) {
            Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show();
        }

        //if all fields are empty, error message will be displayed
        if (email.isEmpty() && dobButton.getText().toString().equals(todaysDate) && selectedId == -1 && !isMeasurementSystemSelected && heightFeet.isEmpty() && weight.isEmpty() && !isActivityLevelSelected) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }

        //Insert into database

        DBAdapter db = new DBAdapter(this);
        db.open();

        //quote smart
        String stringEmailSQL = db.quoteSmart(email);
        String stringDOBSQL = db.quoteSmart(dob);
        String stringGenderSQL = db.quoteSmart(selectedGenderValue);
        Double doubleHeightCMDoubleSQL = db.quoteSmart(heightCMDouble);
        Double doubleWeightKGDoubleSQL = db.quoteSmart(weightDouble);
        String activityLevelSQL = db.quoteSmart(activityLevelString);
        String stringMeasurementSQL = db.quoteSmart(stringMeasurementSystem);

        String stringInput = "NULL, " + stringEmailSQL + ", " + stringDOBSQL + ", " + stringGenderSQL + ", " + doubleHeightCMDoubleSQL + ", " + doubleWeightKGDoubleSQL + ", " + activityLevelSQL + ", " + stringMeasurementSQL;


        db.insert("users",
                "user_id, user_email, user_dob, user_gender, user_height, user_weight, user_activity_level, user_measurement_system",
                stringInput);


        db.close();


    }

    public void measurementChanged(){
        //measurement system changed once a measurement system has been selected
        Spinner spinnerMeasurements = (Spinner) findViewById(R.id.spinnerMeasurementSystem);
        spinnerMeasurements.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String measurementSystem = parent.getItemAtPosition(position).toString();
                if (measurementSystem.equals("Metric")) {
                    TextView textViewHeight = findViewById(R.id.textViewHeight);
                    textViewHeight.setText(R.string.HeightCM);
                    TextView textViewWeight = findViewById(R.id.textViewWeight);
                    textViewWeight.setText(R.string.WeightKG);

                    TextView textViewHeightFeet = findViewById(R.id.textViewHeightFeet);
                    textViewHeightFeet.setText("cm");
                    textViewHeightFeet.setVisibility(View.VISIBLE);

                    TextView textViewWeightLBS = findViewById(R.id.textViewWeightLbs);
                    textViewWeightLBS.setText("kg");
                    textViewWeightLBS.setVisibility(View.VISIBLE);

                    TextView textViewHeightInches = findViewById(R.id.textViewHeightInches);
                    textViewHeightInches.setVisibility(View.INVISIBLE);
                    EditText editTextHeightInches = findViewById(R.id.editTextHeightInches);
                    editTextHeightInches.setVisibility(View.INVISIBLE);
                }
                else if (measurementSystem.equals("Imperial")) {
                    TextView textViewHeight = findViewById(R.id.textViewHeight);
                    textViewHeight.setText(R.string.HeightFT);
                    TextView textViewWeight = findViewById(R.id.textViewWeight);
                    textViewWeight.setText(R.string.WeightLBS);

                    TextView textViewHeightFeet = findViewById(R.id.textViewHeightFeet);
                    textViewHeightFeet.setText("ft");
                    textViewHeightFeet.setVisibility(View.VISIBLE);
                    TextView textViewHeightInches = findViewById(R.id.textViewHeightInches);
                    textViewHeightInches.setVisibility(View.VISIBLE);
                    EditText editTextHeightInches = findViewById(R.id.editTextHeightInches);
                    editTextHeightInches.setVisibility(View.VISIBLE);

                    TextView textViewWeightLBS = findViewById(R.id.textViewWeightLbs);
                    textViewWeightLBS.setText("lbs");
                    textViewWeightLBS.setVisibility(View.VISIBLE);
                }
                else {
                    TextView textViewHeight = findViewById(R.id.textViewHeight);
                    textViewHeight.setText("Height");
                    TextView textViewWeight = findViewById(R.id.textViewWeight);
                    textViewWeight.setText("Weight");
                    TextView textViewHeightFeet = findViewById(R.id.textViewHeightFeet);
                    textViewHeightFeet.setVisibility(View.INVISIBLE);
                    TextView textViewWeightLBS = findViewById(R.id.textViewWeightLbs);
                    textViewWeightLBS.setVisibility(View.INVISIBLE);
                    TextView textViewHeightInches = findViewById(R.id.textViewHeightInches);
                    textViewHeightInches.setVisibility(View.INVISIBLE);
                    EditText editTextHeightInches = findViewById(R.id.editTextHeightInches);
                    editTextHeightInches.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

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
