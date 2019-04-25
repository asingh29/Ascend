package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class PitchComplete extends AppCompatActivity {

    TextView time;
    TextView plan;
    String[] days = {"Su", "M", "T", "W", "Th", "F", "S"};
    Pitch pitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitch_complete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        String pitchName = i.getStringExtra("PitchName");
        RealmResults<Pitch> realmPitch = realm.where(Pitch.class).equalTo("name", pitchName).findAll();
        pitch = realmPitch.get(0);
        time = findViewById(R.id.time);
        plan = findViewById(R.id.plan_string);
        StringBuilder buildDayString = new StringBuilder("");
        if (pitch.sunday) buildDayString.append(days[0]);
        if (pitch.monday) buildDayString.append(days[1]);
        if (pitch.tuesday) buildDayString.append(days[2]);
        if (pitch.wednesday) buildDayString.append(days[3]);
        if (pitch.thursday) buildDayString.append(days[4]);
        if (pitch.friday) buildDayString.append(days[5]);
        if (pitch.saturday) buildDayString.append(days[6]);
        String dayString = buildDayString.toString();
        time.setText(dayString + "\n" + pitch.start + "-" + pitch.end);
        plan.setText(pitch.plan);
    }
    public void pitchComplete(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        pitch.complete = true;
        realm.commitTransaction();
        Intent i;
        i = new Intent(this, HomePage.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
