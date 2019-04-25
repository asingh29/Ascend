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

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class browseDescription extends AppCompatActivity {

    private static final String TAG = "browseDescription";
    private ArrayList<Phase> curPhases;
    private String peakname;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(browseDescription.this, HomePage.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
                    Intent intent = new Intent(browseDescription.this, YourPeaks.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_browse:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        peakname = i.getStringExtra("peakname");
        setContentView(R.layout.browse_description);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_browse);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        curPhases = new ArrayList<Phase>();
        initCurPitches();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initCurPitches();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCurPitches();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView recycler = findViewById(R.id.listOfPhases);
        RecyclerViewAdapterBrowseDescription adapter = new RecyclerViewAdapterBrowseDescription(curPhases, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCurPitches() {
        Log.d(TAG, "initCurPitches: called");
        curPhases.clear();
        Realm realm = Realm.getDefaultInstance();
        Peak peaky = realm.where(Peak.class).equalTo("name", peakname).findFirst();
        //RealmResults<Pitch> p = realm.where(Peak.class).findAll();
        for (int i = 0; i < peaky.phase.size(); i++) {
            Phase cur = peaky.phase.get(i);
            curPhases.add(cur);
        }
        initRecyclerView();
    }

    public void onClick(View v) {

    }

}
