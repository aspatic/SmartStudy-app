//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;

import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity implements
        View.OnClickListener {

    String username;
    Button btnDatePicker, btnTimePicker, addTaskButton;
    ImageButton closeButton;
    EditText task, description, txtDate, txtTime, duration;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TaskDatabaseHelper taskDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnDatePicker = findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        task = findViewById(R.id.task);
        description = findViewById(R.id.description);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        addTaskButton = findViewById(R.id.addTask);
        closeButton = findViewById(R.id.close);
        duration = findViewById(R.id.duration);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        taskDatabaseHelper = new TaskDatabaseHelper(this);


        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(String.format("%04d-%02d-%02d", year, monthOfYear+1, dayOfMonth));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void addTask() {
        if (task.getText().toString().isEmpty()) {
            Toast.makeText(AddTaskActivity.this, "Please enter task!", Toast.LENGTH_SHORT).show();
            return;
        } else if (txtDate.getText().toString().isEmpty()) {
            Toast.makeText(AddTaskActivity.this, "Please enter date!", Toast.LENGTH_SHORT).show();
            return;
        } else if (txtTime.getText().toString().isEmpty()) {
            Toast.makeText(AddTaskActivity.this, "Please enter time!", Toast.LENGTH_SHORT).show();
            return;
        } else if (duration.getText().toString().isEmpty()) {
            Toast.makeText(AddTaskActivity.this, "Please enter duration!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            taskDatabaseHelper.addTask(username, task.getText().toString(), description.getText().toString(), txtDate.getText().toString() + " " + txtTime.getText().toString() + ":00.000", duration.getText().toString());
            Toast.makeText(AddTaskActivity.this, "Task added!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}