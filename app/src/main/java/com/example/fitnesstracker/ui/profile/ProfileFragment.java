package com.example.fitnesstracker.ui.profile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnesstracker.DBAdapter;
import com.example.fitnesstracker.databinding.FragmentProfileBinding;
import com.example.fitnesstracker.edit_profile_goals;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private DBAdapter dbAdapter;
    private TextView profileName;
    private TextView profileEmail;
    private TextView profileDOB;
    private TextView profileGender;
    private TextView profileMeasurementSystem;
    private TextView profileCurrentWeight;
    private TextView profileWeeklyGoal;
    private TextView profileTargetWeight;
    private Button profileEditGoals;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel ProfileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        ProfileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        profileName = binding.profileName;
        profileEmail = binding.profileEmail;
        profileDOB = binding.profileDob;
        profileGender = binding.profileGender;
        profileMeasurementSystem = binding.profileMeasurementSystem;
        profileCurrentWeight = binding.textViewCurrentWeightValue;
        profileWeeklyGoal = binding.textViewWeeklyGoalValue;
        profileTargetWeight = binding.textViewTargetWeightValue;
        profileEditGoals = binding.editProfileButton;

        //edit profile button
        profileEditGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), edit_profile_goals.class);
                startActivity(intent);
            }
        });

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

            profileEmail.setText(userEmail);

            if (cursor != null && cursor.moveToFirst()) {
                int goalCurrentWeightIndex = cursor.getColumnIndex("goal_current_weight");
                int goalTargetWeightIndex = cursor.getColumnIndex("goal_target_weight");
                int goalWeeklyGoalIndex = cursor.getColumnIndex("goal_weekly_goal");

                if (goalCurrentWeightIndex != -1 && goalTargetWeightIndex != -1 && goalWeeklyGoalIndex != -1) {
                    Double goalCurrentTarget = cursor.getDouble(goalCurrentWeightIndex);
                    Double goalTargetWeight = cursor.getDouble(goalTargetWeightIndex);
                    String goalWeeklyGoal = cursor.getString(goalWeeklyGoalIndex);

                    profileCurrentWeight.setText(goalCurrentTarget.toString());
                    profileTargetWeight.setText(goalTargetWeight.toString());
                    profileWeeklyGoal.setText(goalWeeklyGoal);

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
                int userDOBIndex = userCursor.getColumnIndex("user_dob");
                int userGenderIndex = userCursor.getColumnIndex("user_gender");
                int userMeasurementSystemIndex = userCursor.getColumnIndex("user_measurement_system");

                if (userNameIndex != -1 && userDOBIndex != -1 && userGenderIndex != -1 && userMeasurementSystemIndex != -1) {
                    String userName = userCursor.getString(userNameIndex);
                    String userDOB = userCursor.getString(userDOBIndex);
                    String userGender = userCursor.getString(userGenderIndex);
                    String userMeasurementSystem = userCursor.getString(userMeasurementSystemIndex);

                    profileName.setText(userName);
                    profileDOB.setText(userDOB);
                    profileGender.setText(userGender);
                    profileMeasurementSystem.setText(userMeasurementSystem);

                } else {
                    Log.d("HomeFragment", "user_name column not found in the cursor.");
                }
            } else {
                Log.d("HomeFragment", "Failed to retrieve user data from the database.");
            }
        } else {
            Log.d("HomeFragment", "User email is null or empty.");
        }





















        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}