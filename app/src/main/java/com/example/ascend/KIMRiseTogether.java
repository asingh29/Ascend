/**
 * Bumjin Kim (bkim63)
 * bkim63@jhu.edu
 */
package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class KIMRiseTogether extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kim_rise_together);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
