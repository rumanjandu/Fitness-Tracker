package com.example.fitnesstracker.ui.food;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstracker.DBAdapter;
import com.example.fitnesstracker.FoodAdapter;
import com.example.fitnesstracker.databinding.FragmentFoodBinding;

import java.util.ArrayList;
import java.util.List;


public class FoodFragment extends Fragment {

    // Food class
    private EditText editTextSearchFood;
    private Button buttonSearchFood;
    private TextView textViewFoodResult;
    private DBAdapter dbAdapter;

    private RecyclerView recyclerViewFood;
    private FoodAdapter foodAdapter;

    private FragmentFoodBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FoodViewModel foodViewModel =
                new ViewModelProvider(this).get(FoodViewModel.class);

        binding = FragmentFoodBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize views
        editTextSearchFood = binding.editTextSearchFood;
        editTextSearchFood.addTextChangedListener(searchWatcher);
        buttonSearchFood = binding.buttonSearchFood;
        textViewFoodResult = binding.textViewFoodResult;
        recyclerViewFood = binding.recyclerViewFood;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize DBAdapter object
        dbAdapter = new DBAdapter(getContext());

        // Initialize FoodAdapter object
        buttonSearchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editTextSearchFood.getText().toString().trim();
                searchFood(searchQuery);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // TextWatcher object
    private TextWatcher searchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            searchFood(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    // searchFood() method
    private void searchFood(String searchQuery) {
        List<Food> foodList = new ArrayList<>();
        dbAdapter.open();

        // Cursor object
        Cursor cursor;
        if (searchQuery.isEmpty()){
            cursor = null;
            foodList.clear();
        } else {
        cursor = dbAdapter.getFoodByQuery(searchQuery);
        }

        // If cursor is not null and moveToFirst() method returns true
        if (cursor != null && cursor.moveToFirst()) {
            foodList.clear();

            // getColumnIndex() method
            int foodIdIndex = cursor.getColumnIndex("food_id");
            int foodNameIndex = cursor.getColumnIndex("food_name");
            int foodServingSizeIndex = cursor.getColumnIndex("food_serving_size");
            int foodCaloriesIndex = cursor.getColumnIndex("food_calories");
            int foodProteinIndex = cursor.getColumnIndex("food_proteins");
            int foodCarbsIndex = cursor.getColumnIndex("food_carbohydrates");
            int foodFatIndex = cursor.getColumnIndex("food_fat");

            // do-while loop
            do {
                int foodId = cursor.getInt(foodIdIndex);
                String foodName = cursor.getString(foodNameIndex);
                double foodServingSize = cursor.getDouble(foodServingSizeIndex);
                double foodCalories = cursor.getDouble(foodCaloriesIndex);
                double foodProtein = cursor.getDouble(foodProteinIndex);
                double foodCarbs = cursor.getDouble(foodCarbsIndex);
                double foodFat = cursor.getDouble(foodFatIndex);

                // Food object
                Food food = new Food(foodId, foodName, foodServingSize, foodCalories, foodProtein, foodCarbs, foodFat);
                foodList.add(food);
            } while (cursor.moveToNext());

            // FoodAdapter object
            if (foodAdapter == null) {
                foodAdapter = new FoodAdapter(requireContext(), foodList);
                recyclerViewFood.setAdapter(foodAdapter);
            } else {
                foodAdapter.setFoodList(foodList);
                foodAdapter.notifyDataSetChanged();
            }

            textViewFoodResult.setVisibility(View.GONE);
        } else {
            foodList.clear();

            if (foodAdapter != null) {
                foodAdapter.notifyDataSetChanged();
            }

            textViewFoodResult.setVisibility(View.VISIBLE);
        }

        if (cursor != null) {
            cursor.close();
        }
        dbAdapter.close();
    }

    // Food class
    public class Food {
        private final int foodId;
        private final String foodName;
        private final double foodServingSize;
        private final double foodCalories;
        private final double foodProtein;
        private final double foodCarbs;
        private final double foodFat;

        // Constructor
        public Food(int foodId, String foodName, double foodServingSize, double foodCalories, double foodProtein, double foodCarbs, double foodFat) {
            this.foodId = foodId;
            this.foodName = foodName;
            this.foodServingSize = foodServingSize;
            this.foodCalories = foodCalories;
            this.foodProtein = foodProtein;
            this.foodCarbs = foodCarbs;
            this.foodFat = foodFat;
        }

        // Getter methods
        public int getFoodId() {
            return foodId;
        }

        public String getFoodName() {
            return foodName;
        }
        public double getServingSize(){
            return foodServingSize;
        }

        public double getFoodCalories() {
            return foodCalories;
        }

        public double getFoodProtein() {
            return foodProtein;
        }

        public double getFoodCarbs() {
            return foodCarbs;
        }

        public double getFoodFat() {
            return foodFat;
        }
    }
}
