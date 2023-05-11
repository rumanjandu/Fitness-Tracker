package com.example.fitnesstracker;

import android.content.Context;

public class DBSetupInsert {

    private final Context context;

    public DBSetupInsert(Context ctx){
        this.context = ctx;
    }

    public void setupInsertToFood(String values){
        DBAdapter db = new DBAdapter(context);
        db.open();

        db.insert("food",
                "food_id, food_name, food_serving_size, food_serving_measurement, food_serving_name_number, food_serving_name_word, food_energy, food_proteins, food_carbohydrates, food_fat, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calulated, food_fat_calculated, food_user_id, food_barcode, food_category_id, food_image_a, food_image_b, food_image_c, food_description",
                values);
        db.close();
    }

    public void insertAllFood(){
        setupInsertToFood("1, 'Chicken Breast', 100, 'g', 1, 'serving', 165, 31, 0, 3.6, 165, 31, 0, 3.6, 1, 0, 1, 'chicken_breast_a', 'chicken_breast_b', 'chicken_breast_c', 'Chicken Breast'");
        setupInsertToFood("2, 'Chicken Thigh', 100, 'g', 1, 'serving', 209, 18, 0, 15, 209, 18, 0, 15, 1, 0, 1, 'chicken_thigh_a', 'chicken_thigh_b', 'chicken_thigh_c', 'Chicken Thigh'");
        setupInsertToFood("3, 'Chicken Drumstick', 100, 'g', 1, 'serving', 172, 17, 0, 11, 172, 17, 0, 11, 1, 0, 1, 'chicken_drumstick_a', 'chicken_drumstick_b', 'chicken_drumstick_c', 'Chicken Drumstick'");
        setupInsertToFood("4, 'Chicken Wing', 100, 'g', 1, 'serving', 203, 16, 0, 15, 203, 16, 0, 15, 1, 0, 1, 'chicken_wing_a', 'chicken_wing_b', 'chicken_wing_c', 'Chicken Wing'");
        setupInsertToFood("5, 'Chicken Leg', 100, 'g', 1, 'serving', 184, 17, 0, 12, 184, 17, 0, 12, 1, 0, 1, 'chicken_leg_a', 'chicken_leg_b', 'chicken_leg_c', 'Chicken Leg'");
        setupInsertToFood("6, 'Chicken Liver', 100, 'g', 1, 'serving', 165, 24, 0, 7.2, 165, 24, 0, 7.2, 1, 0, 1, 'chicken_liver_a', 'chicken_liver_b', 'chicken_liver_c', 'Chicken Liver'");
        setupInsertToFood("7, 'Chicken Heart', 100, 'g', 1, 'serving', 165, 17, 0, 11, 165, 17, 0, 11, 1, 0, 1, 'chicken_heart_a', 'chicken_heart_b', 'chicken_heart_c', 'Chicken Heart'");
        setupInsertToFood("8, 'Eggs', 100, 'g', 1, 'serving', 155, 13, 1.1, 11, 155, 13, 1.1, 11, 1, 0, 1, 'eggs_a', 'eggs_b', 'eggs_c', 'Eggs'");
        setupInsertToFood("9, 'Egg White', 100, 'g', 1, 'serving', 52, 11, 0.7, 0.2, 52, 11, 0.7, 0.2, 1, 0, 1, 'egg_white_a', 'egg_white_b', 'egg_white_c', 'Egg White'");
        setupInsertToFood("10, 'Egg Yolk', 100, 'g', 1, 'serving', 322, 16, 3.6, 27, 322, 16, 3.6, 27, 1, 0, 1, 'egg_yolk_a', 'egg_yolk_b', 'egg_yolk_c', 'Egg Yolk'");
        setupInsertToFood("11, 'Egg Boiled', 100, 'g', 1, 'serving', 155, 13, 1.1, 11, 155, 13, 1.1, 11, 1, 0, 1, 'egg_boiled_a', 'egg_boiled_b', 'egg_boiled_c', 'Egg Boiled'");
        setupInsertToFood("12, 'Egg Fried', 100, 'g', 1, 'serving', 196, 14, 1.1, 15, 196, 14, 1.1, 15, 1, 0, 1, 'egg_fried_a', 'egg_fried_b', 'egg_fried_c', 'Egg Fried'");
        setupInsertToFood("13, 'Egg Scrambled', 100, 'g', 1, 'serving', 196, 14, 1.1, 15, 196, 14, 1.1, 15, 1, 0, 1, 'egg_scrambled_a', 'egg_scrambled_b', 'egg_scrambled_c', 'Egg Scrambled'");
        setupInsertToFood("14, 'Egg Omelette', 100, 'g', 1, 'serving', 196, 14, 1.1, 15, 196, 14, 1.1, 15, 1, 0, 1, 'egg_omelette_a', 'egg_omelette_b', 'egg_omelette_c', 'Egg Omelette'");
        setupInsertToFood("15, 'Egg Poached', 100, 'g', 1, 'serving', 196, 14, 1.1, 15, 196, 14, 1.1, 15, 1, 0, 1, 'egg_poached_a', 'egg_poached_b', 'egg_poached_c', 'Egg Poached'");
        setupInsertToFood("16, 'Egg Raw', 100, 'g', 1, 'serving', 143, 12, 1, 10, 143, 12, 1, 10, 1, 0, 1, 'egg_raw_a', 'egg_raw_b', 'egg_raw_c', 'Egg Raw'");
        setupInsertToFood("17, 'Ribeye', 100, 'g', 1, 'serving', 258, 17, 0, 21, 258, 17, 0, 21, 1, 0, 1, 'ribeye_a', 'ribeye_b', 'ribeye_c', 'Ribeye'");
        setupInsertToFood("18, 'Sirloin', 100, 'g', 1, 'serving', 176, 26, 0, 7.7, 176, 26, 0, 7.7, 1, 0, 1, 'sirloin_a', 'sirloin_b', 'sirloin_c', 'Sirloin'");
        setupInsertToFood("19, 'T-Bone', 100, 'g', 1, 'serving', 271, 18, 0, 22, 271, 18, 0, 22, 1, 0, 1, 't_bone_a', 't_bone_b', 't_bone_c', 'T-Bone'");
        setupInsertToFood("20, 'Porterhouse', 100, 'g', 1, 'serving', 320, 17, 0, 28, 320, 17, 0, 28, 1, 0, 1, 'porterhouse_a', 'porterhouse_b', 'porterhouse_c', 'Porterhouse'");
    }


    //insert example user into database
    public void insertAllUsers(){
        DBAdapter db = new DBAdapter(context);
        db.open();

        //TODO
        //db.insert("")
        db.close();
    }

}
