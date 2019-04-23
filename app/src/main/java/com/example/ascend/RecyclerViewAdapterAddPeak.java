package com.example.ascend;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class RecyclerViewAdapterAddPeak extends  RecyclerView.Adapter<RecyclerViewAdapterAddPeak.ViewHolder> {

    public RecyclerViewAdapterAddPeak(ArrayList<Phase> p, Context c, String phase, String peak) {

    }

    @NonNull
    @Override
    public RecyclerViewAdapterAddPeak.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phasetasks_layout, viewGroup, false);
        //RecyclerViewAdapterPitch.ViewHolder holder = new RecyclerViewAdapterPitch.ViewHolder(view);
        //return holder;
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button name;
        //Button time;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            //time = itemView.findViewById(R.id.time);
            layout = itemView.findViewById(R.id.pitch_layout);
        }
    }

}
