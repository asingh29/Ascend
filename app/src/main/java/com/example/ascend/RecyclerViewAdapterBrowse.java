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

import java.text.DateFormat;
import java.util.ArrayList;

//import com.example.ascend.RecyclerViewAdapterBrowse;

public class RecyclerViewAdapterBrowse extends  RecyclerView.Adapter<RecyclerViewAdapterBrowse.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterBrow";

    private ArrayList<Peak> peaks;
    private Context nContext;

    public RecyclerViewAdapterBrowse(ArrayList<Peak> p, Context c) {
        peaks = p;
        nContext = c;
    }


    @NonNull
    @Override
    public RecyclerViewAdapterBrowse.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_browse_first_page, viewGroup, false);
        RecyclerViewAdapterBrowse.ViewHolder Holder = new RecyclerViewAdapterBrowse.ViewHolder(view);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterBrowse.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        final Peak p = peaks.get(i);
        String startString = DateFormat.getDateInstance(DateFormat.FULL).format(p.start);
        String endString = DateFormat.getDateInstance(DateFormat.FULL).format(p.end);
        viewHolder.name.setText(p.name + "\n" + "Start: " + startString + "\n" + "End: " + endString);
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(v.getContext(), browseDescription.class);
                j.putExtra("peakname", p.name);
                nContext.startActivity(j);
            }
        });
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), browseDescription.class);
                i.putExtra("peakname", p.name);
                nContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peaks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button name;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.browse_name);
            layout = itemView.findViewById(R.id.browse_layout);
        }
    }
}
