package com.example.ascend;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    SharedPreferences sharedpreferences;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);

        LocalDateTime start = LocalDateTime.of(2019,  4,  25,  3,  0);
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
        editor.commit();





        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
