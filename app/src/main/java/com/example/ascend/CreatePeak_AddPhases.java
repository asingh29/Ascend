package com.example.ascend;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.Realm;

public class CreatePeak_AddPhases extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Button start_date;
    private Button end_date;
    private Button addPitch;
    String phase_name;
    String phase_description;
    String peak_name;
    private boolean fromStart;
    private TextView startDate;
    private TextView endDate;
    private Date phaseStartDate;
    private Date phaseEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm realm = Realm.getDefaultInstance();
        setContentView(R.layout.activity_create_peak__add_phases);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        final TextView name = findViewById(R.id.add_phase_name);
        final TextView desc = findViewById(R.id.add_phase_description);
        Button save = findViewById(R.id.save_phase);
        Button cancel = findViewById(R.id.discard_phase);
        addPitch = findViewById(R.id.add_pitch);
        startDate = findViewById(R.id.add_ph_start);
        endDate = findViewById(R.id.add_ph_end);

        peak_name = i.getStringExtra("peakname");
        if (i.hasExtra("fromaddpitch")) {
            phase_name = i.getStringExtra("phasename");
            Phase cur = realm.where(Phase.class).equalTo("name", phase_name).findFirst();
            name.setText(phase_name);
            desc.setText(cur.description);
            startDate.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(cur.start));
            endDate.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(cur.end));
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean good = true;

                phase_name = name.getText().toString();
                phase_description = desc.getText().toString();
                if (phase_name.length() == 0) {
                    good = false;
                    Snackbar.make(view, "Need a name!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (phase_description.length() == 0) {
                    good = false;
                    Snackbar.make(view, "Need a description!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (startDate.getText().length() == 0 || endDate.getText().length() == 0) {
                    good = false;
                    Snackbar.make(view, "Need dates!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (good) {
                    if (phaseStartDate.compareTo(phaseEndDate) < 0) {
                        //need to check if phase start date and phase end date exist
                        Realm realm = Realm.getDefaultInstance();
                        Peak parentPeak = realm.where(Peak.class).equalTo("name", peak_name).findFirst();
                        Phase p = new Phase(phaseStartDate, phaseEndDate, phase_name, phase_description); //fix the date here
                        realm.beginTransaction();
                        Phase cur = realm.where(Phase.class).equalTo("name", phase_name).findFirst();
                        if (cur == null) {
                            parentPeak.addPhase(p);
                        }
                        else {
                            Snackbar.make(view, "No duplicate phases!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        realm.commitTransaction();
                        Intent i = new Intent(CreatePeak_AddPhases.this, Create_Peak_Add_Pitch.class);
                        i.putExtra("peakname", peak_name);
                        i.putExtra("phasename", phase_name);
                        startActivity(i);
                        Snackbar.make(view, "Phase saved!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(view, "Start date cannot be after end date!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }

            }
        });
        addPitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean good = true;

                phase_name = name.getText().toString();
                phase_description = desc.getText().toString();
                if (phase_name.length() == 0) {
                    good = false;
                    Snackbar.make(v, "Need a name!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (phase_description.length() == 0) {
                    good = false;
                    Snackbar.make(v, "Need a description!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (startDate.getText().length() == 0 || endDate.getText().length() == 0) {
                    good = false;
                    Snackbar.make(v, "Need dates!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if (good) {
                    if (phaseStartDate.compareTo(phaseEndDate) < 0) {
                        //need to check if phase start date and phase end date exist
                        Realm realm = Realm.getDefaultInstance();
                        Peak parentPeak = realm.where(Peak.class).equalTo("name", peak_name).findFirst();
                        Phase p = new Phase(phaseStartDate, phaseEndDate, phase_name, phase_description); //fix the date here
                        realm.beginTransaction();
                        Phase cur = realm.where(Phase.class).equalTo("name", phase_name).findFirst();
                        if (cur == null) {
                            parentPeak.addPhase(p);
                        }
                        else {
                            Snackbar.make(v, "No duplicate phases!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        realm.commitTransaction();
                        Intent i = new Intent(CreatePeak_AddPhases.this, Create_Peak_Add_Pitch.class);
                        i.putExtra("peakname", peak_name);
                        i.putExtra("phasename", phase_name);
                        startActivity(i);
                        Snackbar.make(v, "Phase saved!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(v, "Start date cannot be after end date!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }






            }
        });
        start_date = findViewById(R.id.add_phase_start_date);
        end_date = findViewById(R.id.add_phase_end_date);

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                fromStart = true;
            }
        });

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                fromStart = false;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreatePeak_AddPhases.this, createPeak.class);
                i.putExtra("peakname", peak_name);
                i.putExtra("peakSet", true);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());

        //Log.d(TAG, "onDateSet: " + view.getId());

        if (fromStart) {
            //Log.d(TAG, "onDateSet: in here");
            startDate = findViewById(R.id.add_ph_start);
            startDate.setText(currentDateString);
            phaseStartDate = c.getTime();
        }
        else {
            //Log.d(TAG, "onDateSet: in there");
            endDate = findViewById(R.id.add_ph_end);
            endDate.setText(currentDateString);
            phaseEndDate = c.getTime();
        }
    }

}
