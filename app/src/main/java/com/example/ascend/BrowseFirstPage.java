package com.example.ascend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
//import com.example.ascend.RecyclerViewAdapterBrowse;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class BrowseFirstPage extends AppCompatActivity {

    private TextView mTextMessage;
    private ArrayList<Peak> curPeaks;
    private static final String TAG = "BrowsePeaks";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Intent i = new Intent(BrowseFirstPage.this, HomePage.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_peaks:
                    //mTextMessage.setText(R.string.title_dashboard);
                    i = new Intent(BrowseFirstPage.this, YourPeaks.class);
                    startActivity(i);
                    return true;
                case R.id.navigation_browse:
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_first_page);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_browse);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        curPeaks = new ArrayList<Peak>();
        initCurPeaks();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCurPeaks();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initCurPeaks();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: called");
        RecyclerView recycler = findViewById(R.id.BrowsePeaks);
        RecyclerViewAdapterBrowse adapter = new RecyclerViewAdapterBrowse(curPeaks, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initCurPeaks() {
        Log.d(TAG, "initCurPitches: called");
        curPeaks.clear();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Peak> p = realm.where(Peak.class).findAll();
        for (int i = 0; i < p.size(); i++) {
            Peak cur = p.get(i);
            if(cur.browse == true) {
                curPeaks.add(cur);
            }
        }
        initRecyclerView();
    }

}
