package com.example.learningandroidstudio.Bhajans_Songs;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.example.learningandroidstudio.AppBarNavigation;
import com.example.learningandroidstudio.DatabaseHandler.DBHandler;
import com.example.learningandroidstudio.DatabaseHandler.DBModal.DeityModal;
import com.example.learningandroidstudio.R;
import com.example.learningandroidstudio.Utils.constants.Constants;
import com.example.learningandroidstudio.Bhajans_Songs.CustomRecyclerAdapter.deityListCustomRecyclerAdapter;

import java.util.ArrayList;

public class BhajanDashboard extends AppBarNavigation implements AdapterView.OnItemClickListener {
    private RecyclerView rcView;
    private deityListCustomRecyclerAdapter CRAdt;
    ArrayList<DeityModal> deityData;
    ArrayList<DeityModal> filterDeityData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhajan_dashboard);

        TextView slogan= findViewById(R.id.AppBar_Slogan);
        slogan.setText("Select Deity 🎵");

        // Database
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DBHandler dbHandler = new DBHandler(BhajanDashboard.this);
                Log.d("Database", "Getting Deity DB");
                deityData= dbHandler.getDeityDataFromDB();
                filterDeityData = new ArrayList<>(deityData);
                CRAdt= new deityListCustomRecyclerAdapter(deityData, Constants.DeityImageData, BhajanDashboard.this);

                rcView= findViewById(R.id.DeityListRecyclerView);
                rcView.setLayoutManager(new GridLayoutManager(BhajanDashboard.this, 2));
                rcView.setAdapter(CRAdt);
                rcView.setHasFixedSize(true);
                rcView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    Log.d("scrollChange", String.format("OldX: %d NewX: %d OldY: %d NewY: %d",oldScrollX, scrollX, oldScrollY, scrollY));
                    if (oldScrollY < 0) {
                        Log.d("scrollChange", "Going Up");
                        toggleAppBar(false);
                    } else {
                        toggleAppBar(true);
                        Log.d("scrollChange", "Going down");
                    }
                });
        //        registerForContextMenu(rcView);
            }
        });

//        populateListView();
    }

    // Opening and closing the Filter Layout
    public void openCloseFilter(View v) {
        LinearLayout filterLayout= findViewById(R.id.filterLayout);
        ImageButton filterBtn= findViewById(R.id.FilterButton);
        if (filterLayout.getVisibility() == View.VISIBLE) {
            filterBtn.setImageResource(R.drawable.ic_baseline_filter_alt_off_24);
            filterLayout.setVisibility(View.GONE);
        } else {
            filterBtn.setImageResource(R.drawable.ic_baseline_filter_alt_24);
            filterLayout.setVisibility(View.VISIBLE);
        }
    }

    // Filterting the DeityList data based on the DeityCategory selected
    public void filterDeityList(String filter) {
        Log.d("DeityList","filter"+filter+" datasize "+deityData.size());
        filterDeityData = new ArrayList<>();
        for (int i = 0; i < deityData.size(); i++) {
            Log.d("DeityList","deity "+deityData.get(i).getDeityName()+" "+deityData.get(i).getDeityCategory());
            if (deityData.get(i).getDeityCategory().equals(Constants.DeityFilterSarwaDharma)  || deityData.get(i).getDeityCategory().equals(filter)) {
                Log.d("DeityList","Added deity "+deityData.get(i).getDeityName()+deityData.get(i).getDeityCategory());
                filterDeityData.add(deityData.get(i));
            }
        }
        Log.d("DeityList","filter"+filter+" datasize "+deityData.size()+" "+ filterDeityData.size());
    }


    // Applying the filter for listing the deity list
    public void setDeityFilter(View v) {
        Switch maleDeitySwitch= findViewById(R.id.MaleDeitySwitch);
        Switch femaleDeitySwitch= findViewById(R.id.FemaleDeitySwitch);
        Switch allDeitySwitch= findViewById(R.id.AllDeitySwitch);

      if (v.equals(maleDeitySwitch)) {
            filterDeityList(Constants.DeityFilterMale);
            maleDeitySwitch.setChecked(true);
            femaleDeitySwitch.setChecked(false);
            allDeitySwitch.setChecked(false);
        } else if (v.equals(femaleDeitySwitch)) {
            filterDeityList(Constants.DeityFilterFemale);
            femaleDeitySwitch.setChecked(true);
            maleDeitySwitch.setChecked(false);
            allDeitySwitch.setChecked(false);
        } else {
            Log.d("DeityList", "backup is NULL");
            filterDeityData = new ArrayList<>(deityData);
            Log.d("DeityList", "backup is full"+ filterDeityData.size());
            allDeitySwitch.setChecked(true);
            maleDeitySwitch.setChecked(false);
            femaleDeitySwitch.setChecked(false);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CRAdt= new deityListCustomRecyclerAdapter(filterDeityData, Constants.DeityImageData, BhajanDashboard.this);
                rcView= findViewById(R.id.DeityListRecyclerView);
                rcView.setLayoutManager(new GridLayoutManager(BhajanDashboard.this, 2));
                rcView.setAdapter(CRAdt);
                rcView.setHasFixedSize(true);
                //        registerForContextMenu(rcView);
            }
        });
    }

    private void populateListView() {

//        L MaledietyList= findViewById(R.id.DeityLinearList);
//        ArrayAdapter ad= new ArrayAdapter(this, android.R.layout.simple_list_item_1, Constants.MaleDeities);
//        MaledietyList.setAdapter(ad);
//
//        ListView feMaleDeityList= findViewById(R.id.feMaleDeityList);
//        ArrayAdapter ad1= new ArrayAdapter(this, android.R.layout.simple_list_item_1, Constants.FemaleDeities);
//        feMaleDeityList.setAdapter(ad1);
////
//        ListView otherList= findViewById(R.id.otherList);
//        ArrayAdapter ad2= new ArrayAdapter(this, android.R.layout.simple_list_item_1, Constants.MaleDeities);
//        otherList.setAdapter(ad2);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.item_context_menu, menu);
//        menu.setHeaderTitle("Select Action");
//
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
////        if (item.getItemId() == R.id.call)
//        return true;
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("DeityList","Clicked "+filterDeityData.get(position).getDeityName()+" "+filterDeityData.get(position).getDeityID()+" "+position+" "+filterDeityData.size());
        Intent listBhajan= new Intent(this, ListBhajans.class);
        listBhajan.putExtra(getResources().getString(R.string.selectedDeityIntent), filterDeityData.get(position).getDeityName());
        listBhajan.putExtra(getResources().getString(R.string.selectedDeityIdIntent), String.valueOf(filterDeityData.get(position).getDeityID()));
        startActivity(listBhajan);
    }
}