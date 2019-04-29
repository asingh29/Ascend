package com.example.ascend;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.TimePicker;

import io.realm.Realm;

public class Create_Peak_Add_Pitch extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private CheckBox sunday;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private TextView pitchname;
    private TextView pitchplan;
    private String phase_name;
    private String peak_name;
    private Button starttime;
    private Button endtime;
    private TextView stime;
    private TextView etime;
    private String start1;
    private String end1;
    private boolean fromstart;
    private Button cancel;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__peak__add__pitch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        peak_name = i.getStringExtra("peakname");
        phase_name = i.getStringExtra("phasename");
        sunday = findViewById(R.id.sunday);
        monday = findViewById(R.id.monday);
        tuesday = findViewById(R.id.tuesday);
        wednesday = findViewById(R.id.wednesday);
        thursday = findViewById(R.id.thursday);
        friday = findViewById(R.id.friday);
        saturday = findViewById(R.id.saturday);
        pitchname = findViewById(R.id.pitch_name);
        pitchplan = findViewById(R.id.pitch_description);
        stime = findViewById(R.id.start_time);
        etime = findViewById(R.id.end_time);
        starttime = findViewById(R.id._start_time_pitch);
        endtime = findViewById(R.id._end_time_pitch);
        cancel = findViewById(R.id.done);
        save = findViewById(R.id.save);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Create_Peak_Add_Pitch.this, CreatePeak_AddPhases.class);
                i.putExtra("peakname", peak_name);
                startActivity(i);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean good = true;
                boolean good2;

                String pitch_name = pitchname.getText().toString();
                String pitch_plan = pitchplan.getText().toString();

                if (pitchname.length() == 0) {
                    good = false;
                    Snackbar.make(v, "Need a name!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (pitchplan.length() == 0) {
                    good = false;
                    Snackbar.make(v, "Need a plan!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (good) {

                    int starthour = Integer.parseInt(start1.substring(0, start1.indexOf(":")));
                    int endhour = Integer.parseInt(end1.substring(0, end1.indexOf(":")));
                    int startmin = Integer.parseInt(start1.substring(start1.indexOf(":") + 1, start1.indexOf(":") + 3));
                    int endmin = Integer.parseInt(end1.substring(end1.indexOf(":") + 1, end1.indexOf(":") + 3));
                    if (start1.indexOf("P") > -1) {
                        starthour = starthour + 12;
                    } else {
                        if (starthour == 12) {
                            starthour = 0;
                        }
                    }
                    if (end1.indexOf("P") > -1) {
                        endhour = endhour + 12;
                    } else {
                        if (endhour == 12) {
                            endhour = 0;
                        }
                    }
                    if (starthour < endhour) {
                        good2 = true;
                    } else if (starthour == endhour && startmin < endmin) {
                        good2 = true;
                    } else {
                        good2 = false;
                    }


                    if (good2) {
                        Realm realm = Realm.getDefaultInstance();

                        Phase curPhase = realm.where(Phase.class).equalTo("name", phase_name).findFirst();
                        realm.beginTransaction();
                        Pitch newPitch = new Pitch(pitch_name, pitch_plan, start1, end1, sunday.isChecked(), monday.isChecked(), tuesday.isChecked(), wednesday.isChecked(), thursday.isChecked(), friday.isChecked(), saturday.isChecked());
                        Pitch cur = realm.where(Pitch.class).equalTo("name", pitch_name).findFirst();
                        if (cur == null) {
                            curPhase.addPitch(newPitch);
                        }
                        else {
                            Snackbar.make(v, "No duplicate pitches!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        realm.commitTransaction();
                        Intent i = new Intent(Create_Peak_Add_Pitch.this, CreatePeak_AddPhases.class);
                        i.putExtra("peakname", peak_name);
                        i.putExtra("phasename", phase_name);
                        i.putExtra("fromaddpitch", true);
                        startActivity(i);
                        finish();
                    } else {
                        Snackbar.make(v, "Start time cannot be after end time!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker1 = new TimePickerFragment();
                fromstart = true;
                timePicker1.show(getSupportFragmentManager(), "time picker");
            }
        });
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker1 = new TimePickerFragment();
                fromstart = false;
                timePicker1.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String format;
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
            stime.setText(start1);
            Snackbar.make(starttime, "Time changed!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (fromstart == false) {
            end1 = "" + hourOfDay + ":" + minute + " " + format;
            etime.setText(end1);
            Snackbar.make(endtime, "Time changed!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
    }
}
