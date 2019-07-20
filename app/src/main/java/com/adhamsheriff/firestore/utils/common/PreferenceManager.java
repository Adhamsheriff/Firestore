package com.adhamsheriff.firestore.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    //    static SharedPreferences preferences;
    public static void setLogin(String login, Context context) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().putString("login", login).commit();
    }

    public static String getLogin(Context context) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return preferences.getString("login", "");
    }

    public static void setUserType(String userType, Context context) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().putString("userType", userType).commit();
    }

    public static String getUserType(Context context) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return preferences.getString("userType", "");
    }
}
