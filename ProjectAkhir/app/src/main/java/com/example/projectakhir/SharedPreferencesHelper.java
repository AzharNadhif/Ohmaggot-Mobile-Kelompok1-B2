package com.example.projectakhir;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private static final String PREF_NAME = "user_data";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_AUTH_TOKEN = "auth_token";
    
    public static void saveUserData(Context context, String username, String password, String email) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public static void saveUserId(Context context, String id){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE).edit();
        editor.putString(KEY_USER_ID,id);
        editor.apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME,"");
    }

    public static String getPassword(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_PASSWORD,"");
    }

    public static String getEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return preferences.getString(KEY_EMAIL, "");
    }

    public static String getUserId(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return preferences.getString(KEY_USER_ID,"");

    }

    public static void saveUserName(Context context, String username) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public static String getUserName (Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME, "");
    }

    public static void saveUserEmail (Context context, String email){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public static String getUserEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_EMAIL, "");
    }

    public static void saveUserPassword(Context context, String password) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public static String getUserPassword(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_PASSWORD, "");
    }
    public static void saveAuthToken(Context context, String authToken) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_AUTH_TOKEN, authToken);
        editor.apply();
    }

    public static String getAuthToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_AUTH_TOKEN, "");
    }
}
