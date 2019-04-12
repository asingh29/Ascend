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
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class phasetasks extends AppCompatActivity {

    private TextView mTextMessage;
    private static final String TAG = "phase_tasks";
    private ArrayList<Pitch> curPitches;
    private String phasename;
    private String peakname;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent i = new Intent(phasetasks.this, MainActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
                    mTextMessage.setText(R.string.title_dashboard);
                    i = new Intent(phasetasks.this, YourPeaks.class);
                    startActivity(i);
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
        setContentView(R.layout.activity_phasetasks);
        Intent i = getIntent();
        phasename = i.getStringExtra("phasename");
        peakname = i.getStringExtra("peakname");


        mTextMessage = (TextView) findViewById(R.id.message);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        curPitches = new ArrayList<Pitch>();
        initCurPitches();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView recycler = findViewById(R.id.cur_phasetasks);
        RecyclerViewAdapterPitch adapter = new RecyclerViewAdapterPitch(curPitches, this, phasename, peakname);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCurPitches() {
        Log.d(TAG, "initCurPitches: called");
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Phase> p = realm.where(Phase.class).equalTo("name", phasename).findAll();
        RealmList<Pitch> phasey = p.get(0).all;
        Log.d(TAG, "initCurPitches: " + phasey.size());
        for (int i = 0; i < phasey.size(); i++) {
            Pitch cur = phasey.get(i);
            curPitches.add(cur);

        }
        if (curPitches.size() > 0 ) {
            initRecyclerView();
        }
    }

}
