package com.example.fitnesstracker.ui.home;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.Guideline;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnesstracker.DBAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import com.example.fitnesstracker.R;
import com.example.fitnesstracker.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProgressBar progressBar;
    private TextView progressText;
    private PieChart pieChart;
    private TextView userGreet;
    private TextView proteinText;
    private TextView carbsText;
    private TextView fatText;
    private DBAdapter dbAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //set the id for the progressbar and progress text
        progressBar = binding.progressBar;
        progressText = binding.progressText;
        userGreet = binding.userGreet;
        pieChart = binding.pieChart;

        String email = null;
        if (getArguments() != null) {
            email = getArguments().getString("email");
        }


        dbAdapter = new DBAdapter(getContext());
        dbAdapter.open();

        String userEmail = email;
        if (userEmail != null && !userEmail.isEmpty()) {
            Cursor cursor = dbAdapter.getUserGoals(userEmail);
            if (cursor != null) {
                String[] columnNames = cursor.getColumnNames();
                for (String columnName : columnNames) {
                    Log.d("Column", columnName);
                }
            }

            if (cursor != null && cursor.moveToFirst()) {
                int goalKcalIndex = cursor.getColumnIndex("goal_kcal");
                int goalProteinIndex = cursor.getColumnIndex("goal_protein");
                int goalCarbsIndex = cursor.getColumnIndex("goal_carbs");
                int goalFatIndex = cursor.getColumnIndex("goal_fat");

                if (goalKcalIndex != -1 && goalProteinIndex != -1 && goalCarbsIndex != -1 && goalFatIndex != -1) {
                    double goalKcal = cursor.getDouble(goalKcalIndex);
                    double goalProtein = cursor.getDouble(goalProteinIndex);
                    double goalCarbs = cursor.getDouble(goalCarbsIndex);
                    double goalFat = cursor.getDouble(goalFatIndex);

                    Log.d("HomeFragment", "Retrieved goalKcal: " + goalKcal);

                    progressText.setText(goalKcal + " Calories Remaining");
                    int intGoalKcal = (int) Math.round(goalKcal);
                    progressBar.setProgress(intGoalKcal);

                    ArrayList<PieEntry> entries = new ArrayList<>();
                    entries.add(new PieEntry((float) goalProtein, "Protein"));
                    entries.add(new PieEntry((float) goalCarbs, "Carbs"));
                    entries.add(new PieEntry((float) goalFat, "Fat"));

                    PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
                    dataSet.setColors(Color.MAGENTA, Color.GREEN, Color.RED, Color.YELLOW);
                    PieData data = new PieData(dataSet);
                    pieChart.setData(data);
                    dataSet.setValueTextSize(14f);

                    // Set the value formatter to grams
                    dataSet.setValueFormatter(new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            return value + "g";
                        }
                    });

                    Typeface tf = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC);
                    dataSet.setValueTypeface(tf);
                    pieChart.setEntryLabelTypeface(tf);


                    // Customize the chart
                    pieChart.setDrawHoleEnabled(false);
                    pieChart.setTransparentCircleRadius(50f);
                    pieChart.setHoleRadius(50f);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setEntryLabelTextSize(14f);
                    pieChart.setEntryLabelColor(Color.BLACK);
                    pieChart.animateY(1000);
                    Legend legend = pieChart.getLegend();
                    legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
                    legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
                    //pieChart.setDrawEntryLabels(false);


                } else {
                    Log.d("HomeFragment", "One or more columns not found in the cursor.");
                }
            } else {
                if (cursor == null) {
                    Log.d("HomeFragment", "Cursor is null.");
                } else if (cursor.getCount() == 0) {
                    Log.d("HomeFragment", "Cursor is empty.");
                } else {
                    Log.d("HomeFragment", "Cursor move to first failed.");
                }
            }
        } else {
            Log.d("HomeFragment", "User email is null or empty.");
        }

        if (userEmail != null && !userEmail.isEmpty()) {
            Cursor userCursor = dbAdapter.getUserByEmail(userEmail);
            if (userCursor != null && userCursor.moveToFirst()) {
                int userNameIndex = userCursor.getColumnIndex("user_name");
                if (userNameIndex != -1) {
                    String userName = userCursor.getString(userNameIndex);
                    userGreet.setText("Hello, " + userName + "!");
                } else {
                    Log.d("HomeFragment", "user_name column not found in the cursor.");
                }
            } else {
                Log.d("HomeFragment", "Failed to retrieve user data from the database.");
            }
        } else {
            Log.d("HomeFragment", "User email is null or empty.");
        }


            //get the user's goals



/*        progressText.setText("55 Calories Remaining");
        progressBar.setProgress(55);*/


        /*// Set some data
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(50f, "Protein"));
        entries.add(new PieEntry(40f, "Carbs"));
        entries.add(new PieEntry(20f, "Fat"));

        PieDataSet dataSet = new PieDataSet(entries, "Pie Chart");
        dataSet.setColors(Color.MAGENTA, Color.GREEN, Color.RED, Color.YELLOW);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setValueTextSize(14f);

        // Set the value formatter to grams
        dataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return value + "g";
            }
        });

        Typeface tf = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC);
        dataSet.setValueTypeface(tf);
        pieChart.setEntryLabelTypeface(tf);


        // Customize the chart
        pieChart.setDrawHoleEnabled(false);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setHoleRadius(50f);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelTextSize(14f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.animateY(1000);
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        //pieChart.setDrawEntryLabels(false);
*/

        //userGreet.setText("Hello, User!");

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}