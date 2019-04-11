package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class peak_phases extends AppCompatActivity {

    private TextView mTextMessage;

    private static final String TAG = "peak_phases";
    private ArrayList<Phase> curPhases;
    private String peakname;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_peaks:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_browse:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peak_phases);

        Intent i = getIntent();
        peakname = i.getStringExtra("peakname");

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        curPhases = new ArrayList<Phase>();
        initCurPeaks();
    }


    public void back(View v) {
        Intent i = new Intent(peak_phases.this, YourPeaks.class);
        startActivity(i);
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView recycler = findViewById(R.id.listOfPeaks);
        RecyclerViewAdapterPhase adapter = new RecyclerViewAdapterPhase(curPhases, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCurPeaks() {
        Log.d(TAG, "initCurPitches: called");
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Peak> p = realm.where(Peak.class).equalTo("name", peakname).findAll();
        Peak peaky = p.get(0);
        for (int i = 0; i < peaky.phase.size(); i++) {
            Phase cur = peaky.phase.get(i);
            if (cur != null) {
                curPhases.add(cur);
            }
        }
        if (curPhases.size() > 0 ) {
            initRecyclerView();
        }
    }

}
