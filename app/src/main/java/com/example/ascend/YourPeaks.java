package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class YourPeaks extends AppCompatActivity {

    private static final String TAG = "YourPeaks";
    private ArrayList<Peak> curPeaks;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(YourPeaks.this, MainActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
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
        setContentView(R.layout.activity_your_peaks);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_peaks);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        curPeaks = new ArrayList<Peak>();
        initCurPeaks();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initCurPeaks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCurPeaks();
    }


    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView recycler = findViewById(R.id.listOfPeaks);
        RecyclerViewAdapter3 adapter = new RecyclerViewAdapter3(curPeaks, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCurPeaks() {
        Log.d(TAG, "initCurPitches: called");
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Peak> p = realm.where(Peak.class).findAll();
        for (int i = 0; i < p.size(); i++) {
            Peak cur = p.get(i);
            if (cur != null) {
                curPeaks.add(cur);
            }
        }
        if (curPeaks.size() > 0 ) {
            initRecyclerView();
        }
    }

    public void addPeak(View view) {
        Realm realm = Realm.getDefaultInstance();
        Calendar cal = new GregorianCalendar();
        cal.set(2019,4,10);
        Date peakStartDate = cal.getTime();
        cal.set(2019, 5, 10);
        Date peakEndDate = cal.getTime();
        Peak peak1 = new Peak("Run a Marathon", "run fast, run hard", peakStartDate, peakEndDate);
        cal.set(2019, 4, 10);
        Date phase1Start = cal.getTime();
        cal.set(2019, 4, 30);
        Date phase1End = cal.getTime();
        Phase phase1 = new Phase(phase1Start, phase1End);
        String pitch1Start = "9:00";
        String pitch1End = "10:00";
        String pitch2Start = "11:00";
        String pitch2End = "12:00";
        Pitch pitch1 = new Pitch("4 mile run", 4, pitch1Start, pitch1End);
        Pitch pitch2 = new Pitch("Eat a Healthy Lunch", 4, pitch2Start, pitch2End);
        pitch1.plan = "Warmup";
        phase1.addPitch(pitch1);
        phase1.addPitch(pitch2);
        phase1.active = true;
        peak1.addPhase(phase1);

        realm.beginTransaction();
        Peak marathon = realm.copyToRealm(peak1);
        //Phase marathon_phase_one = realm.copyToRealm(phase1);
        realm.commitTransaction();

        /*LocalDateTime start = LocalDateTime.of(2019,  4,  25,  3,  0);
        LocalDateTime end = LocalDateTime.of(2019,  7,  25,  3,  0);
        LocalTime s = LocalTime.of(9,0);
        LocalTime e = LocalTime.of(10,0);
        Peak peak1 = new Peak("Run a Marathon", "Run so fast", start, end);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Phase phase1 = new Phase(start, end);
        Pitch pitch1 = new Pitch("4 mile run", 1, s, e);
        phase1.addPitch(pitch1);
        peak1.addPhase(phase1);
        Gson gson = new Gson();
        String p1 = gson.toJson(peak1);
        editor.putString("p1", p1);
        editor.commit();*/
    }

}
