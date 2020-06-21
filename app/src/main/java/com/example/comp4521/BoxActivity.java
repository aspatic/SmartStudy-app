//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class BoxActivity extends AppCompatActivity {

    TaskDatabaseHelper taskDatabaseHelper;
    EditText et_search;
    ListView taskList;
    String username;
    ArrayList<String> array;
    ArrayList<Integer> idArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        taskDatabaseHelper = new TaskDatabaseHelper(this);
        et_search = findViewById(R.id.et_search);
        taskList = findViewById(R.id.list_search);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        final Cursor cursor = taskDatabaseHelper.getTasks(username);

        array = new ArrayList<>();
        idArray = new ArrayList<>();

        if (cursor != null && cursor.getCount() != 0) {
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
                Intent intent = new Intent(BoxActivity.this, TaskDetailActivity.class);
                intent.putExtra("id", idArray.get(position));
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            search();

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        LinearLayout layout_rewards = findViewById(R.id.layout_rewards);
        layout_rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoxActivity.this, RewardActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_calendar = findViewById(R.id.layout_calendar);
        layout_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoxActivity.this, CalendarActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_account = findViewById(R.id.layout_account);
        layout_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoxActivity.this, AccountActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        LinearLayout layout_clock = findViewById(R.id.layout_clock);
        layout_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoxActivity.this, ClockActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

    }

    private void search() {
        String task_search = et_search.getText().toString();
        array.clear();
        idArray.clear();
        final Cursor cursor = taskDatabaseHelper.getTasks(username);
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String task = cursor.getString(cursor.getColumnIndex("task"));
                if (task.toLowerCase().contains(task_search.toLowerCase())) {
                    array.add(cursor.getString(cursor.getColumnIndex("task")));
                    idArray.add(cursor.getInt(cursor.getColumnIndex("id")));
                }
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);

        taskList.setAdapter(arrayAdapter);


    }


}
