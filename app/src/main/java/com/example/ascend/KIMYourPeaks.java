/**
 * Bumjin Kim (bkim63)
 * bkim63@jhu.edu
 */
package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class KIMYourPeaks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kim_your_peaks);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button marathonButton = (Button)findViewById(R.id.marathonButton);
        marathonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KIMYourPeaks.this, KIMRunMarathon.class);
                startActivity(intent);
            }
        });

    }

}
