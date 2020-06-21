//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TaskDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private static final String pkey = "id";
    private static final String fkey = "username";
    private static final String col1 = "task";
    private static final String col2 = "description";
    private static final String col3 = "time";
    private static final String col4 = "duration"; // minutes
    private static final String col5 = "status"; // true -> not finished, false -> finished
    SQLiteDatabase db;



    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s varchar(64), %s varchar(64), %s varchar(256), %s DATETIME, %s INT, %s INT)", TABLE_NAME, pkey, fkey, col1, col2, col3, col4, col5);
        Log.i("ANSONABC", query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

//    public void addTask(String username, String task, String description) {
//        db = this.getWritableDatabase();
//        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', '%s', '%s', %s)", TABLE_NAME, fkey, col1, col2, col5, username, task, description, "1"));
//    }
//
//    public void addTask(String username, String task, String description, String time) {
//        db = this.getWritableDatabase();
//        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', %s)", TABLE_NAME, fkey, col1, col2, col3, col5, username, task, description, time, "1"));
//    }

    public void addTask(String username, String task, String description, String time, String duration) {
        db = this.getWritableDatabase();
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', %s, %s)", TABLE_NAME, fkey, col1, col2, col3, col4, col5, username, task, description, time, duration, "1");
        db.execSQL(query);
    }

    public Cursor getTask(int id) {
        db = this.getReadableDatabase();
        return db.rawQuery(String.format("SELECT * FROM %s WHERE id = '%s'", TABLE_NAME, Integer.toString(id)), null);
    }

    public Cursor getTasks(String username) {
        db = this.getReadableDatabase();
        return db.rawQuery(String.format("SELECT * FROM %s WHERE username = '%s'", TABLE_NAME, username), null);
    }

    public Cursor getTasks(String username, String date) {
        db = this.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE username = '%s' AND DATE(time) = '%s'", TABLE_NAME, username, date);
        Log.i("HAHAHA", query);
        return db.rawQuery(query, null);
    }

    public boolean allTasksFinished(String username, int numDays) {
        db = this.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE username = '%s' AND status = 1 AND julianday('now') - julianday(time) > 0 AND julianday('now') - julianday(time) < %s", TABLE_NAME, username, Integer.toString(numDays));
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    public void finishTask(int id) {
        db = this.getWritableDatabase();
        db.execSQL(String.format("UPDATE %s SET STATUS = 0 WHERE id = %s", TABLE_NAME, Integer.toString(id)));
    }

    public float getTaskCompletionRatio(String username) {
        db = this.getReadableDatabase();
        int completedTasks, totalTasks;
        String query = String.format("SELECT * FROM %s WHERE username = '%s' AND status = 0", TABLE_NAME, username);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0) {
            completedTasks = cursor.getCount();
            cursor.close();

        } else {
            cursor.close();
            return 0;
        }
        query = String.format("SELECT * FROM %s WHERE username = '%s'", TABLE_NAME, username);
        cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.getCount() > 0) {
            totalTasks = cursor.getCount();
            float taskCompletionRatio = (float) completedTasks / (float) totalTasks;
            cursor.close();
            return taskCompletionRatio;
        } else {
            cursor.close();
            return 0;
        }
    }

    public void deleteAllTasks() {
        db = this.getWritableDatabase();
        db.execSQL(String.format("DELETE FROM %s", TABLE_NAME));
    }




    public void close() {
        if (db != null) {
            db.close();
        }
    }


}
