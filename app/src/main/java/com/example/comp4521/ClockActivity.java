//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class ClockActivity extends AppCompatActivity {

    private TabHost tabHost;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("plan").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tomato").setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("timer").setContent(R.id.tab3));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("achieve").setContent(R.id.tab4));

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        LinearLayout layout_reward = (LinearLayout) findViewById(R.id.layout_rewards);
        layout_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClockActivity.this, RewardActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_calendar = (LinearLayout) findViewById(R.id.layout_calendar);
        layout_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClockActivity.this, CalendarActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_box = (LinearLayout) findViewById(R.id.layout_box);
        layout_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClockActivity.this, BoxActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_account = findViewById(R.id.layout_account);
        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClockActivity.this, AccountActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}
