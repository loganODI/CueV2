package com.example.loganodi.cuev2;

public class PreferenceHelper {

    final public static String KEY_DEMO_NAME = "Demo Name";

    public static void setName(String value, String key_name) {
        MainActivity.preferences.edit().putString(key_name, value ).commit();
    }
    public static String getName(String key_name) {
        return MainActivity.preferences.getString(key_name,"");
    }


}
