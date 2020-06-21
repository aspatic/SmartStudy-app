//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import static java.lang.String.*;

public class CalendarActivity extends AppCompatActivity {

    ListView taskList;
    ImageButton addTaskButton;
    CalendarView calendarView;

    int year, month, dayOfMonth;

    String username;

    TaskDatabaseHelper taskDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ListView taskList = findViewById(R.id.taskList);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        taskDatabaseHelper = new TaskDatabaseHelper(this);
        //taskDatabaseHelper.deleteAllTasks();


        calendarView = findViewById(R.id.calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                year = y;
                month = m;
                dayOfMonth = d;

                Cursor cursor = taskDatabaseHelper.getTasks(username, String.format(java.util.Locale.US, "%04d-%02d-%02d", year, month+1, dayOfMonth));

                ArrayList<String> array = new ArrayList<>();
                final ArrayList<Integer> idArray = new ArrayList<>();

                if (cursor == null || cursor.getCount() == 0) {
                    array.add("No Tasks Today!");
                } else {
                    while (cursor.moveToNext()) {
                        array.add(cursor.getString(cursor.getColumnIndex("task")));
                        idArray.add(cursor.getInt(cursor.getColumnIndex("id")));
                    }
                }


                ArrayAdapter arrayAdapter = new ArrayAdapter(CalendarActivity.this, android.R.layout.simple_list_item_1, array);

                ListView taskListIn = findViewById(R.id.taskList);
                taskListIn.setAdapter(arrayAdapter);
                taskListIn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(CalendarActivity.this, TaskDetailActivity.class);
                        intent.putExtra("id", idArray.get(position));
                        startActivity(intent);
                    }
                });
            }
        });


        Cursor cursor = taskDatabaseHelper.getTasks(username, String.format(java.util.Locale.US, "%04d-%02d-%02d", year, month+1, dayOfMonth));

        ArrayList<String> array = new ArrayList<>();
        final ArrayList<Integer> idArray = new ArrayList<>();

        if (cursor == null || cursor.getCount() == 0) {
            array.add("No Tasks Today!");
        } else {
            while (cursor.moveToNext()) {
                array.add(cursor.getString(cursor.getColumnIndex("task")));
                idArray.add(cursor.getInt(cursor.getColumnIndex("id")));
            }
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);

        taskList.setAdapter(arrayAdapter);
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CalendarActivity.this, TaskDetailActivity.class);
                intent.putExtra("id", idArray.get(position));
                startActivity(intent);
            }
        });

        LinearLayout layout_rewards = findViewById(R.id.layout_rewards);
        layout_rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, RewardActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_box = findViewById(R.id.layout_box);
        layout_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, BoxActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_clock = findViewById(R.id.layout_clock);
        layout_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, ClockActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_account = findViewById(R.id.layout_account);
        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AccountActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        addTaskButton = findViewById(R.id.addTask);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, AddTaskActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);

            }
        });


    }
}
