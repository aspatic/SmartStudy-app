package com.example.comp4521;
//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    String username;
    TextView tv_email;
    TextView tv_password;
    LinearLayout layout_logout;
    UserDatabaseHelper userDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);

        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_password = (TextView) findViewById(R.id.tv_password);
        layout_logout = (LinearLayout) findViewById(R.id.layout_logout);
        userDatabaseHelper = new UserDatabaseHelper(this);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");
        loadData();

        layout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        LinearLayout layout_reward = (LinearLayout) findViewById(R.id.layout_rewards);
        layout_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, RewardActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_calendar = (LinearLayout) findViewById(R.id.layout_calendar);
        layout_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, CalendarActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_box = (LinearLayout) findViewById(R.id.layout_box);
        layout_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, BoxActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_clock = findViewById(R.id.layout_clock);
        layout_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, ClockActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    public void loadData() {
        String email = userDatabaseHelper.getEmail(username);
        String password = userDatabaseHelper.getPassword(username);
        tv_email.setText(email);
        tv_password.setText(password);
    }

    public void logout() {
        startActivity(new Intent(AccountActivity.this, MainActivity.class));
        finish();
    }
}
