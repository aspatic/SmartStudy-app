//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String pkey = "username";
    private static final String col1 = "email";
    private static final String col2 = "password";
    SQLiteDatabase db;



    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s (%s varchar(64) PRIMARY KEY, %s varchar(64), %s varchar(64))", TABLE_NAME, pkey, col1, col2));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

    public void addUser(String username, String email, String password) {
        db = this.getWritableDatabase();
        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s) VALUES ('%s', '%s', '%s')", TABLE_NAME, pkey, col1, col2, username, email, password));
    }

    public boolean usernameExists(String username) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE username = '%s'", TABLE_NAME, username), null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean emailExists(String email) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE email = '%s'", TABLE_NAME, email), null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean loginSuccessful(String email, String password) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE email = '%s' AND password = '%s'", TABLE_NAME, email, password), null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public String getUsername(String email) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT username FROM %s WHERE email = '%s'", TABLE_NAME, email), null);
        cursor.moveToFirst();
        String res = cursor.getString(cursor.getColumnIndex("username"));
        cursor.close();
        return res;
    }

    public String getEmail(String username) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT email FROM %s WHERE username = '%s'", TABLE_NAME, username), null);
        cursor.moveToFirst();
        String res = cursor.getString(cursor.getColumnIndex("email"));
        cursor.close();
        return res;
    }

    public String getPassword(String username) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT password FROM %s WHERE username = '%s'", TABLE_NAME, username), null);
        cursor.moveToFirst();
        String res = cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return res;
    }


    public void close() {
        if (db != null) {
            db.close();
        }
    }


}
