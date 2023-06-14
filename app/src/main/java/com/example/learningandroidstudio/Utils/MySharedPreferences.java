package com.example.learningandroidstudio.Utils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learningandroidstudio.Utils.constants.Constants;

import java.util.ArrayList;

public class MySharedPreferences {
    public static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;

    public static void createSharedPreference() {
        spEditor= sp.edit();
    }

    public static void clearSharedPreferences() {
        if (spEditor == null)
            return;
        spEditor.clear();
        spEditor.apply();
    }

    public static void setFavouriteChanged(boolean flag){
        spEditor.putBoolean(Constants.favouriteListChanged, flag);
        spEditor.apply();
    }

    public static boolean getFavouriteChanged(){
        return sp.getBoolean(Constants.favouriteListChanged, false);
    }

//    public static void setDeityData(String username, int userId) {
//        Log.d("SharedPref", "username: "+username+" id: "+userId);
//        spEditor.put
//        spEditor.apply();
//    }
//
//
//    public static ArrayList<Dei> getDeityData(String username, int userId) {
//        Log.d("SharedPref", "username: "+username+" id: "+userId);
//        spEditor.putString(Constants.userNamePref, username);
//        spEditor.putInt(Constants.userIdPref, userId);
//        spEditor.apply();
//    }


    public static void setUserDetails(String username, int userId) {
        Log.d("SharedPref", "username: "+username+" id: "+userId);
        spEditor.putString(Constants.userNamePref, username);
        spEditor.putInt(Constants.userIdPref, userId);
        spEditor.apply();
    }

    public static String getUserNamePref() {
        return sp.getString(Constants.userNamePref, "");
    }

    public static int getUserIdPref() {
        return sp.getInt(Constants.userIdPref, -1);
    }

    public static void setLoginStatus(boolean flag) {
        Log.d("SharedPref", "Setting login status to "+flag);
        spEditor.putBoolean(Constants.LoginPref, flag);
        spEditor.apply();
    }

    public static boolean getLoginStatus() {
        try {
            return sp.getBoolean(Constants.LoginPref, false);
        }catch (NullPointerException ex) {
            return false;
        }
    }

}
