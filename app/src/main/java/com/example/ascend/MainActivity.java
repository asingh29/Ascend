package com.example.ascend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<Pitch> curPitches;

    SharedPreferences sharedpreferences;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_peaks:
                    Intent i = new Intent(MainActivity.this, YourPeaks.class);
                    startActivity(i);
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
        setContentView(R.layout.activity_main);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);
        sharedpreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        curPitches = new ArrayList<Pitch>();
        /*String pitch1Start = "9:00";
        String pitch1End = "10:00";
        Pitch pitch1 = new Pitch("4 mile run", 5, pitch1Start, pitch1End);
        curPitches.add(pitch1);
        initRecyclerView();*/
        initCurPitches();
    }

    public void onCLick(View view) {
        Intent i = new Intent(getApplicationContext(), YourPeaks.class);
        startActivity(i);
    }
    private void initCurPitches() {
        Log.d(TAG, "initCurPitches: called");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        curPitches.clear();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Phase> p = realm.where(Phase.class).equalTo("active", true).findAll();
        for (int i = 0; i < p.size(); i++) {
            Phase cur = p.get(i);
            RealmList<Pitch> temp = cur.getPitches(day - 1);
            for (int j = 0; j < temp.size(); j++) {
                Pitch cPitch = temp.get(j);
                curPitches.add(cPitch);
            }
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView recycler = findViewById(R.id.cur_pitches);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(curPitches, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //initCurPitches();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //initCurPitches();
    }
}
