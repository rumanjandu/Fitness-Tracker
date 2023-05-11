package com.example.fitnesstracker;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class registerScreenGoal extends AppCompatActivity {

    String email = "";
    String dob = "";
    String gender = "";
    String measurementSystem = "";
    String activityLevel = "";
    Double height = 0.0;
    Double weight = 0.0;

    String todaysDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen_goal);

        Button btnSubmit = (Button) findViewById(R.id.buttonSignupSecond);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        dob = intent.getStringExtra("dob");
        gender = intent.getStringExtra("gender");
        height = intent.getDoubleExtra("height", 0.0);
        weight = intent.getDoubleExtra("weight", 0.0);
        activityLevel = intent.getStringExtra("activityLevel");
        measurementSystem = intent.getStringExtra("measurement");
        todaysDate = intent.getStringExtra("goalDate");
        measurementType();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpSubmit2();
            }
        });

    }

    public void measurementType(){

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

        if (measurementSystem.equals("Imperial")){
            targetWeightDouble = targetWeightDouble * 0.453592;
            //round to 2 decimal places
            targetWeightDouble = Math.round(targetWeightDouble * 100.0) / 100.0;
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

        //Calculate energy goal based on weekly goal
        double energyGoal = 0.0;
        switch (weeklyGoal) {
            case "0.5 kg":
                energyGoal = 3500;
                break;
            case "1 kg":
                energyGoal = 7000;
                break;
            case "1.5 kg":
                energyGoal = 10500;
                break;
            case "2 kg":
                energyGoal = 14000;
                break;
            case "2.5 kg":
                energyGoal = 17500;
                break;
            case "3 kg":
                energyGoal = 21000;
                break;
            case "0.5 lb":
                energyGoal = 3500;
                break;
            case "1 lb":
                energyGoal = 7000;
                break;
            case "1.5 lb":
                energyGoal = 10500;
                break;
            case "2 lb":
                energyGoal = 14000;
                break;
            case "2.5 lb":
                energyGoal = 17500;
                break;
            case "3 lb":
                energyGoal = 21000;
                break;
            default:
                Log.d(TAG, "signUpSubmit2: Error");
                break;
        }

        //calculate age from date of birth
        LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dateOfBirth, currentDate).getYears();
        //calculate estimated calorie intake based on activity level, gender, age, height, weight


        //if no errors, insert into database
/*        if (error == 0) {
            DBAdapter.DatabaseHelper dbHelper = new DBAdapter.DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();

            Cursor cursor = db.rawQuery("SELECT goals.* " +
                    "FROM goals " +
                    "INNER JOIN users ON goals.user_email = users.user_email " +
                    "WHERE users.user_email = " + "\""+email.toString()+"\"", null);
            int goalId = -1;
            if (cursor.moveToFirst()) {
                goalId = cursor.getInt(0);
            }
            cursor.close();

            boolean success = DBAdapter.updateGoal(db, goalId, targetWeightDouble, weeklyGoal, getApplicationContext());
            if (success) {
                Toast.makeText(this, "Goal added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error adding goal", Toast.LENGTH_SHORT).show();
            }

        }*/

        double calorieIntake = calculateCalorieIntake(height, weight, age, gender, activityLevel, weeklyGoal);

        double proteinIntake = calculateProteinIntake(height, weight, age, gender, activityLevel, weeklyGoal);

        double carbIntake = calculateCarbIntake(height, weight, age, gender, activityLevel, weeklyGoal);

        double fatIntake = calculateFatIntake(height, weight, age, gender, activityLevel, weeklyGoal);

        double energyIntake = calculateEnergyIntake(height, weight, age, gender, activityLevel, weeklyGoal);



        //Insert into database

       if (error == 0) {
        DBAdapter db = new DBAdapter(this);
        db.open();

        //quote smart
        String stringEmailSQL = db.quoteSmart(email);
        String stringDOBSQL = db.quoteSmart(dob);
        String stringGenderSQL = db.quoteSmart(gender);
        Double doubleHeightCMDoubleSQL = db.quoteSmart(height);
        Double doubleWeightKGDoubleSQL = db.quoteSmart(weight);
        String activityLevelSQL = db.quoteSmart(activityLevel);
        String stringMeasurementSQL = db.quoteSmart(measurementSystem);
        Double doubleCalorieIntakeSQL = db.quoteSmart(calorieIntake);
        Double doubleProteinIntakeSQL = db.quoteSmart(proteinIntake);
        Double doubleCarbIntakeSQL = db.quoteSmart(carbIntake);
        Double doubleFatIntakeSQL = db.quoteSmart(fatIntake);
        Double doubleTargetWeightSQL = db.quoteSmart(targetWeightDouble);
        String doubleWeeklyGoalSQL = db.quoteSmart(weeklyGoal);
        Double doubleEnergyIntakeSQL = db.quoteSmart(energyIntake);


        //input for user table
        String stringInputUsers = "NULL, " + stringEmailSQL + ", " + stringDOBSQL + ", " + stringGenderSQL + ", " + doubleHeightCMDoubleSQL + ", " +  activityLevelSQL + ", " + stringMeasurementSQL;
        db.insert("users",
                "user_id, user_email, user_dob, user_gender, user_height, user_activity_level, user_measurement_system",
                stringInputUsers);

       String goalDateSQL = db.quoteSmart(todaysDate);


        //insert into goals table
        String stringInputGoals = "NULL, " + doubleWeightKGDoubleSQL + ", " + goalDateSQL + ", " + stringEmailSQL + ", " + doubleTargetWeightSQL + ", " + doubleWeeklyGoalSQL + ", " + doubleCalorieIntakeSQL + ", " + doubleProteinIntakeSQL + ", " + doubleCarbIntakeSQL + ", " + doubleFatIntakeSQL + ", " + doubleEnergyIntakeSQL;
        db.insert("goals",
                "goal_id, goal_current_weight, goal_date, user_email, goal_target_weight, goal_weekly_goal, goal_kcal, goal_protein, goal_carbs, goal_fat, goal_energy",
                stringInputGoals);

        db.close();
        }

       //if no errors, navigate to next page
        if (error == 0) {
            Intent i = new Intent(this, Home_Page.class);
            startActivity(i);
        }



    }

        private boolean weeklyGoalSelected(Spinner spinnerWeeklyGoal) {
        int selectedPosition = spinnerWeeklyGoal.getSelectedItemPosition();
        if (selectedPosition == AdapterView.INVALID_POSITION) {
            return false;
        } else return selectedPosition != 0;
    }

    public double calculateCalorieIntake(double height, double weight, int age, String gender, String activityLevel, String weeklyGoal) {
        double bmr;
        double activityFactor;

        //convert metric values to imperial values first
        if (measurementSystem.equals("Metric")) {
            height = height * 0.393701;
            weight = weight * 2.20462;
        }else if (measurementSystem.equals("Imperial")){
            //do nothing
        }

            // Calculate BMR based on gender
        if (gender.equals("Male")) {
            bmr = 88.362 + (13.397 * weight) - (5.677 * age);
        } else if (gender.equals("Female")) {
            bmr = 447.593 + (9.247 * weight) - (4.330 * age);
        } else {
            throw new IllegalArgumentException("Invalid gender. Gender must be 'male' or 'female'");
        }

        // Determine activity factor based on activity level
        switch (activityLevel) {
            case "Little to no exercise":
                activityFactor = 1.2;
                break;
            case "Light exercise (1–3 days per week)":
                activityFactor = 1.375;
                break;
            case "Moderate exercise (3–5 days per week)":
                activityFactor = 1.55;
                break;
            case "Heavy exercise (6–7 days per week)":
                activityFactor = 1.725;
                break;
            case "Very heavy exercise (twice per day, extra heavy workouts)":
                activityFactor = 1.9;
                break;
            default:
                throw new IllegalArgumentException("Invalid activity level. Activity level must be one of the following: " +
                        "'Little to no exercise', 'Light exercise (1–3 days per week)', 'Moderate exercise (3–5 days per week)', " +
                        "'Heavy exercise (6–7 days per week)', 'Very heavy exercise (twice per day, extra heavy workouts)'");
        }

        // Calculate daily calorie intake based on BMR and activity factor
        double dailyCalorieIntake = bmr * activityFactor;

        // Determine the unit of the weekly goal (kg or lb) and adjust daily calorie intake accordingly
        if (weeklyGoal.contains("kg")) {
            double weeklyWeightGoal;
            try {
                weeklyWeightGoal = Double.parseDouble(weeklyGoal.replaceAll("[^\\d.]", ""));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid weekly goal. No number found in string: " + weeklyGoal);
            }
            if (weeklyGoal.contains("Lose")) {
                dailyCalorieIntake -= (weeklyWeightGoal * 7700) / 7; // Reduce daily calorie intake for weight loss
            } else {
                dailyCalorieIntake += (weeklyWeightGoal * 7700) / 7; // Increase daily calorie intake for weight gain/maintenance
            }
        } else if (weeklyGoal.contains("lb")) {
            double weeklyWeightGoal;
            try {
                weeklyWeightGoal = Double.parseDouble(weeklyGoal.replaceAll("[^\\d.]", ""));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid weekly goal. No number found in string: " + weeklyGoal);
            }
            if (weeklyGoal.contains("Lose")) {
                dailyCalorieIntake -= (weeklyWeightGoal * 3500) / 7; // Reduce daily calorie intake for weight loss
            } else {
                dailyCalorieIntake += (weeklyWeightGoal * 3500) / 7; // Increase daily calorie intake for weight gain/maintenance
            }
        } else if (weeklyGoal.equals("Maintain current weight")) {
            // Do nothing, dailyCalorieIntake already calculated
        } else {
            throw new IllegalArgumentException("Invalid weekly goal. Weekly goal must be in the format '<number> kg/lb per week' or 'Maintain current weight'");
        }


        //convert daily calorie intake to 2 decimal places
        dailyCalorieIntake = Math.round(dailyCalorieIntake * 100.0) / 100.0;


        return dailyCalorieIntake;
    }


    public double calculateProteinIntake(double height, double weight, int age, String gender, String activityLevel, String weeklyGoal) {
        double proteinIntake;
        double bmr = 0.0;
        double weightGoalFactor;
        double activityFactor;

        //convert metric values to imperial values first
        if (measurementSystem.equals("Metric")) {
            weight = weight * 2.20462;
        }else if (measurementSystem.equals("Imperial")){
            //do nothing
        }

        // Determine activity factor based on activity level
        switch (activityLevel) {
            case "Little to no exercise":
                activityFactor = 1.2;
                break;
            case "Light exercise (1–3 days per week)":
                activityFactor = 1.375;
                break;
            case "Moderate exercise (3–5 days per week)":
                activityFactor = 1.55;
                break;
            case "Heavy exercise (6–7 days per week)":
                activityFactor = 1.725;
                break;
            case "Very heavy exercise (twice per day, extra heavy workouts)":
                activityFactor = 1.9;
                break;
            default:
                throw new IllegalArgumentException("Invalid activity level. Activity level must be one of the following: " +
                        "'Little to no exercise', 'Light exercise (1–3 days per week)', 'Moderate exercise (3–5 days per week)', " +
                        "'Heavy exercise (6–7 days per week)', 'Very heavy exercise (twice per day, extra heavy workouts)'");
        }

        // Determine weight goal factor based on weekly weight goal
        if (weeklyGoal.contentEquals("Maintain current weight")) {
            weightGoalFactor = 0;
        } else {
            double weightGoal = Double.parseDouble(weeklyGoal.split(" ")[1].replaceAll("[^0-9.]", ""));
            String weightGoalDirection = weeklyGoal.split(" ")[0];
            weightGoalFactor = weightGoalDirection.equals("Lose") ? -0.5 : 0.5;
            weightGoalFactor *= weightGoal * 3500 / 7; // convert from kg/week to calorie deficit/surplus per day
        }

        //calculate bmr
        if (gender.contentEquals("Male")) {
            bmr = 88.362 + (13.397 * weight) - (5.677 * age);
        } else if (gender.contentEquals("Female")){
            bmr = 447.593 + (9.247 * weight) - (4.330 * age);
        }

        //calculate calorie intake
        double calorieIntake = calculateCalorieIntake(height, weight, age, gender, activityLevel, weeklyGoal);

        proteinIntake = calorieIntake * 0.15 / 4; // 15% of daily calories from protein, 4 calories per gram of protein

        //convert proteinIntake to 2 decimal places
        proteinIntake = Math.round(proteinIntake * 100.0) / 100.0;

        return proteinIntake;

    }

    public double calculateCarbIntake(double height, double weight, int age, String gender, String activityLevel, String weeklyGoal) {
        double calorieIntake = calculateCalorieIntake(height, weight, age, gender, activityLevel, weeklyGoal);
        double carbPercentage = getCarbPercentage(activityLevel);
        double carbIntake;

        carbIntake = (calorieIntake * carbPercentage) / 4; // 1 gram of carbs contains about 4 calories

        //convert carbIntake to 2 decimal places
        carbIntake = Math.round(carbIntake * 100.0) / 100.0;

        return carbIntake;
    }

    private double getCarbPercentage(String activityLevel) {
        switch (activityLevel) {
            case "Little to no exercise":
                return 0.4;
            case "Light exercise (1–3 days per week)":
                return 0.45;
            case "Moderate exercise (3–5 days per week)":
                return 0.50;
            case "Heavy exercise (6–7 days per week)":
                return 0.55;
            case "Very heavy exercise (twice per day, extra heavy workouts)":
                return 0.60;
            default:
                throw new IllegalArgumentException("Invalid activity level. Activity level must be one of the following: " +
                        "'Little to no exercise', 'Light exercise (1–3 days per week)', 'Moderate exercise (3–5 days per week)', " +
                        "'Heavy exercise (6–7 days per week)', 'Very heavy exercise (twice per day, extra heavy workouts)'");
        }
    }

    public double calculateFatIntake(double height, double weight, int age, String gender, String activityLevel, String weeklyGoal) {
        // Calculate calorie intake
        double calorieIntake = calculateCalorieIntake(height, weight, age, gender, activityLevel, weeklyGoal);

        // Calculate fat intake
        double fatIntake;
        fatIntake = (calorieIntake * 0.25) / 9; // 1 gram of fat contains 9 calories
        //convert fatIntake to 2 decimal places
        fatIntake = Math.round(fatIntake * 100.0) / 100.0;

        return fatIntake;
    }

    public double calculateEnergyIntake(double height, double weight, int age, String gender, String activityLevel, String weeklyGoal) {
        // Calculate daily calorie intake using existing function
        double calorieIntake = calculateCalorieIntake(height, weight, age, gender, activityLevel, weeklyGoal);

        // Convert calorie intake to joules
        double energyIntake = calorieIntake * 4.184;

        // Round to 2 decimal places
        energyIntake = Math.round(energyIntake * 100.0) / 100.0;

        return energyIntake;
    }



}
