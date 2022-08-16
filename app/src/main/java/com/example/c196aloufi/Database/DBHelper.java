package com.example.c196aloufi.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wguDB";
    private static final int DATABASE_VERSION = 1;


    public DBHelper( Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createDatabase () {
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS terms (id INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, title TEXT, start_date DATE, end_date DATE)");
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS courses (id INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, title TEXT, instructorName Text, instructorEmail TEXT, instructorPhone TEXT," +
                "courseStatus Text, start_date DATE, end_date DATE, courseNotes TEXT, assocTerm INTEGER)");
        this.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS assessments (id INTEGER PRIMARY KEY " +
                "AUTOINCREMENT,  title TEXT, due_date DATE, )");
    }
}
