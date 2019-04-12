package com.example.ascend;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class pitchDescription extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TimePicker timePicker1;
    private TimePicker timePicker2;
    TextView time;
    TextView name;
    String format;
    String start1;
    String end1;
    int hour;
    int min;
    String[] days = {"Su", "M", "T", "W", "Th", "F", "S"};

    private static final String TAG = "pitchDescription";
    SharedPreferences sharedpreferences;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_peaks:
                    Intent i = new Intent(pitchDescription.this, YourPeaks.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_browse:
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        name = findViewById(R.id.Name);
        if (hourOfDay == 0) {
            hourOfDay += 12;
            format = "AM";
        } else if (hourOfDay == 12) {
            format = "PM";
        } else if (hourOfDay > 12) {
            hourOfDay -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        if (view.getId() == R.id.button) {
            start1 = "" + hourOfDay + ":" + minute + " " + format;
            name.setText("Peak: " + "\n" + new StringBuilder().append(hourOfDay).append(" : ").append(minute).append(" ").append(format));
        } else if (view.getId() == R.id.button2) {
            end1 = "" + hourOfDay + ":" + minute + " " + format;
            name.setText("Peak: " + "\n" + new StringBuilder().append(hourOfDay).append(" : ").append(minute).append(" ").append(format));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pitch_description);
        Intent i = getIntent();
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        timePicker2 = findViewById(R.id.timePicker2);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        final String peakName = i.getStringExtra("peakname");
        final String start = i.getStringExtra("start");
        String end = i.getStringExtra("end");
        String day = i.getStringExtra("day");
        name = findViewById(R.id.Name);
        //hour = timePicker1.getCurrentHour();
        //min = timePicker1.getCurrentMinute();
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        name.setText("Peak: " + peakName + "\n" + new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));

        Button butt = (Button) findViewById(R.id.button);
        Button butt2 = (Button) findViewById(R.id.button2);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker1 = new TimePickerFragment();
                timePicker1.show(getSupportFragmentManager(), "time picker");
            }
        });

        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker2 = new TimePickerFragment();
                timepicker2.show(getSupportFragmentManager(), "time picker2");
            }
        });

    }




}
