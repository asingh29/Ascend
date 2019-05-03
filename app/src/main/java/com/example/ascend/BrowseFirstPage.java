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
//import com.example.ascend.RecyclerViewAdapterBrowse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class BrowseFirstPage extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<Peak> curPeaks;
    private static final String TAG = "BrowsePeaks";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent i = new Intent(BrowseFirstPage.this, HomePage.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
                    //mTextMessage.setText(R.string.title_dashboard);
                    i = new Intent(BrowseFirstPage.this, YourPeaks.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_browse:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_first_page);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_browse);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        Calendar cal = new GregorianCalendar();
        cal.set(2019,3,30);
        Date peakStartDate = cal.getTime();
        cal.set(2019, 5, 30);
        Date peakEndDate = cal.getTime();
        Peak peak1 = new Peak("Run a Marathon", "Train for two months to run the Boston Marathon on June 10th", peakStartDate, peakEndDate, true);
        cal.set(2019, 3, 30);
        Date phase1Start = cal.getTime();
        cal.set(2019, 4, 10);
        Date phase1End = cal.getTime();
        Phase phase1 = new Phase(phase1Start, phase1End, "The Beginning", "Build good training habits and fundamental strength/endurance");
        String pitch1Start = "9:00 AM";
        String pitch1End = "10:00 AM";
        String pitch2Start = "11:00 AM";
        String pitch2End = "12:00 PM";
        Pitch pitch1 = new Pitch("4 mile run", "Warmup, stretch, run 4 mile route in Druid Hill Park, cooldown, stretch", pitch1Start, pitch1End, false, true, true, true, false, true, false);
        Pitch pitch2 = new Pitch("Eat a Healthy Lunch", "2 fruits, 2 veggies, 1 serving of protein and 1 serving of carbs",pitch2Start, pitch2End, true, true, true, true, true, true, true);
        Pitch pitch3 = new Pitch("Workout", "3x10 Squat\n3x10 Deadlift\n3x10 Calf raises", pitch1Start, pitch1End, true, false, true, false, true, false, true);
        phase1.addPitch(pitch1);
        phase1.addPitch(pitch2);
        phase1.addPitch(pitch3);
        phase1.active = false;
        peak1.addPhase(phase1);

        cal.set(2019, 4, 11);
        Date phase2Start = cal.getTime();
        cal.set(2019, 5, 30);
        Date phase2End = cal.getTime();
        Phase phase2 = new Phase(phase2Start, phase2End, "The End", "Solidify muscle foundation and up weekly mileage");
        Pitch p1 = new Pitch("6 mile run", "Warmup, stretch, run 6 mile route in through Patterson Park, cooldown, stretch", pitch1Start, pitch1End, false, true, false, true, false, true, false);
        Pitch p2 = new Pitch("Eat Healthy", "2 fruits, 2 veggies, 1 serving of protein and 1 serving of carbs",pitch2Start, pitch2End, true, true, true, true, true, true, true);
        Pitch p3 = new Pitch("10 mile run", "Warmup, stretch, run to Leakin Park and back", pitch1Start, pitch1End, true, false, false, false, true, false, true);
        phase2.addPitch(p1);
        phase2.addPitch(p2);
        phase2.addPitch(p3);
        phase2.active = false;
        peak1.addPhase(phase2);

        Peak peak2 = new Peak("Study for MCAT", "Spend two months studying for the MCAT", peakStartDate, peakEndDate, true);
        cal.set(2019, 3, 30);
        phase1Start = cal.getTime();
        cal.set(2019, 4, 10);
        phase1End = cal.getTime();
        phase1 = new Phase(phase1Start, phase1End, "Study Books", "Build good study habits and complete necessary readings");
        pitch1Start = "9:00 AM";
        pitch1End = "10:30 AM";
        pitch2Start = "11:00 AM";
        pitch2End = "12:30 PM";
        pitch1 = new Pitch("Study Physics", "study Physics book for 1.5 hours", pitch1Start, pitch1End, false, true, true, true, false, true, false);
        pitch2 = new Pitch("Study Bio", "study Bio book for 1.5 hours",pitch1Start, pitch1End, true, true, true, true, true, true, true);
        pitch3 = new Pitch("Study Reading Comprehension", "study Reading Comprehension", pitch2Start, pitch2End, true, false, true, false, true, false, true);
        phase1.addPitch(pitch1);
        phase1.addPitch(pitch2);
        phase1.addPitch(pitch3);
        phase1.active = false;
        peak2.addPhase(phase1);

        cal.set(2019, 4, 11);
        phase2Start = cal.getTime();
        cal.set(2019, 5, 30);
        phase2End = cal.getTime();
        phase2 = new Phase(phase2Start, phase2End, "Practice", "Take a ton of practice exams");
        p1 = new Pitch("Practice Bio section", "take Bio practice exam", pitch1Start, pitch1End, false, true, true, true, false, true, false);
        p2 = new Pitch("Practice Reading Comprehension", "take reading comprehension practice exam",pitch2Start, pitch2End, true, true, true, true, true, true, true);
        p3 = new Pitch("Practice Physics", "take physics practice exam", pitch1Start, pitch1End, true, false, false, false, true, false, true);
        phase2.addPitch(p1);
        phase2.addPitch(p2);
        phase2.addPitch(p3);
        phase2.active = false;
        peak2.addPhase(phase2);


        realm.beginTransaction();
        Peak marathon = realm.where(Peak.class).equalTo("name", "Run a Marathon").findFirst();
        Peak mcat = realm.where(Peak.class).equalTo("name", "Study for MCAT").findFirst();
        if (marathon == null) realm.copyToRealm(peak1);
        if (mcat == null) realm.copyToRealm(peak2);
        realm.commitTransaction();

        curPeaks = new ArrayList<Peak>();
        initCurPeaks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCurPeaks();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initCurPeaks();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView recycler = findViewById(R.id.BrowsePeaks);
        RecyclerViewAdapterBrowse adapter = new RecyclerViewAdapterBrowse(curPeaks, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCurPeaks() {
        Log.d(TAG, "initCurPitches: called");
        curPeaks.clear();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Peak> p = realm.where(Peak.class).findAll();
        for (int i = 0; i < p.size(); i++) {
            Peak cur = p.get(i);
            if(cur.browse == true) {
                curPeaks.add(cur);
            }
        }
        initRecyclerView();
    }

}
