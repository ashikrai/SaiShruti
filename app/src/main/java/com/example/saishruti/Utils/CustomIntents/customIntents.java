package com.example.saishruti.Utils.CustomIntents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;

public class customIntents extends AppCompatActivity {

    public static Intent openURL(String url){
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        return webIntent;
    }

    public static Intent sendEmail(String subject, String body){
        String [] address= {"ashikthulungrai7@gmail.com", "AshikR1@verifone.com"};
        Intent sendMail= new Intent(Intent.ACTION_SEND);
        sendMail.setType("*/*");
        sendMail.putExtra(Intent.EXTRA_EMAIL, address);
        sendMail.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendMail.putExtra(Intent.EXTRA_TEXT, body);
        return sendMail;
    }
}