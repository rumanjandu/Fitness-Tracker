package com.example.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
    // Variables
    private static final String databaseName = "fitnesstrack";
    private static final int databaseVersion = 51;

    // Database Variables
    private final Context context;
    private static DatabaseHelper DBHelper;
    private static SQLiteDatabase db;

    //Class DBAdapter
    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /*public static void insertUserGoal(SQLiteDatabase db, int goalID, double targetWeightDouble,  String weeklyGoal) {
        ContentValues values = new ContentValues();
        values.put("goal_id", goalID);
        values.put("goal_target_weight", targetWeightDouble);
        values.put("goal_weekly_goal", weeklyGoal);
        db.update("goals", null, values);
    }*/

    public static boolean updateGoal(SQLiteDatabase db, int goalID, double targetWeightDouble,  String weeklyGoal, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("goal_target_weight", targetWeightDouble);
        contentValues.put("goal_weekly_goal", weeklyGoal);
        db.update("goals", contentValues, "goal_id = ?", new String[] {String.valueOf(goalID)});

        int rowsAffected = db.update("goals", contentValues, "goal_id = ?", new String[] {String.valueOf(goalID)});
        db.close();
        // Update was successful
        // Update failed
        return rowsAffected > 0;
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

    //quote smart
    public String quoteSmart(String value) {
        boolean isNumeric = false;
        try {
            Double num = Double.parseDouble(value);
            isNumeric = true;
        } catch (NumberFormatException e) {
            System.out.println("Could not parse " + e);
        }
        if (!isNumeric){
            //Escapes special characters in a string for use in an SQL statement
            if (value != null && value.length() > 0) {
                value = value.replace("\\", "\\\\");
                value = value.replace("'", "\\'");
                value = value.replace("\0", "\\0");
                value = value.replace("\n", "\\n");
                value = value.replace("\r", "\\r");
                value = value.replace("\"", "\\\"");
                value = value.replace("\\x1a", "\\Z");
            }
        }
        value = "'" + value + "'";

        return value;
    }

    public double quoteSmart(double value) {
        return value;
    }
    public int quoteSmart(int value) {
        return value;
    }




    public void insert(String table, String fields, String values) {

        db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values + ")");
    }

    public void insertUser(User user){
        this.open();
        db.execSQL("INSERT INTO users" + "(" + user.getFields() + ") VALUES (" + user.getUserValues() + ")");
        this.close();
    }

    // Check if a user with the given email and password exists
    public boolean checkUser(String email, String password) {
        this.open();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE user_email = ? AND user_password = ?", new String[]{email, password});
        boolean userExists = (cursor.getCount() > 0);
        cursor.close();
        this.close();
        return userExists;
    }

    public Cursor getUserGoals(String userEmail) {
        if (userEmail == null || userEmail.isEmpty()) {
            // Handle the case where userEmail is null or empty
            return null;
        }

        this.open();
        Cursor cursor = db.rawQuery("SELECT * FROM goals WHERE user_email = ?", new String[]{userEmail});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Logging statements to track column names and values
                String[] columnNames = cursor.getColumnNames();
                for (String columnName : columnNames) {
                    int columnIndex = cursor.getColumnIndex(columnName);
                    String columnValue = cursor.getString(columnIndex);
                    Log.d("DBAdapter", "Column Name: " + columnName + ", Value: " + columnValue);
                }
            } else {
                Log.d("DBAdapter", "Cursor is empty.");
            }
        } else {
            Log.d("DBAdapter", "Cursor is null.");
        }
        this.close();
        return cursor;
    }

    public boolean checkUserExists(String email) {
        this.open();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE user_email = ?", new String[]{email});
        boolean userExists = (c.getCount() > 0);
        c.close();
        this.close();
        return userExists;
    }

    public Cursor getFoodByQuery(String searchQuery) {
        if (searchQuery == null || searchQuery.isEmpty()) {
            // Handle the case where searchQuery is null or empty
            return null;
        }

        // Add wildcard characters to the search query
        String wildcardQuery = "%" + searchQuery + "%";

        this.open();
        Cursor cursor = db.rawQuery("SELECT * FROM food WHERE food_name LIKE ?", new String[]{wildcardQuery});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Logging statements to track column names and values
                int columnIndex;
                String columnValue;
                do {
                    columnIndex = cursor.getColumnIndex("food_name");
                    if (columnIndex != -1) {
                        columnValue = cursor.getString(columnIndex);
                        Log.d("DBAdapter", "Food Name: " + columnValue);
                    }

                    columnIndex = cursor.getColumnIndex("food_serving_size");
                    if (columnIndex != -1) {
                        columnValue = cursor.getString(columnIndex);
                        Log.d("DBAdapter", "Food Name: " + columnValue);
                    }

                    columnIndex = cursor.getColumnIndex("food_calories");
                    if (columnIndex != -1) {
                        columnValue = cursor.getString(columnIndex);
                        Log.d("DBAdapter", "Food Calories: " + columnValue);
                    }

                    columnIndex = cursor.getColumnIndex("food_energy");
                    if (columnIndex != -1) {
                        columnValue = cursor.getString(columnIndex);
                        Log.d("DBAdapter", "Food Energy: " + columnValue);
                    }

                    columnIndex = cursor.getColumnIndex("food_proteins");
                    if (columnIndex != -1) {
                        columnValue = cursor.getString(columnIndex);
                        Log.d("DBAdapter", "Food Proteins: " + columnValue);
                    }

                    columnIndex = cursor.getColumnIndex("food_carbohydrates");
                    if (columnIndex != -1) {
                        columnValue = cursor.getString(columnIndex);
                        Log.d("DBAdapter", "Food Carbohydrates: " + columnValue);
                    }

                    columnIndex = cursor.getColumnIndex("food_fat");
                    if (columnIndex != -1) {
                        columnValue = cursor.getString(columnIndex);
                        Log.d("DBAdapter", "Food Fat: " + columnValue);
                    }

                    Log.d("DBAdapter", "-------------------");
                } while (cursor.moveToNext());
            } else {
                Log.d("DBAdapter", "Cursor is empty.");
            }
        } else {
            Log.d("DBAdapter", "Cursor is null.");
        }
        return cursor;
    }
    // Retrieve user details by email
    public Cursor getUserByEmail(String userEmail) {
        if (userEmail == null || userEmail.isEmpty()) {
            // Handle the case where userEmail is null or empty
            return null;
        }

        this.open();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE user_email = ?", new String[]{userEmail});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Logging statements to track column names and values
                String[] columnNames = cursor.getColumnNames();
                for (String columnName : columnNames) {
                    int columnIndex = cursor.getColumnIndex(columnName);
                    String columnValue = cursor.getString(columnIndex);
                    Log.d("DBAdapter", "Column Name: " + columnName + ", Value: " + columnValue);
                }
            } else {
                Log.d("DBAdapter", "Cursor is empty.");
            }
        } else {
            Log.d("DBAdapter", "Cursor is null.");
        }
        this.close();
        return cursor;
    }


    public int count(String table){
        Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public Cursor selectPrimaryKey(String table, String primaryKey, long rowID, String[] fields) throws SQLException {
        Cursor mCursor = db.query(table, fields, primaryKey + "=" + rowID, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean update(String table, String primaryKey, long rowId, String field, double value) throws SQLException {
        ContentValues args = new ContentValues();
        args.put(field, value);
        return db.update(table, args, primaryKey + "=" + rowId, null) > 0;
    }

    public boolean update(String table, String primaryKey, long rowId, String field, String value) throws SQLException {
        // Toast.makeText(context, "UPDATE " + table + " SET " + field + "=" + value + " WHERE " + primaryKey + "=" + rowId, Toast.LENGTH_SHORT).show();

        // Remove first and last value of value
        value = value.substring(1, value.length()-1); // removes apostrophe after running quote smart

        ContentValues args = new ContentValues();
        args.put(field, value);
        return db.update(table, args, primaryKey + "=" + rowId, null) > 0;
    }


    //Database Helper
    public static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);

        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try {
                //Create goals table
                db.execSQL("CREATE TABLE IF NOT EXISTS goals (" +
                        " goal_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " goal_name VARCHAR," +
                        " goal_description VARCHAR," +
                        " goal_date DATE," +
                        " goal_current_weight DOUBLE," +
                        " goal_target_weight DOUBLE," +
                        " goal_kcal DOUBLE," +
                        " goal_energy DOUBLE," +
                        " goal_protein DOUBLE," +
                        " goal_carbs DOUBLE," +
                        " goal_fat DOUBLE," +
                        " user_email VARCHAR," +
                        " goal_weekly_goal DOUBLE);");

            }
            catch (SQLException e){
                e.printStackTrace();
            }

            //Create food calorie intake table
            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS food_diary_cal_eaten (" +
                        " calorie_eaten_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " calorie_eaten_date DATE," +
                        " calorie_eaten_meal_no INT," +
                        " calorie_eaten_energy INT," +
                        " calorie_eaten_proteins INT," +
                        " calorie_eaten_carbs INT," +
                        " calorie_eaten_fat INT);");
            }
            catch (SQLException e){
                e.printStackTrace();
            }

            //Create food diary table
            try {

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
            }
            catch (SQLException e){
                e.printStackTrace();
            }

            //Create food table
            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS food ("
                        +  " food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            " food_name VARCHAR," +
                            " food_serving_size DOUBLE," +
                            " food_serving_measurement VARCHAR," +
                            " food_serving_name_number INT," +
                            " food_serving_name_word VARCHAR," +
                            " food_calories DOUBLE," +
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
            }
            catch (SQLException e){
                e.printStackTrace();
            }

            //Create users table
            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                        " user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " user_email VARCHAR," +
                        " user_name VARCHAR," +
                        " user_password VARCHAR," +
                        " user_alias VARCHAR," +
                        " user_dob DATE," +
                        " user_gender VARCHAR," +
                        " user_location VARCHAR," +
                        " user_height DOUBLE," +
                        " user_activity_level VARCHAR," +
                        " user_goal_calories DOUBLE," +
                        " user_last_seen TIME," +
                        " user_measurement_system VARCHAR," +
                        " user_age INT);");



            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS food_diary_cal_eaten");
            db.execSQL("DROP TABLE IF EXISTS food_diary");
            db.execSQL("DROP TABLE IF EXISTS food");
            db.execSQL("DROP TABLE IF EXISTS goals");
            onCreate(db);
            String TAG = "TAG";
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
            + ", which will destroy all old data");
        }

    }
}
