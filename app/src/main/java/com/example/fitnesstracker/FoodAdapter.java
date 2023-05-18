package com.example.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstracker.ui.food.FoodFragment.Food;
import com.example.fitnesstracker.R;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> foodList;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);

        holder.foodNameTextView.setText(food.getFoodName());
        holder.foodServingSizeTextView.setText(String.valueOf("Serving Size: " + food.getServingSize()));
        holder.foodCaloriesTextView.setText(String.valueOf("Calories: " + food.getFoodCalories()));
        holder.foodProteinTextView.setText(String.valueOf("Protein: " + food.getFoodProtein()));
        holder.foodCarbsTextView.setText(String.valueOf("Carbohydrates: " + food.getFoodCarbs()));
        holder.foodFatTextView.setText(String.valueOf("Fat: " + food.getFoodFat()));
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        public TextView foodNameTextView;
        public TextView foodServingSizeTextView;
        public TextView foodCaloriesTextView;
        public TextView foodProteinTextView;
        public TextView foodCarbsTextView;
        public TextView foodFatTextView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.text_food_name);
            foodServingSizeTextView = itemView.findViewById(R.id.text_food_serving_size);
            foodCaloriesTextView = itemView.findViewById(R.id.text_food_calories);
            foodProteinTextView = itemView.findViewById(R.id.text_food_protein);
            foodCarbsTextView = itemView.findViewById(R.id.text_food_carbs);
            foodFatTextView = itemView.findViewById(R.id.text_food_fat);
        }
    }
}
