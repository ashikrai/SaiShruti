package com.example.learningandroidstudio.Bhajans_Songs.CustomRecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningandroidstudio.DatabaseHandler.DBModal.BhajanLiteModal;
import com.example.learningandroidstudio.R;

import java.util.ArrayList;
import java.util.Map;

public class bhajanListCustomRecyclerAdapter extends RecyclerView.Adapter<BhajanListViewHolder> {

    private ArrayList<BhajanLiteModal> bhajanLiteData;
    private AdapterView.OnItemClickListener onClickItem;

    public bhajanListCustomRecyclerAdapter(ArrayList<BhajanLiteModal> data, AdapterView.OnItemClickListener onClick) {
        this.bhajanLiteData= data;
        this.onClickItem= onClick;
    }

    @Override
    public BhajanListViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View bhajanListView= inflater.inflate(R.layout.bhajan_list_layout, parent, false);
        BhajanListViewHolder viewHolder= new BhajanListViewHolder(bhajanListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BhajanListViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        final int index= viewHolder.getAdapterPosition();
        viewHolder.BhajanName.setText(this.bhajanLiteData.get(index).getBhajanName());
        viewHolder.BhajanIndex.setText(String.valueOf(index+1));
        viewHolder.unfavourite.setVisibility(View.GONE);
        viewHolder.bhajanListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onItemClick(null, v, position, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.bhajanLiteData.size();
    }

}
