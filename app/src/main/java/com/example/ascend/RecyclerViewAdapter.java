package com.example.ascend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Pitch> pitches;
    private Context nContext;

    public RecyclerViewAdapter(ArrayList<Pitch> p, Context c) {
        pitches = p;
        nContext = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pitch_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        Pitch p = pitches.get(i);
        viewHolder.name.setText(p.getName());
        viewHolder.time.setText(p.getStart().toString());
    }

    @Override
    public int getItemCount() {
        return pitches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView time;
        ImageView arrow;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pitch_name);
            time = itemView.findViewById(R.id.pitch_time);
            arrow = itemView.findViewById(R.id.arrow_check);
            layout = itemView.findViewById(R.id.pitch_layout);
        }
    }
}
