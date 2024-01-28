package com.example.saishruti.Utils;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import com.example.saishruti.Utils.constants.Constants;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class Utility {
    private Utility () {}

    public static String encryptPassword(String Password) {
        Log.d("Database","Encrypting Password");
        String EncryptedPassword="", Message= Constants.secureEncryptionMessage;
        try {
            EncryptedPassword= AESCrypt.encrypt(Password, Message);
        } catch (GeneralSecurityException ex) {
            Log.e("Database", "Error while encrypting the password");
        }
        Log.d("Database", "Encrypted password "+EncryptedPassword);
        return EncryptedPassword;
    }

    public static void vibrate(Vibrator vibrator) {
//        vibrator.vibrate(100);
        if (Build.VERSION.SDK_INT >= 26)
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        else
            vibrator.vibrate(200);
    }
}
