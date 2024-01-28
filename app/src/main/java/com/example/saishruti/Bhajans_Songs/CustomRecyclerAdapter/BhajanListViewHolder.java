package com.example.saishruti.Bhajans_Songs.CustomRecyclerAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saishruti.R;

public class BhajanListViewHolder extends RecyclerView.ViewHolder {
    public TextView BhajanName;
    public TextView BhajanIndex;
    public ImageView unfavourite;
//    public View view;
    public LinearLayout bhajanListLayout;

    public BhajanListViewHolder(@NonNull View itemView) {
        super(itemView);
        BhajanName= itemView.findViewById(R.id.bhajanList_Name);
        unfavourite= itemView.findViewById(R.id.unfavourite);
//        view= itemView;
        BhajanIndex= itemView.findViewById(R.id.bhajanList_Count);
        bhajanListLayout= itemView.findViewById(R.id.bhajanListLayout);
    }
}
