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
        setupInsertToFood("21, 'Tenderloin', 100, 'g', 1, 'serving', 143, 21, 0, 6, 143, 21, 0, 6, 1, 0, 1, 'tenderloin_a', 'tenderloin_b', 'tenderloin_c', 'Tenderloin'");
        setupInsertToFood("22, 'Ground Beef', 100, 'g', 1, 'serving', 254, 18, 0, 20, 254, 18, 0, 20, 1, 0, 1, 'ground_beef_a', 'ground_beef_b', 'ground_beef_c', 'Ground Beef'");
        setupInsertToFood("23, 'Pasta', 100, 'g', 1, 'serving', 131, 5.5, 0.7, 1.1, 131, 5.5, 0.7, 1.1, 1, 0, 1, 'pasta_a', 'pasta_b', 'pasta_c', 'Pasta'");
        setupInsertToFood("24, 'Rice', 100, 'g', 1, 'serving', 130, 2.7, 0.3, 2.7, 130, 2.7, 0.3, 2.7, 1, 0, 1, 'rice_a', 'rice_b', 'rice_c', 'Rice'");
        setupInsertToFood("25, 'Potato', 100, 'g', 1, 'serving', 77, 1.6, 0.1, 1.8, 77, 1.6, 0.1, 1.8, 1, 0, 1, 'potato_a', 'potato_b', 'potato_c', 'Potato'");
        setupInsertToFood("26, 'Sweet Potato', 100, 'g', 1, 'serving', 86, 1.6, 0.1, 2, 86, 1.6, 0.1, 2, 1, 0, 1, 'sweet_potato_a', 'sweet_potato_b', 'sweet_potato_c', 'Sweet Potato'");
        setupInsertToFood("27, 'Broccoli', 100, 'g', 1, 'serving', 34, 2.8, 0.4, 2.8, 34, 2.8, 0.4, 2.8, 1, 0, 1, 'broccoli_a', 'broccoli_b', 'broccoli_c', 'Broccoli'");
        setupInsertToFood("28, 'Carrot', 100, 'g', 1, 'serving', 41, 0.9, 0.2, 9.6, 41, 0.9, 0.2, 9.6, 1, 0, 1, 'carrot_a', 'carrot_b', 'carrot_c', 'Carrot'");
        setupInsertToFood("29, 'Cauliflower', 100, 'g', 1, 'serving', 25, 1.9, 0.3, 2, 25, 1.9, 0.3, 2, 1, 0, 1, 'cauliflower_a', 'cauliflower_b', 'cauliflower_c', 'Cauliflower'");
        setupInsertToFood("30, 'Corn', 100, 'g', 1, 'serving', 86, 3.3, 1.2, 19, 86, 3.3, 1.2, 19, 1, 0, 1, 'corn_a', 'corn_b', 'corn_c', 'Corn'");
        setupInsertToFood("31, 'Cucumber', 100, 'g', 1, 'serving', 15, 0.7, 0.1, 3.6, 15, 0.7, 0.1, 3.6, 1, 0, 1, 'cucumber_a', 'cucumber_b', 'cucumber_c', 'Cucumber'");
        setupInsertToFood("32, 'Lettuce', 100, 'g', 1, 'serving', 15, 1.4, 0.2, 2.9, 15, 1.4, 0.2, 2.9, 1, 0, 1, 'lettuce_a', 'lettuce_b', 'lettuce_c', 'Lettuce'");
        setupInsertToFood("33, 'Mushroom', 100, 'g', 1, 'serving', 22, 3.1, 0.3, 3.1, 22, 3.1, 0.3, 3.1, 1, 0, 1, 'mushroom_a', 'mushroom_b', 'mushroom_c', 'Mushroom'");
        setupInsertToFood("34, 'Onion', 100, 'g', 1, 'serving', 40, 1.1, 0.1, 9.3, 40, 1.1, 0.1, 9.3, 1, 0, 1, 'onion_a', 'onion_b', 'onion_c', 'Onion'");
        setupInsertToFood("35, 'Peas', 100, 'g', 1, 'serving', 81, 5.4, 0.4, 14, 81, 5.4, 0.4, 14, 1, 0, 1, 'peas_a', 'peas_b', 'peas_c', 'Peas'");
        setupInsertToFood("36, 'Pepper', 100, 'g', 1, 'serving', 20, 0.9, 0.2, 4.2, 20, 0.9, 0.2, 4.2, 1, 0, 1, 'pepper_a', 'pepper_b', 'pepper_c', 'Pepper'");
        setupInsertToFood("37, 'Spinach', 100, 'g', 1, 'serving', 23, 2.9, 0.4, 2.9, 23, 2.9, 0.4, 2.9, 1, 0, 1, 'spinach_a', 'spinach_b', 'spinach_c', 'Spinach'");
        setupInsertToFood("38, 'Tomato', 100, 'g', 1, 'serving', 18, 0.9, 0.2, 3.9, 18, 0.9, 0.2, 3.9, 1, 0, 1, 'tomato_a', 'tomato_b', 'tomato_c', 'Tomato'");
        setupInsertToFood("39, 'Apple', 100, 'g', 1, 'serving', 52, 0.3, 0.2, 13.8, 52, 0.3, 0.2, 13.8, 1, 0, 1, 'apple_a', 'apple_b', 'apple_c', 'Apple'");
        setupInsertToFood("40, 'Banana', 100, 'g', 1, 'serving', 89, 1.1, 0.3, 22.8, 89, 1.1, 0.3, 22.8, 1, 0, 1, 'banana_a', 'banana_b', 'banana_c', 'Banana'");
        setupInsertToFood("41, 'Grapefruit', 100, 'g', 1, 'serving', 42, 0.8, 0.1, 10.4, 42, 0.8, 0.1, 10.4, 1, 0, 1, 'grapefruit_a', 'grapefruit_b', 'grapefruit_c', 'Grapefruit'");
        setupInsertToFood("42, 'Grapes', 100, 'g', 1, 'serving', 69, 0.6, 0.3, 18.1, 69, 0.6, 0.3, 18.1, 1, 0, 1, 'grapes_a', 'grapes_b', 'grapes_c', 'Grapes'");
        setupInsertToFood("43, 'Lemon', 100, 'g', 1, 'serving', 29, 1.1, 0.3, 9.3, 29, 1.1, 0.3, 9.3, 1, 0, 1, 'lemon_a', 'lemon_b', 'lemon_c', 'Lemon'");
        setupInsertToFood("44, 'Lime', 100, 'g', 1, 'serving', 30, 0.7, 0.2, 10.5, 30, 0.7, 0.2, 10.5, 1, 0, 1, 'lime_a', 'lime_b', 'lime_c', 'Lime'");
        setupInsertToFood("45, 'Orange', 100, 'g', 1, 'serving', 47, 0.9, 0.1, 11.8, 47, 0.9, 0.1, 11.8, 1, 0, 1, 'orange_a', 'orange_b', 'orange_c', 'Orange'");
        setupInsertToFood("46, 'Peach', 100, 'g', 1, 'serving', 39, 0.9, 0.3, 9.5, 39, 0.9, 0.3, 9.5, 1, 0, 1, 'peach_a', 'peach_b', 'peach_c', 'Peach'");
        setupInsertToFood("47, 'Pear', 100, 'g', 1, 'serving', 57, 0.4, 0.3, 15.2, 57, 0.4, 0.3, 15.2, 1, 0, 1, 'pear_a', 'pear_b', 'pear_c', 'Pear'");
        setupInsertToFood("48, 'Pineapple', 100, 'g', 1, 'serving', 50, 0.5, 0.1, 13.1, 50, 0.5, 0.1, 13.1, 1, 0, 1, 'pineapple_a', 'pineapple_b', 'pineapple_c', 'Pineapple'");
        setupInsertToFood("49, 'Plum', 100, 'g', 1, 'serving', 46, 0.7, 0.3, 11.6, 46, 0.7, 0.3, 11.6, 1, 0, 1, 'plum_a', 'plum_b', 'plum_c', 'Plum'");
        setupInsertToFood("50, 'Raisins', 100, 'g', 1, 'serving', 299, 3.1, 0.5, 79.2, 299, 3.1, 0.5, 79.2, 1, 0, 1, 'raisins_a', 'raisins_b', 'raisins_c', 'Raisins'");
        setupInsertToFood("51, 'Strawberry', 100, 'g', 1, 'serving', 32, 0.7, 0.3, 7.7, 32, 0.7, 0.3, 7.7, 1, 0, 1, 'strawberry_a', 'strawberry_b', 'strawberry_c', 'Strawberry'");
        setupInsertToFood("52, 'Watermelon', 100, 'g', 1, 'serving', 30, 0.6, 0.2, 7.6, 30, 0.6, 0.2, 7.6, 1, 0, 1, 'watermelon_a', 'watermelon_b', 'watermelon_c', 'Watermelon'");
        setupInsertToFood("53, 'Asparagus', 100, 'g', 1, 'serving', 20, 2.2, 0.1, 3.9, 20, 2.2, 0.1, 3.9, 1, 0, 1, 'asparagus_a', 'asparagus_b', 'asparagus_c', 'Asparagus'");
        setupInsertToFood("54, 'Broccoli', 100, 'g', 1, 'serving', 34, 2.8, 0.4, 6.6, 34, 2.8, 0.4, 6.6, 1, 0, 1, 'broccoli_a', 'broccoli_b', 'broccoli_c', 'Broccoli'");
        setupInsertToFood("55, 'Carrot', 100, 'g', 1, 'serving', 41, 0.9, 0.2, 9.6, 41, 0.9, 0.2, 9.6, 1, 0, 1, 'carrot_a', 'carrot_b', 'carrot_c', 'Carrot'");
        setupInsertToFood("56, 'Celery', 100, 'g', 1, 'serving', 16, 0.7, 0.2, 3.5, 16, 0.7, 0.2, 3.5, 1, 0, 1, 'celery_a', 'celery_b', 'celery_c', 'Celery'");
        setupInsertToFood("57, 'Corn', 100, 'g', 1, 'serving', 86, 3.3, 1.2, 19.0, 86, 3.3, 1.2, 19.0, 1, 0, 1, 'corn_a', 'corn_b', 'corn_c', 'Corn'");
        setupInsertToFood("58, 'Cucumber', 100, 'g', 1, 'serving', 15, 0.6, 0.1, 3.6, 15, 0.6, 0.1, 3.6, 1, 0, 1, 'cucumber_a', 'cucumber_b', 'cucumber_c', 'Cucumber'");
        setupInsertToFood("59, 'Green Pepper', 100, 'g', 1, 'serving', 20, 0.9, 0.2, 4.7, 20, 0.9, 0.2, 4.7, 1, 0, 1, 'green_pepper_a', 'green_pepper_b', 'green_pepper_c', 'Green Pepper'");
        setupInsertToFood("60, 'Bread', 100, 'g', 1, 'serving', 265, 9.4, 3.3, 44.1, 265, 9.4, 3.3, 44.1, 1, 0, 1, 'bread_a', 'bread_b', 'bread_c', 'Bread'");
        setupInsertToFood("61, 'Couscous', 100, 'g', 1, 'serving', 112, 3.8, 0.6, 23.2, 112, 3.8, 0.6, 23.2, 1, 0, 1, 'couscous_a', 'couscous_b', 'couscous_c', 'Couscous'");
        setupInsertToFood("62, 'Oatmeal', 100, 'g', 1, 'serving', 68, 2.4, 1.4, 12.3, 68, 2.4, 1.4, 12.3, 1, 0, 1, 'oatmeal_a', 'oatmeal_b', 'oatmeal_c', 'Oatmeal'");
        setupInsertToFood("63, 'Lamb', 100, 'g', 1, 'serving', 294, 25.6, 18.0, 0.0, 294, 25.6, 18.0, 0.0, 1, 0, 1, 'lamb_a', 'lamb_b', 'lamb_c', 'Lamb'");
        setupInsertToFood("64, 'Pork', 100, 'g', 1, 'serving', 242, 16.4, 7.9, 0.0, 242, 16.4, 7.9, 0.0, 1, 0, 1, 'pork_a', 'pork_b', 'pork_c', 'Pork'");
        setupInsertToFood("65, 'Turkey', 100, 'g', 1, 'serving', 189, 8.0, 2.4, 0.0, 189, 8.0, 2.4, 0.0, 1, 0, 1, 'turkey_a', 'turkey_b', 'turkey_c', 'Turkey'");
        setupInsertToFood("66, 'Nuts', 100, 'g', 1, 'serving', 607, 15.2, 54.5, 16.1, 607, 15.2, 54.5, 16.1, 1, 0, 1, 'nuts_a', 'nuts_b', 'nuts_c', 'Nuts'");
        setupInsertToFood("67, 'Peanut Butter', 100, 'g', 1, 'serving', 588, 25.1, 50.0, 20.0, 588, 25.1, 50.0, 20.0, 1, 0, 1, 'peanut_butter_a', 'peanut_butter_b', 'peanut_butter_c', 'Peanut Butter'");
        setupInsertToFood("68, 'Tofu', 100, 'g', 1, 'serving', 76, 4.8, 4.2, 1.9, 76, 4.8, 4.2, 1.9, 1, 0, 1, 'tofu_a', 'tofu_b', 'tofu_c', 'Tofu'");
        setupInsertToFood("69, 'Yogurt', 100, 'g', 1, 'serving', 59, 3.5, 3.3, 3.5, 59, 3.5, 3.3, 3.5, 1, 0, 1, 'yogurt_a', 'yogurt_b', 'yogurt_c', 'Yogurt'");
        setupInsertToFood("70, 'Milk', 100, 'g', 1, 'serving', 42, 3.4, 1.0, 4.7, 42, 3.4, 1.0, 4.7, 1, 0, 1, 'milk_a', 'milk_b', 'milk_c', 'Milk'");
        setupInsertToFood("71, 'Cheese', 100, 'g', 1, 'serving', 402, 25.4, 33.1, 1.3, 402, 25.4, 33.1, 1.3, 1, 0, 1, 'cheese_a', 'cheese_b', 'cheese_c', 'Cheese'");
        setupInsertToFood("72, 'Fish', 100, 'g', 1, 'serving', 206, 12.4, 5.1, 0.0, 206, 12.4, 5.1, 0.0, 1, 0, 1, 'fish_a', 'fish_b', 'fish_c', 'Fish'");
        setupInsertToFood("73, 'Salmon', 100, 'g', 1, 'serving', 208, 20.4, 13.4, 0.0, 208, 20.4, 13.4, 0.0, 1, 0, 1, 'salmon_a', 'salmon_b', 'salmon_c', 'Salmon'");

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
