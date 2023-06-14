package com.example.learningandroidstudio.Bhajans_Songs.CustomRecyclerAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningandroidstudio.R;

public class DeityListViewHolder extends RecyclerView.ViewHolder {
    public TextView deityName;
    public ImageView deityImage;
    public View view;
    public LinearLayout deityLayout;

    public DeityListViewHolder(@NonNull View itemView) {
        super(itemView);
        deityName= itemView.findViewById(R.id.deityListText);
        deityImage= itemView.findViewById(R.id.deityImage);
        view= itemView;
        deityLayout= itemView.findViewById(R.id.deityLayout);
    }
}
