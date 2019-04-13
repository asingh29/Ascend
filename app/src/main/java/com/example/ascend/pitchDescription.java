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
import android.widget.EditText;
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
    String pitchName;
    String peakname;
    Pitch pitch;
    int hour;
    private boolean fromstart;
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
                    Intent i = new Intent(pitchDescription.this, MainActivity.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
                    i = new Intent(pitchDescription.this, YourPeaks.class);
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
        setContentView(R.layout.pitch_description);
        Intent i = getIntent();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_peaks);
        peakname = i.getStringExtra("peakname");
        pitchName = i.getStringExtra("pitchName");
        Button butt = (Button) findViewById(R.id.button);
        RealmResults<Pitch> realmPitch = realm.where(Pitch.class).equalTo("name", pitchName).findAll();
        pitch = realmPitch.get(0);
        EditText et = (EditText) findViewById(R.id.Notes);
        et.setText(pitch.plan);
        Button butt2 = (Button) findViewById(R.id.button2);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker1 = new TimePickerFragment();
                fromstart = true;
                timePicker1.show(getSupportFragmentManager(), "time picker");
            }
        });

        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timepicker2 = new TimePickerFragment();
                fromstart = false;
                timepicker2.show(getSupportFragmentManager(), "time picker2");
            }
        });
        Button butt3 = (Button) findViewById(R.id.button3);
        butt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

    }

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
        if (fromstart == true) {
            start1 = "" + hourOfDay + ":" + minute + " " + format;
            name.setText("Peak: " + peakname + "\n" + new StringBuilder().append(hourOfDay).append(" : ").append(minute).append(" ").append(format));
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            pitch.start = start1;
            realm.commitTransaction();
        } else if (fromstart == false) {
            end1 = "" + hourOfDay + ":" + minute + " " + format;
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            pitch.end = end1;
            realm.commitTransaction();
            name.setText("Peak: " + peakname + "\n" + new StringBuilder().append(hourOfDay).append(" : ").append(minute).append(" ").append(format));
        }
    }

    public void saveChanges() {
        EditText edit = (EditText) findViewById(R.id.Notes);
        String message = edit.getText().toString();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        pitch.plan = message;
        realm.commitTransaction();
    }




}
