package com.example.ascend;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class phasetasks extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private static final String TAG = "phase_tasks";
    private ArrayList<Pitch> curPitches;
    private Button changeStart;
    private Button changeEnd;
    private String phasename;
    private String peakname;
    private boolean fromStart;
    private TextView startDate;
    private TextView endDate;
    TextView name;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(phasetasks.this, HomePage.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
                    return true;
                case R.id.navigation_browse:
                    Intent intent = new Intent(phasetasks.this, BrowseFirstPage.class);
                    startActivity(intent);
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_peaks);
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        curPitches = new ArrayList<Pitch>();
        initCurPitches();

        name = findViewById(R.id.phaseName);
        name.setText(phasename);

        changeStart = findViewById(R.id.change_start_phase);

        changeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                fromStart = true;
            }
        });

        changeEnd = findViewById(R.id.change_end_phase);

        changeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                fromStart = false;
            }
        });

        startDate = findViewById(R.id.startDate_phase);
        endDate = findViewById(R.id.endDate_phase);

        Phase phase = realm.where(Phase.class).equalTo("name", phasename).findFirst();
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(phase.start);
        startDate.setText(currentDateString);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(phase.end);
        endDate.setText(currentDateString);

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
        Phase p = realm.where(Phase.class).equalTo("name", phasename).findFirst();
        RealmList<Pitch> phasey = p.all;
        Log.d(TAG, "initCurPitches: " + phasey.size());
        for (int i = 0; i < phasey.size(); i++) {
            Pitch cur = phasey.get(i);
            curPitches.add(cur);

        }
        if (curPitches.size() > 0 ) {
            initRecyclerView();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        Log.d(TAG, "onDateSet: " + view.getId());

        if (fromStart) {
            Log.d(TAG, "onDateSet: in here");
            startDate = findViewById(R.id.startDate_phase);
            startDate.setText(currentDateString);
            Date d = c.getTime();
            Phase curPhase = realm.where(Phase.class).equalTo("name", phasename).findFirst();
            realm.beginTransaction();
            curPhase.start = d;
            realm.commitTransaction();
            Snackbar.make(changeStart, "Date changed!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else {
            Log.d(TAG, "onDateSet: in there");
            endDate = findViewById(R.id.endDate_phase);
            endDate.setText(currentDateString);
            Date d = c.getTime();
            Phase curPhase = realm.where(Phase.class).equalTo("name", phasename).findFirst();
            realm.beginTransaction();
            curPhase.end = d;
            realm.commitTransaction();
            Snackbar.make(changeEnd, "Date changed!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

    }

    public void onCLick(View v) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Phase p = realm.where(Phase.class).equalTo("name", phasename).findFirst();
        RealmList<Pitch> phasey = p.all;
        Log.d(TAG, "initCurPitches: " + phasey.size());
        for (int i = 0; i < phasey.size(); i++) {
            Pitch cur = phasey.get(i);
            cur.deleteFromRealm();
        }
        RealmList<Pitch> Sunday = p.sunday;
        for (int i = 0; i < Sunday.size(); i++) {
            Pitch cur = Sunday.get(i);
            cur.deleteFromRealm();
        }
        RealmList<Pitch> Monday = p.monday;
        for (int i = 0; i < Monday.size(); i++) {
            Pitch cur = Monday.get(i);
            cur.deleteFromRealm();
        }
        RealmList<Pitch> tuesday = p.tuesday;
        for (int i = 0; i < tuesday.size(); i++) {
            Pitch cur = tuesday.get(i);
            cur.deleteFromRealm();
        }
        RealmList<Pitch> wednesday = p.wednesday;
        for (int i = 0; i < wednesday.size(); i++) {
            Pitch cur = wednesday.get(i);
            cur.deleteFromRealm();
        }
        RealmList<Pitch> thur = p.thursday;
        for (int i = 0; i < thur.size(); i++) {
            Pitch cur = thur.get(i);
            cur.deleteFromRealm();
        }
        RealmList<Pitch> friday = p.friday;
        for (int i = 0; i < friday.size(); i++) {
            Pitch cur = friday.get(i);
            cur.deleteFromRealm();
        }
        RealmList<Pitch> saturday = p.saturday;
        for (int i = 0; i < saturday.size(); i++) {
            Pitch cur = saturday.get(i);
            cur.deleteFromRealm();
        }
        p.deleteFromRealm();
        realm.commitTransaction();
        Intent i = new Intent(phasetasks.this, peak_phases.class);
        i.putExtra("peakname", peakname);
        startActivity(i);
    }

}
