//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


import java.util.ArrayList;

public class RewardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AchievementListAdapter mAdapter;
    private ArrayList<Achievement> mAcData;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerviewAchievements);


        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mAcData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new AchievementListAdapter(this, mAcData, username);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();

        LinearLayout layout_account = findViewById(R.id.layout_account);
        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardActivity.this, AccountActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_calendar = findViewById(R.id.layout_calendar);
        layout_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardActivity.this, CalendarActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_box = findViewById(R.id.layout_box);
        layout_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardActivity.this, BoxActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_clock = findViewById(R.id.layout_clock);
        layout_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardActivity.this, ClockActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }


    //navigate to PAST RECORDS activity
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.past){
            Intent i = new Intent(this,Record.class);
            startActivity(i);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.record_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }





    //Create data for achievement list
    private void initializeData() {
        // Get the resources from the XML file.
        TypedArray acImageResources =
                getResources().obtainTypedArray(R.array.ac_images);
        String[] acList = getResources()
                .getStringArray(R.array.ac_titles);
        String[] acInfo = getResources()
                .getStringArray(R.array.ac_info);

        // Clear the existing data (to avoid duplication).
        mAcData.clear();

        // Create the ArrayList of achievement
        for(int i=0;i<acList.length;i++){
            mAcData.add(new Achievement(acList[i],acInfo[i],acImageResources.getResourceId(i,0)));
        }
        acImageResources.recycle();
        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }










}
