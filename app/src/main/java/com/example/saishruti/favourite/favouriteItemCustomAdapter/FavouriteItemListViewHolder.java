package com.example.saishruti.favourite.favouriteItemCustomAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saishruti.R;

public class FavouriteItemListViewHolder extends RecyclerView.ViewHolder {
    public TextView ItemName;
    public TextView ItemIndex;
    public ImageView unfavourite;
//    public View view;
    public LinearLayout favbhajanListLayout;

    public FavouriteItemListViewHolder(@NonNull View itemView) {
        super(itemView);
        unfavourite= itemView.findViewById(R.id.unfavourite);
        ItemName= itemView.findViewById(R.id.bhajanList_Name);
//        view= itemView;
        ItemIndex= itemView.findViewById(R.id.bhajanList_Count);
        favbhajanListLayout= itemView.findViewById(R.id.bhajanListLayout);
    }
}
