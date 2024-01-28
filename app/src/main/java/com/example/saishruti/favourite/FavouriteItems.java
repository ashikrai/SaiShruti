package com.example.saishruti.favourite;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saishruti.AppBarNavigation;
import com.example.saishruti.DatabaseHandler.DBHandler;
import com.example.saishruti.DatabaseHandler.DBModal.FavouriteItemModal;
import com.example.saishruti.R;
import com.example.saishruti.Utils.MySharedPreferences;
import com.example.saishruti.Utils.constants.Constants;
import com.example.saishruti.favourite.favouriteItemCustomAdapter.FavouriteItemListCustomRecyclerAdapter;
import java.util.ArrayList;
import pl.droidsonroids.gif.GifImageView;


public class FavouriteItems extends AppBarNavigation implements AdapterView.OnItemClickListener{
    private RecyclerView BhajanRecyclerView;
    private RecyclerView SongRecyclerView;
    private RecyclerView VedamRecyclerView;
    private FavouriteItemListCustomRecyclerAdapter CRAdt;
    private ArrayList<FavouriteItemModal> favItems;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_items);
        ImageView fav= findViewById(R.id.AppBarFavourite);
        fav.setEnabled(false);
        TextView slogan= findViewById(R.id.AppBar_Slogan);
        slogan.setText("Favourite Items 💗");

        GifImageView favGif= findViewById(R.id.Fav_gif_animation);
        favGif.setImageResource(R.drawable.favourite_animation);
        Log.d("AppBarNavigation", "showing fav animation");
        favGif.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("AppBarNavigation", "hiding fav animation");
                favGif.setImageResource(R.drawable.no_background);
            }
        }, 2000);

        ((TextView)findViewById(R.id.FavItemLabel)).setText("Favourite Bhajan List");
        populateFavBhajanRecyclerView(true);
    }

    private ArrayList<FavouriteItemModal> FilterFavouriteItemList(String ItemType) {
        ArrayList<FavouriteItemModal> temp= new ArrayList<>();
        for (FavouriteItemModal item : favItems) {
            if(item.getItemType().equals(ItemType))
                temp.add(item);
        }
        return temp;
    }

    // ------------------------------------------ Favourite Bhajans section ------------------------------------------

    private void populateFavBhajanRecyclerView(boolean fetchfromDB) {
        if (fetchfromDB)
            PopulateFavouriteItems();
        ArrayList<FavouriteItemModal> BhajanFavItem= FilterFavouriteItemList(Constants.FavItemBhajan);
        CRAdt= new FavouriteItemListCustomRecyclerAdapter(BhajanFavItem, FavouriteItems.this);

        BhajanRecyclerView= findViewById(R.id.favBhajanRecyclerView);
        BhajanRecyclerView.setLayoutManager(new LinearLayoutManager(FavouriteItems.this));
        BhajanRecyclerView.setAdapter(CRAdt);
        BhajanRecyclerView.setHasFixedSize(true);
        BhajanRecyclerView.setVisibility(View.VISIBLE);
    }

    public void OpenCloseBhajanList(View v) {
        Log.d("FavouriteItems", "OpenClose Favourite Bhajan Layout");
        if (BhajanRecyclerView == null) {
            populateFavBhajanRecyclerView(false);
        }
        if (MySharedPreferences.getFavouriteChanged())
            populateFavBhajanRecyclerView(true);

//        if (BhajanRecyclerView.getVisibility()==View.GONE)
//        {
//            BhajanRecyclerView.setVisibility(View.VISIBLE);
//            ((ImageView) findViewById(R.id.fabBhajanCollapseIcon)).setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
//        } else {
//            ((ImageView) findViewById(R.id.fabBhajanCollapseIcon)).setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
//            BhajanRecyclerView.setVisibility(View.GONE);
//        }
        findViewById(R.id.favBhajanBtn).setEnabled(false);
        findViewById(R.id.favSongBtn).setEnabled(true);
        findViewById(R.id.favVedamBtn).setEnabled(true);


        ((TextView)findViewById(R.id.FavItemLabel)).setText("Favourite Bhajan List");
        BhajanRecyclerView.setVisibility(View.VISIBLE);
        if (SongRecyclerView != null)
            SongRecyclerView.setVisibility(View.GONE);
        if (VedamRecyclerView != null)
            VedamRecyclerView.setVisibility(View.GONE);
    }

    // ------------------------------------------ Favourite Songs section ------------------------------------------
    private void populateFavSongRecyclerView(boolean fetchfromDB) {
        if (fetchfromDB)
            PopulateFavouriteItems();
        ArrayList<FavouriteItemModal> SongFavItem= FilterFavouriteItemList(Constants.FavItemSong);
        CRAdt= new FavouriteItemListCustomRecyclerAdapter(SongFavItem, FavouriteItems.this);

        SongRecyclerView= findViewById(R.id.favSongRecyclerView);
        SongRecyclerView.setLayoutManager(new LinearLayoutManager(FavouriteItems.this));
        SongRecyclerView.setAdapter(CRAdt);
        SongRecyclerView.setHasFixedSize(true);
        SongRecyclerView.setVisibility(View.VISIBLE);
    }

    public void OpenCloseSongList(View v) {
        Log.d("FavouriteItems", "OpenClose Favourite Song Layout");
        if (SongRecyclerView == null) {
            populateFavSongRecyclerView(false);
        }
        if (MySharedPreferences.getFavouriteChanged())
            populateFavSongRecyclerView(true);

//        if (SongRecyclerView.getVisibility()==View.GONE)
//        {
//            SongRecyclerView.setVisibility(View.VISIBLE);
//            ((ImageView) findViewById(R.id.fabSongCollapseIcon)).setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
//        } else {
//            ((ImageView) findViewById(R.id.fabSongCollapseIcon)).setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
//            SongRecyclerView.setVisibility(View.GONE);
//        }

        findViewById(R.id.favBhajanBtn).setEnabled(true);
        findViewById(R.id.favSongBtn).setEnabled(false);
        findViewById(R.id.favVedamBtn).setEnabled(true);

        SongRecyclerView.setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.FavItemLabel)).setText("Favourite Song List");
        if (BhajanRecyclerView != null)
            BhajanRecyclerView.setVisibility(View.GONE);
        if (VedamRecyclerView != null)
            VedamRecyclerView.setVisibility(View.GONE);
    }


    // ------------------------------------------ Favourite Vedam section ------------------------------------------
    private void populateFavVedamRecyclerView(boolean fetchfromDB) {
        if (fetchfromDB)
            PopulateFavouriteItems();
        ArrayList<FavouriteItemModal> VedamFavItem= FilterFavouriteItemList(Constants.FavItemVedam);
        CRAdt= new FavouriteItemListCustomRecyclerAdapter(VedamFavItem, FavouriteItems.this);

        VedamRecyclerView= findViewById(R.id.favVedamRecyclerView);
        VedamRecyclerView.setLayoutManager(new LinearLayoutManager(FavouriteItems.this));
        VedamRecyclerView.setAdapter(CRAdt);
        VedamRecyclerView.setHasFixedSize(true);
        VedamRecyclerView.setVisibility(View.VISIBLE);
    }

    public void OpenCloseVedamList(View v) {
        Log.d("FavouriteItems", "OpenClose Favourite Song Layout");
        if (VedamRecyclerView == null) {
            populateFavVedamRecyclerView(false);
        }

        if (MySharedPreferences.getFavouriteChanged())
            populateFavVedamRecyclerView(true);

//        if (VedamRecyclerView.getVisibility()==View.GONE)
//        {
//            VedamRecyclerView.setVisibility(View.VISIBLE);
//            ((ImageView) findViewById(R.id.fabVedamCollapseIcon)).setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
//        } else {
//            ((ImageView) findViewById(R.id.fabVedamCollapseIcon)).setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
//            VedamRecyclerView.setVisibility(View.GONE);
//        }

        findViewById(R.id.favBhajanBtn).setEnabled(true);
        findViewById(R.id.favSongBtn).setEnabled(true);
        findViewById(R.id.favVedamBtn).setEnabled(false);

        VedamRecyclerView.setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.FavItemLabel)).setText("Favourite Vedam List");
        if (BhajanRecyclerView != null)
            BhajanRecyclerView.setVisibility(View.GONE);
        if (SongRecyclerView != null)
            SongRecyclerView.setVisibility(View.GONE);

    }


    // ------------------------------------------ Fetching Favourite Items  ------------------------------------------
    private void  PopulateFavouriteItems() {
        dbHandler= new DBHandler(this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                favItems= dbHandler.getUserFavouriteItem(MySharedPreferences.getUserIdPref());
            }
        });
        if (favItems.size() == 0) {
            Log.d("FavouriteItem","There are no items in the marked Favourite");
            findViewById(R.id.showfavouriteItemLayout).setVisibility(View.GONE);
            findViewById(R.id.noFavoruiteLayout).setVisibility(View.VISIBLE);
            return;
        }
        MySharedPreferences.setFavouriteChanged(false);
        findViewById(R.id.showfavouriteItemLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.noFavoruiteLayout).setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }
}