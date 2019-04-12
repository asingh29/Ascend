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
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterPhase extends  RecyclerView.Adapter<RecyclerViewAdapterPhase.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterPhas";

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
        final Phase p = phases.get(i);
        viewHolder.phase.setText(p.getName());
        viewHolder.details.setText(p.getDescription());
        viewHolder.phase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), phasetasks.class);
                i.putExtra("phasename", p.getName());
                nContext.startActivity(i);
            }
        });
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), phasetasks.class);
                i.putExtra("phasename", p.getName());
                nContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button phase;
        Button details;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phase = itemView.findViewById(R.id.phase);
            details = itemView.findViewById(R.id.details);
            layout = itemView.findViewById(R.id.phase_layout);
        }
    }
}
