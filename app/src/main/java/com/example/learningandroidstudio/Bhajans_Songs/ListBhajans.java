package com.example.learningandroidstudio.Bhajans_Songs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningandroidstudio.AppBarNavigation;
import com.example.learningandroidstudio.Bhajans_Songs.CustomRecyclerAdapter.bhajanListCustomRecyclerAdapter;
import com.example.learningandroidstudio.Bhajans_Songs.CustomRecyclerAdapter.deityListCustomRecyclerAdapter;
import com.example.learningandroidstudio.Bhajans_Songs.contribute_activity.ContributeActivity;
import com.example.learningandroidstudio.DatabaseHandler.DBHandler;
import com.example.learningandroidstudio.DatabaseHandler.DBModal.BhajanLiteModal;
import com.example.learningandroidstudio.R;
import com.example.learningandroidstudio.Utils.constants.Constants;

import java.util.ArrayList;

public class ListBhajans extends AppBarNavigation implements AdapterView.OnItemClickListener {

    private int initialIndex;
    private ArrayList<BhajanLiteModal> bhajanLiteData;
    private RecyclerView rcView;
    private bhajanListCustomRecyclerAdapter CRAdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bhajans);
        Intent inIntent= getIntent();
        String selectedDeity,selectedDeityId;
        selectedDeity= inIntent.getStringExtra(getResources().getString(R.string.selectedDeityIntent));
        selectedDeityId= inIntent.getStringExtra(getResources().getString(R.string.selectedDeityIdIntent));
        initialIndex= 0;

        TextView slogan;
        slogan= findViewById(R.id.AppBar_Slogan);
        slogan.setText(selectedDeity+" Bhajans ♥ ");

        bhajanLiteData= new ArrayList<>();
        populateListView(Integer.parseInt(selectedDeityId));
    }

    private void populateListView(int selectedDeityID){
        Log.d("BhajanList", "Getting bhajan for Deity Id "+selectedDeityID);
        DBHandler dbhandler= new DBHandler(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bhajanLiteData= dbhandler.getBhajanByDeityId(selectedDeityID, initialIndex, initialIndex + Constants.dbFetchSize);

                if (bhajanLiteData.size() == 0 ){
                    findViewById(R.id.NoBhajanLayout).setVisibility(View.VISIBLE);
                    findViewById(R.id.rcViewLayout).setVisibility(View.GONE);
                    return;
                }
                findViewById(R.id.NoBhajanLayout).setVisibility(View.GONE);
                findViewById(R.id.rcViewLayout).setVisibility(View.VISIBLE);
                CRAdt= new bhajanListCustomRecyclerAdapter(bhajanLiteData, ListBhajans.this);

                rcView= findViewById(R.id.bhajanListRecyclerView);
                rcView.setLayoutManager(new LinearLayoutManager(ListBhajans.this));
                rcView.setAdapter(CRAdt);
                rcView.setHasFixedSize(true);
            }
        });

    }

    public void abbBhajanContributeActivity (View v) {
        Log.d("BhajanList", "Navigating to addBhajan Activity");
        Intent addBhajanIntent= new Intent(this, ContributeActivity.class);
        addBhajanIntent.putExtra(getResources().getString(R.string.ContributeTypeIntent),"Bhajan");
        addBhajanIntent.putExtra(getResources().getString(R.string.DeityNameIntent),getIntent().getStringExtra(getResources().getString(R.string.selectedDeityIntent)));
        startActivity(addBhajanIntent);
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("BhajanList","Clicked "+bhajanLiteData.get(position).getBhajanName()+" "+bhajanLiteData.get(position).getBhajanId()+" "+position+" "+bhajanLiteData.size());
        Intent openBhajan= new Intent(this, DisplayBhajanData.class);
        openBhajan.putExtra(getResources().getString(R.string.openBhajanIdIntent), String.valueOf(bhajanLiteData.get(position).getBhajanId()));
        openBhajan.putExtra(getResources().getString(R.string.openBhajanNameIntent), bhajanLiteData.get(position).getBhajanName());
        openBhajan.putExtra(getResources().getString(R.string.selectedDeityIntent), getIntent().getStringExtra(getResources().getString(R.string.selectedDeityIntent)));
        startActivity(openBhajan);
    }

}