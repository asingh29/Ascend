package com.example.ascend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.DateFormat;
import java.util.ArrayList;

public class RecyclerViewAdapterBrowseDescription extends  RecyclerView.Adapter<RecyclerViewAdapterBrowseDescription.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapterB";

    private ArrayList<Phase> phases;
    private Context nContext;

    public RecyclerViewAdapterBrowseDescription(ArrayList<Phase> p, Context c) {
        phases = p;
        nContext = c;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterBrowseDescription.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse_description_layout, viewGroup, false);
        ViewHolder Holder = new ViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterBrowseDescription.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        final Phase p = phases.get(i);
        String startString = DateFormat.getDateInstance(DateFormat.FULL).format(p.start);
        String endString = DateFormat.getDateInstance(DateFormat.FULL).format(p.end);
        viewHolder.name.setText(p.name + "\n" + p.description + "\n" + "Start: " + startString + "\n" + "End: " + endString);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + phases.size());
        return phases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button name;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            layout = itemView.findViewById(R.id.peaks_layout);
        }
    }
}
