package com.example.fitnesstracker;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    // Variables
    private static final String databaseName = "fitnesstrack";
    private static final int databaseVersion = 23;

    // Database Variables
    private final Context context;
    private static DatabaseHelper DBHelper;
    private static SQLiteDatabase db;

    //Class DBAdapter
    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    //open database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //close database
    public void close() {
        DBHelper.close();
    }


    public void insert(String table, String fields, String values) {

        db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")");
    }

    public void insertUser(User user){
        this.open();
        db.execSQL("INSERT INTO users" + "(" + user.getFields() + ") VALUES (" + user.getUserValues() + ")");
        this.close();
    }

    public boolean checkUserExists(String username){
        this.open();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username = '" + username + "'", null);
        if(c.moveToFirst()){
            do{
             return true;
            }while(c.moveToNext());
        }
        this.close();
        return false;
    }

    public int count(String table){
        Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }


    //Database Helper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);

        }
        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL("CREATE TABLE IF NOT EXISTS food_diary_cal_eaten (" +
                        " calorie_eaten_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " calorie_eaten_date DATE," +
                        " calorie_eaten_meal_no INT," +
                        " calorie_eaten_energy INT," +
                        " calorie_eaten_proteins INT," +
                        " calorie_eaten_carbs INT," +
                        " calorie_eaten_fat INT);");


                db.execSQL("CREATE TABLE IF NOT EXISTS food_diary(" +
                        " fd_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " fd_date DATE," +
                        " fd_meal_number INT," +
                        " fd_food_id," +
                        " fd_serving_size DOUBLE," +
                        " fd_serving_measurement VARCHAR," +
                        " fd_energy_calculated DOUBLE," +
                        " fd_protein_calculated DOUBLE," +
                        " fd_carbohydrates_calculated DOUBLE," +
                        " fd_fat_calculated DOUBLE," +
                        " fd_user_id INT," +
                        "fd_meal_id INT);");



                db.execSQL("CREATE TABLE IF NOT EXISTS food ("
                        +  " food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            " food_name VARCHAR," +
                            " food_manufacturer VARCHAR," +
                            " food_serving_size DOUBLE," +
                            " food_serving_measurement VARCHAR," +
                            " food_serving_name_number DOUBLE," +
                            " food_serving_name_word VARCHAR," +
                            " food_energy DOUBLE," +
                            " food_proteins DOUBLE," +
                            " food_carbohydrates DOUBLE," +
                            " food_fat DOUBLE," +
                            " food_energy_calculated DOUBLE," +
                            " food_proteins_calculated DOUBLE," +
                            " food_carbohydrates_calulated DOUBLE," +
                            " food_fat_calculated DOUBLE," +
                            " food_user_id INT," +
                            " food_barcode VARCHAR," +
                            " food_category_id INT," +
                            " food_image_a VARCHAR," +
                            " food_image_b VARCHAR," +
                            " food_image_c VARCHAR," +
                            " food_description VARCHAR);");

                db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                        " user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " user_email VARCHAR," +
                        " user_password VARCHAR," +
                        " user_alias VARCHAR," +
                        " user_dob DATE," +
                        " user_gender VARCHAR," +
                        " user_location VARCHAR," +
                        " user_height DOUBLE," +
                        " user_weight DOUBLE," +
                        " user_activity_level VARCHAR," +
                        " user_goal_weight DOUBLE," +
                        " user_goal_date DATE," +
                        " user_goal_calories DOUBLE," +
                        " user_last_seen TIME," +
                        " user_age INT);");



            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS food_diary_cal_eaten");
            db.execSQL("DROP TABLE IF EXISTS food_diary");
            db.execSQL("DROP TABLE IF EXISTS food");
            onCreate(db);
            String TAG = "TAG";
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
            + ", which will destroy all old data");
        }

    }
}
