package com.example.learningandroidstudio.Bhajans_Songs.CustomRecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningandroidstudio.DatabaseHandler.DBModal.DeityModal;
import com.example.learningandroidstudio.R;

import java.util.ArrayList;
import java.util.Map;

public class deityListCustomRecyclerAdapter extends RecyclerView.Adapter<DeityListViewHolder> {

    private ArrayList<DeityModal>  deityData;
    private Map<String, Integer> DeityImageData;
//    private Context context;
    private AdapterView.OnItemClickListener onClickItem;

    public deityListCustomRecyclerAdapter(ArrayList<DeityModal> data, Map<String, Integer> imageData, AdapterView.OnItemClickListener onClick) {
        this.deityData= data;
//        this.context= cntxt;
        this.onClickItem= onClick;
        this.DeityImageData= imageData;
    }

    @NonNull
    @Override
    public DeityListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View DeityListView= inflater.inflate(R.layout.card_list_layout, parent, false);
        DeityListViewHolder viewHolder= new DeityListViewHolder(DeityListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeityListViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        final int index= viewHolder.getAdapterPosition();
        if (this.DeityImageData.containsKey(this.deityData.get(index).getDeityName())) {
            viewHolder.deityImage.setImageResource(this.DeityImageData.get(this.deityData.get(index).getDeityName()));
        } else {
            viewHolder.deityImage.setImageResource(R.drawable.ic_baseline_account_circle_24);
        }
        viewHolder.deityName.setText(this.deityData.get(index).getDeityName());
        viewHolder.deityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClickItem.onItemClick(null, v, position, index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.deityData.size();
    }

}
