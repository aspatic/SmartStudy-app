//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    int id;
    String username;
    Button btn_back, btn_do_task;
    TextView task, description, time, duration, status;
    TaskDatabaseHelper taskDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        id = getIntent().getIntExtra("id", 1);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_do_task = findViewById(R.id.btn_do);
        task = findViewById(R.id.tv_task);
        description = findViewById(R.id.tv_description);
        time = findViewById(R.id.tv_time);
        duration = findViewById(R.id.tv_duration);
        status = findViewById(R.id.tv_status);
        taskDatabaseHelper = new TaskDatabaseHelper(this);

        Cursor cursor = taskDatabaseHelper.getTask(id);
        if (cursor != null) {
            cursor.moveToFirst();
            task.setText(cursor.getString(cursor.getColumnIndex("task")));
            description.setText(cursor.getString(cursor.getColumnIndex("description")));
            time.setText(cursor.getString(cursor.getColumnIndex("time")));
            duration.setText(Integer.toString(cursor.getInt(cursor.getColumnIndex("duration"))));
            if (cursor.getInt(cursor.getColumnIndex("status")) == 1) {
                status.setText("Not finished");
            } else {
                status.setText("Finished");
            }

        }

        btn_do_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDatabaseHelper.finishTask(id);
                Intent intent = new Intent(TaskDetailActivity.this, ClockActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

    }
}
