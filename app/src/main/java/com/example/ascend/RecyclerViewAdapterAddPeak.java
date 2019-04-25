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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class RecyclerViewAdapterAddPeak extends  RecyclerView.Adapter<RecyclerViewAdapterAddPeak.ViewHolder> {

    private ArrayList<Pitch> pitches;
    private String phase;
    private String peak;
    private Context nContext;

    public RecyclerViewAdapterAddPeak(ArrayList<Pitch> p, Context c, String ph, String goal) {
        pitches = p;
        nContext = c;
        this.phase = ph;
        this.peak = goal;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterAddPeak.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.add_pitch_to_phase, viewGroup, false);
        RecyclerViewAdapterAddPeak.ViewHolder holder = new RecyclerViewAdapterAddPeak.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        final Pitch p = pitches.get(i);
        viewHolder.name.setText(p.name);
        viewHolder.description.setText(p.plan);

    }

    @Override
    public int getItemCount() {
        return pitches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        EditText description;
        ConstraintLayout layout;
        Button change_start;
        Button change_end;
        TextView start_time;
        TextView end_time;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.time);
            layout = itemView.findViewById(R.id.add_pitch_to_phase);
            start_time = itemView.findViewById(R.id.start_time);
            end_time = itemView.findViewById(R.id.end_time);
        }
    }

}
