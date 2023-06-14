package com.example.learningandroidstudio;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learningandroidstudio.Bhajans_Songs.BhajanDashboard;
import com.example.learningandroidstudio.Utils.CustomIntents.customIntents;
import com.example.learningandroidstudio.Utils.MySharedPreferences;
import com.example.learningandroidstudio.Utils.constants.Constants;

public class SelectProduct extends AppBarNavigation{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        ImageView BackButton= findViewById(R.id.AppBarBackMenu);

        if (MySharedPreferences.getLoginStatus())
            BackButton.setVisibility(View.INVISIBLE);
        else
            BackButton.setVisibility(View.VISIBLE);

//        MySharedPreferences.setDeityFilter(Constants.DeityFilterAll);

//        Delete later
//            Toast.makeText(this, "Enjoy singing Bhajans 🎵", Toast.LENGTH_SHORT).show();
//            Intent intent= new Intent(this, BhajanDashboard.class);
//            startActivity(intent);
    }

    private void openYoutubePage(){
        Intent webIntent= customIntents.openURL(Constants.saiPratibimba);
        try {
            startActivity(webIntent);
        } catch (ActivityNotFoundException ex) {
            Log.e("IntentError", "Error while opening the URL: "+Constants.saiPratibimba);
        }
    }

    private void sendEmail(){
        String subject= "Testing Email";
        String body= "Just sending a test email";
        String [] address= {"ashikthulungrai7@gmail.com", "AshikR1@verifone.com"};

        Intent sendMail= customIntents.sendEmail(subject, body);
        try {
            startActivity(sendMail);
        } catch (ActivityNotFoundException ex) {
            Log.e("IntentError", "Error while sending the mail ");
            Log.e("IntentError","Subject:"+subject+" body: "+body);
        }
    }

    public void NavigateToBhajan(View v){
        Toast.makeText(this, "Enjoy singing Bhajans 🎵", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, BhajanDashboard.class);
        startActivity(intent);
    }

    public void NavigateToVedam(View v){
        Toast.makeText(this, "Enjoy chanting Vedams 🧾", Toast.LENGTH_SHORT).show();
        openYoutubePage();
    }

    public void NavigateToSongs(View v){
        Toast.makeText(this, "Enjoy the Melody 🎹", Toast.LENGTH_SHORT).show();
        openYoutubePage();
    }

    public void NavigateToYoutube(View v){
        Toast.makeText(this, "Welcome to Sai Pratibimba 📽", Toast.LENGTH_SHORT).show();
        openYoutubePage();
    }
}