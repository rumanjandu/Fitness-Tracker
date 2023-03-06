package com.example.fitnesstracker;

public class User {
    private int userId;
    private String username;
    private String password;
    private int height;
    private int weight;
    private int age;

    public User() {
        // Empty constructor required by SQLiteOpenHelper
    }

    public User(String username, String password, int height, int weight, int age) {
        this.username = username;
        this.password = password;
        this.height = height;
        this.weight = weight;
        this.age = age;
    }

    public String getUserValues(){
        return "'" + username + "', '" + password + "', " + height + ", " + weight + ", " + age;
    }

    public String getFields(){
        return "username, password, height, weight, age";
    }

    // Getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    //Databas stuff delete if not needed
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDatabase";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_AGE = "age";


}