package com.example.ascend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class pitchDescription extends AppCompatActivity {

    TextView time;
    TextView name;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pitch_description);
        Intent i = getIntent();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        String peakName = i.getStringExtra("peakname");
        RealmResults<Pitch> realmPitch = realm.where(Pitch.class).equalTo("name", peakName).findAll();
        time = findViewById(R.id.Times);
        Pitch p = realmPitch.get(0);
        time.setText(days[p.day] + "\n" + p.start + "-" + p.end);
        name = findViewById(R.id.Name);
        name.setText("Peak: " + peakName);


    }


}
