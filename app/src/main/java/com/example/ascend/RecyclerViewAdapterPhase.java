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

public class RecyclerViewAdapterPhase extends  RecyclerView.Adapter<RecyclerViewAdapterPhase.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Phase> phases;
    private Context nContext;

    public RecyclerViewAdapterPhase(ArrayList<Phase> p, Context c) {
        phases = p;
        nContext = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phases_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        Phase p = phases.get(i);
        viewHolder.phase.setText(p.getName());

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), phasetasks.class);
                nContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView phase;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phase = itemView.findViewById(R.id.phase);
            layout = itemView.findViewById(R.id.phase_layout);
        }
    }
}
