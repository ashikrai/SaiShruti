package com.example.saishruti.Bhajans_Songs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saishruti.AppBarNavigation;
import com.example.saishruti.DataContainer.BhajanDataContainer;
import com.example.saishruti.DatabaseHandler.DBHandler;
import com.example.saishruti.DatabaseHandler.DBModal.BhajanModal;
import com.example.saishruti.R;
import com.example.saishruti.Utils.MySharedPreferences;
import com.example.saishruti.Utils.constants.Constants;

public class DisplayBhajanData extends AppBarNavigation {

    private TextView BhajanName;
    private TextView DeityName;
    private TextView BhajanLyrics;
    private BhajanModal bhajanData;
    private String BhajanContributor;
    private ImageView deityDataImage;
    private String result;
    private BhajanDataContainer bhajanDataContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bhajan_data);
        bhajanDataContainer= BhajanDataContainer.getInstance();

        Intent intent= getIntent();
        BhajanName= findViewById(R.id.bhajanData_Title);
        DeityName= findViewById(R.id.bhajanData_Deity);
        BhajanLyrics= findViewById(R.id.bhajanData_Lyrics);
        deityDataImage = findViewById(R.id.deityDataImage);

        BhajanContributor= null;

        BhajanName.setText(intent.getStringExtra(getResources().getString(R.string.openBhajanNameIntent)));
        DeityName.setText(intent.getStringExtra(getResources().getString(R.string.selectedDeityIntent))+" Bhajan");
        findViewById(R.id.bhajanDataInfoLayout).setVisibility(View.GONE);

        Bitmap bitmap;
        if (bhajanDataContainer.getSelectedDeity() != null){
            bitmap = BitmapFactory.decodeByteArray(bhajanDataContainer.getSelectedDeity().getDeityImage(), 0, bhajanDataContainer.getSelectedDeity().getDeityImage().length);
            deityDataImage.setImageBitmap(bitmap);
        }

        getBhajanData();
        ((TextView) findViewById(R.id.LikeCountMessage)).setText(String.valueOf(bhajanData.getBhajanLikeCount())+" Likes");

    }

    public void changeFavouriteStatus(View v){
        Log.d("BhajanData","Changing the Favorite Status of item");
        DBHandler dbHandler= new DBHandler(this);
        int bhajanId= Integer.parseInt(getIntent().getStringExtra(getResources().getString(R.string.openBhajanIdIntent)));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                result= dbHandler.markItemFavourite(MySharedPreferences.getUserIdPref(), bhajanId, Constants.FavItemBhajan, getIntent().getStringExtra(getResources().getString(R.string.openBhajanNameIntent)));
            }
        });
        if(!result.equals(Constants.Success))
            Toast.makeText(this, result+"😢", Toast.LENGTH_SHORT).show();
    }

    private void getBhajanData() {
        int bhajanId= Integer.parseInt(getIntent().getStringExtra(getResources().getString(R.string.openBhajanIdIntent)));
        DBHandler dbHandler= new DBHandler(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bhajanData= dbHandler.getBhajanData(bhajanId);
            }
        });
        if (bhajanData == null) {
            Toast.makeText(this, "Error while fetching the bhajanData", Toast.LENGTH_SHORT).show();
            return;
        }
        BhajanLyrics.setText(bhajanData.getBhajanLyrics());

    }

    public void showMoreBhajanData(View v) {
        DBHandler dbHandler= new DBHandler(this);
        ImageButton infoOpen= ((ImageButton)findViewById(R.id.bhajanDataInfoOpen));
        ImageButton infoClose= ((ImageButton)findViewById(R.id.bhajanDataInfoClose));
        LinearLayout infoLayout= findViewById(R.id.bhajanDataInfoLayout);

        if (BhajanContributor == null ) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BhajanContributor= dbHandler.getBhajanContributerName(bhajanData.getBhajanContributedBy());
                }
            });
            ((TextView) findViewById(R.id.bhajanDataRaag)).setText(bhajanData.getBhajanRaag());
            ((TextView) findViewById(R.id.bhajanData_Type)).setText(bhajanData.getBhajanCategory());
            ((TextView) findViewById(R.id.bhajanData_Satus)).setText(bhajanData.getBhajanStatus());
            ((TextView) findViewById(R.id.bhajanData_LikeCount)).setText(String.valueOf(bhajanData.getBhajanLikeCount()));
            ((TextView) findViewById(R.id.bhajanData_Contributer)).setText(BhajanContributor == null ? "admin" : BhajanContributor);
        }

        if (infoOpen.getVisibility() == View.VISIBLE) {
            infoOpen.setVisibility(View.GONE);
            infoClose.setVisibility(View.VISIBLE);
            infoLayout.setVisibility(View.VISIBLE);
        } else {
            infoOpen.setVisibility(View.VISIBLE);
            infoClose.setVisibility(View.GONE);
            infoLayout.setVisibility(View.GONE);
        }
    }
}