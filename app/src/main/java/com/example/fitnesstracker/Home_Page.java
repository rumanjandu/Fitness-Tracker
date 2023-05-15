package com.example.fitnesstracker;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.fitnesstracker.ui.diet.DietFragment;
import com.example.fitnesstracker.ui.food.FoodFragment;
import com.example.fitnesstracker.ui.goal.GoalFragment;
import com.example.fitnesstracker.ui.home.HomeFragment;
import com.example.fitnesstracker.ui.profile.ProfileFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnesstracker.databinding.ActivityHomePageBinding;

public class Home_Page extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* Toolbar */
        setSupportActionBar(binding.appBarHomePage.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_goal, R.id.nav_diet, R.id.nav_food)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        String email = getIntent().getStringExtra("email");
        Bundle homeBundle = new Bundle();
        homeBundle.putString("email", email);
        navController.navigate(R.id.nav_home, homeBundle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home__page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}