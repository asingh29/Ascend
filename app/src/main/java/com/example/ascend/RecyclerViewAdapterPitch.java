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

public class RecyclerViewAdapterPitch  extends  RecyclerView.Adapter<RecyclerViewAdapterPitch.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapterPitc";
    String[] days = {"Su", "M", "T", "W", "Th", "F", "S"};

    private ArrayList<Pitch> pitches;
    private Context nContext;
    private String peakname;
    private String phasename;

    public RecyclerViewAdapterPitch(ArrayList<Pitch> p, Context c, String phase, String peak) {
        pitches = p;
        peakname = peak;
        phasename = phase;
        nContext = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phasetasks_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        final Pitch p = pitches.get(i);
        viewHolder.name.setText(p.getName() + "\n" + p.getStart() + " - " + p.getEnd());
        //viewHolder.time.setText(p.getStart() + " - " + p.getEnd());
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), pitchDescription.class);
                i.putExtra("phasename", phasename);
                i.putExtra("peakname", peakname);
                i.putExtra("pitchName", p.getName());
                i.putExtra("start", p.getStart());
                i.putExtra("end", p.getEnd());
                nContext.startActivity(i);
            }
        });
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), pitchDescription.class);
                i.putExtra("phasename", phasename);
                i.putExtra("peakname", peakname);
                i.putExtra("pitchName", p.name);
                i.putExtra("start", p.start);
                i.putExtra("end", p.end);
                nContext.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + pitches.size());
        return pitches.size();
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
