package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.Realm;

public class YourPeaks extends AppCompatActivity {

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


    }

    public void addPeak(View view) {
        Realm realm = Realm.getDefaultInstance();
        String peakStartDate = "04/01/2019";
        String peakEndDate = "07/01/2019";
        Peak peak1 = new Peak("Run a Marathon", "runfast, run hard", peakStartDate, peakEndDate);
        String phase1Start = "04/01/2019";
        String phase1End = "04/30/2019";
        Phase phase1 = new Phase(phase1Start, phase1End);
        String pitch1Start = "9:00";
        String pitch1End = "10:00";
        Pitch pitch1 = new Pitch("4 mile run", 1, pitch1Start, pitch1End);
        phase1.addPitch(pitch1);
        peak1.addPhase(phase1);

        realm.beginTransaction();
        Peak marathon = realm.copyToRealm(peak1);
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
