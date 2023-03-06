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
                "food_id, food_name, food_manufacturer, food_serving_size, food_serving_measurement, food_serving_name_number",
                        //" food_serving_name_word, food_energy, food_proteins, food_carbohydrates, food_fat, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calulated, food_fat_calculated, food_user_id, food_barcode, food_category_id, food_image_a, food_image_b, food_image_c, food_description",
                values);
        db.close();
    }

    public void insertAllFood(){
        setupInsertToFood("NULL, 'Testfood', 'Tesco', '100', 'g', '1'");
        setupInsertToFood("NULL, 'Steak', 'Gilde', '106.0', 'g', '232'");
        setupInsertToFood("NULL, 'Egg', 'Prior', '136.0', 'g', '211'");
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
