package com.example.learningandroidstudio.favourite.favouriteItemCustomAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningandroidstudio.DatabaseHandler.DBModal.BhajanLiteModal;
import com.example.learningandroidstudio.DatabaseHandler.DBModal.FavouriteItemModal;
import com.example.learningandroidstudio.R;

import java.util.ArrayList;
import java.util.Map;

public class FavouriteItemListCustomRecyclerAdapter extends RecyclerView.Adapter<FavouriteItemListViewHolder> {

    private ArrayList<FavouriteItemModal> favItemData;
    private AdapterView.OnItemClickListener onClickItemOpen;
    private AdapterView.OnItemClickListener onClickItemUnfav;

    public FavouriteItemListCustomRecyclerAdapter(ArrayList<FavouriteItemModal> data, AdapterView.OnItemClickListener onClickOpen) {
        this.favItemData= data;
        this.onClickItemOpen= onClickOpen;
    }

    @Override
    public FavouriteItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View bhajanListView= inflater.inflate(R.layout.bhajan_list_layout, parent, false);
        FavouriteItemListViewHolder viewHolder= new FavouriteItemListViewHolder(bhajanListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteItemListViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        final int index= viewHolder.getAdapterPosition();
        viewHolder.ItemName.setText(this.favItemData.get(index).getItemTitle());
        viewHolder.ItemIndex.setText(String.valueOf(index+1));
        viewHolder.unfavourite.setVisibility(View.VISIBLE);
        viewHolder.favbhajanListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemOpen.onItemClick(null, v, position, index);
            }
        });
        viewHolder.unfavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemOpen.onItemClick(null, v, position, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.favItemData.size();
    }

}
