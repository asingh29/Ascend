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
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterPeak extends  RecyclerView.Adapter<RecyclerViewAdapterPeak.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Peak> peaks;
    private Context nContext;

    public RecyclerViewAdapterPeak(ArrayList<Peak> p, Context c) {
        peaks = p;
        nContext = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.peaks_layout, viewGroup, false);
        ViewHolder Holder = new ViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        final Peak p = peaks.get(i);
        viewHolder.name.setText(p.getName());
        viewHolder.time.setText(p.getStart().toString() + "" + p.getEnd().toString());

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), peak_phases.class);
                i.putExtra("peakname", p.getName());
                nContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peaks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView time;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            layout = itemView.findViewById(R.id.peaks_layout);
        }
    }
}
