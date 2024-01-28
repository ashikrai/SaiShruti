package com.example.saishruti.Bhajans_Songs;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.saishruti.AppBarNavigation;
import com.example.saishruti.DataContainer.BhajanDataContainer;
import com.example.saishruti.DatabaseHandler.DBHandler;
import com.example.saishruti.DatabaseHandler.DBModal.DeityModal;
import com.example.saishruti.R;
import com.example.saishruti.Utils.constants.Constants;
import com.example.saishruti.Bhajans_Songs.CustomRecyclerAdapter.deityListCustomRecyclerAdapter;

import java.util.ArrayList;

public class BhajanDashboard extends AppBarNavigation implements AdapterView.OnItemClickListener {
    private RecyclerView rcView;
    private deityListCustomRecyclerAdapter CRAdt;
    private BhajanDataContainer bhajanDataContainer;
    private ArrayList<DeityModal> filterDeityData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhajan_dashboard);
        bhajanDataContainer= BhajanDataContainer.getInstance();

        TextView slogan= findViewById(R.id.AppBar_Slogan);
        slogan.setText("Select Deity 🎵");
        int itemPerRowCount= 2;

        bhajanDataContainer.setSelectedDeity(null);

        // Database
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DBHandler dbHandler = new DBHandler(BhajanDashboard.this);
                Log.d("Database", "Getting Deity DB");
//                deityData= dbHandler.getDeityDataFromDB();
                bhajanDataContainer.setDeityData(dbHandler.getDeityDataFromDB());
                bhajanDataContainer.setDeityWiseBhajanCount(dbHandler.getDeityWistBhajanCount());
//                deityWiseBhajanCount= dbHandler.getDeityWistBhajanCount();

                filterDeityData = new ArrayList<>(bhajanDataContainer.getDeityData());
                CRAdt= new deityListCustomRecyclerAdapter(bhajanDataContainer.getDeityData(), bhajanDataContainer.getDeityWiseBhajanCount(), BhajanDashboard.this);

                rcView= findViewById(R.id.DeityListRecyclerView);
                rcView.setLayoutManager(new GridLayoutManager(BhajanDashboard.this, itemPerRowCount));
                rcView.setForegroundGravity(Gravity.CENTER);
                rcView.setAdapter(CRAdt);
                rcView.setHasFixedSize(true);

                ScrollView bhajanScroll= (ScrollView)findViewById(R.id.DeityListScrollBar);
                Log.d("scrollChange", "about to set the scroll listener");
                if (bhajanScroll != null) {
                    Log.d("scrollChange", "setting the scroll listener");
                    bhajanScroll.setOnScrollChangeListener(new View.OnScrollChangeListener(){
                        @Override
                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                            Log.d("scrollChange", String.format("OldX: %d NewX: %d OldY: %d NewY: %d",oldScrollX, scrollX, oldScrollY, scrollY));
                            if (scrollY > oldScrollY) {
                                toggleBaseNav(true);
                            } else if (scrollY < oldScrollY) {
                                toggleBaseNav(false);
                            }
                        }
                    });
                }

//                rcView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
//                    Log.d("scrollChange", String.format("OldX: %d NewX: %d OldY: %d NewY: %d",oldScrollX, scrollX, oldScrollY, scrollY));
//                    if (oldScrollY < 0) {
//                        Log.d("scrollChange", "Going Up");
//                        toggleAppBar(false);
//                    } else {
//                        Log.d("scrollChange", "Going down");
//                    }
//                });
        //        registerForContextMenu(rcView);
            }
        });

//        populateListView();
    }

    // Filterting the DeityList data based on the DeityCategory selected
    public void filterDeityList(String filter) {
        filterDeityData = new ArrayList<>();
        ArrayList<DeityModal> deityData= bhajanDataContainer.getDeityData();
        for (int i = 0; i < deityData.size(); i++) {
//            Log.d("DeityList","deity "+deityData.get(i).getDeityName()+" "+deityData.get(i).getDeityCategory());
            if (deityData.get(i).getDeityCategory().equals(Constants.DeityFilterSarwaDharma)  || deityData.get(i).getDeityCategory().equals(filter)) {
//                Log.d("DeityList","Added deity "+deityData.get(i).getDeityName()+deityData.get(i).getDeityCategory());
                filterDeityData.add(deityData.get(i));
            }
        }
//        Log.d("DeityList","filter"+filter+" datasize "+deityData.size()+" "+ filterDeityData.size());
    }


    // Applying the filter for listing the deity list
    public void setDeityFilter(String DeityType) {
        filterDeityList(DeityType);
        if (DeityType.equals(Constants.DeityFilterSarwaDharma)) {
            Log.d("DeityList", "backup is NULL");
            filterDeityData = new ArrayList<>(bhajanDataContainer.getDeityData());
            Log.d("DeityList", "backup is full"+ filterDeityData.size());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CRAdt= new deityListCustomRecyclerAdapter(filterDeityData,bhajanDataContainer.getDeityWiseBhajanCount(), BhajanDashboard.this);
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

    public void showMaleDeities(View view){
        setDeityFilter(Constants.DeityFilterMale);
        ((TextView) findViewById(R.id.allDeityText)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.femaleDeityText)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.maleDeityText)).setVisibility(View.VISIBLE);

        ((CardView)view).setCardBackgroundColor(getColor(R.color.OrangeOrb));
        ((CardView)findViewById(R.id.allDeityCard)).setCardBackgroundColor(getColor(R.color.DarkGray));
        ((CardView)findViewById(R.id.femaleDeityCard)).setCardBackgroundColor(getColor(R.color.DarkGray));
    }

    public void showFemaleDeities(View view){
        setDeityFilter(Constants.DeityFilterFemale);
        ((TextView) findViewById(R.id.allDeityText)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.maleDeityText)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.femaleDeityText)).setVisibility(View.VISIBLE);

        ((CardView)view).setCardBackgroundColor(getColor(R.color.OrangeOrb));
        ((CardView)findViewById(R.id.allDeityCard)).setCardBackgroundColor(getColor(R.color.DarkGray));
        ((CardView)findViewById(R.id.maleDeityCard)).setCardBackgroundColor(getColor(R.color.DarkGray));
    }

    public void showAllDeities(View view){
        setDeityFilter(Constants.DeityFilterSarwaDharma);
        ((TextView) findViewById(R.id.allDeityText)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.maleDeityText)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.femaleDeityText)).setVisibility(View.GONE);

        ((CardView)view).setCardBackgroundColor(getColor(R.color.OrangeOrb));
        ((CardView)findViewById(R.id.maleDeityCard)).setCardBackgroundColor(getColor(R.color.DarkGray));
        ((CardView)findViewById(R.id.femaleDeityCard)).setCardBackgroundColor(getColor(R.color.DarkGray));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("DeityList","Clicked "+filterDeityData.get(position).getDeityName()+" "+filterDeityData.get(position).getDeityID()+" "+position+" "+filterDeityData.size());
        Intent listBhajan= new Intent(this, ListBhajans.class);
//        listBhajan.putExtra(getResources().getString(R.string.selectedDeityIntent), filterDeityData.get(position).getDeityName());
//        listBhajan.putExtra(getResources().getString(R.string.selectedDeityIdIntent), String.valueOf(filterDeityData.get(position).getDeityID()));
        bhajanDataContainer.setSelectedDeity(filterDeityData.get(position));
        startActivity(listBhajan);
    }
}