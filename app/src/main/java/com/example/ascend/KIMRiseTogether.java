/**
 * Bumjin Kim (bkim63)
 * bkim63@jhu.edu
 */
package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class KIMRiseTogether extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(KIMRiseTogether.this, HomePage.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
                    Intent yourPeaksIntent = new Intent(KIMRiseTogether.this, YourPeaks.class);
                    startActivity(yourPeaksIntent);
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
        setContentView(R.layout.activity_kim_rise_together);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_browse);

        Button learnSkillButton = (Button)findViewById(R.id.learnSkillButton);
        learnSkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KIMRiseTogether.this, KIMYourPeaks.class);
                startActivity(intent);
            }
        });

        Button studyButton = (Button)findViewById(R.id.studyButton);
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KIMRiseTogether.this, KIMYourPeaks.class);
                startActivity(intent);
            }
        });

        Button exerciseButton = (Button)findViewById(R.id.exerciseButton);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KIMRiseTogether.this, KIMYourPeaks.class);
                startActivity(intent);
            }
        });
    }

}
