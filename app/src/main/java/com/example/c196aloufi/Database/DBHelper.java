package com.example.c196aloufi.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;

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
                "AUTOINCREMENT,  title TEXT, due_date DATE, performanceAssess BOOLEAN, objectiveAssess BOOLEAN, assoc_Course INTEGER)");
    }

    public TermDAO getTermByID (String id) {
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM terms WHERE id =" + id, null);
        TermDAO term = null;
        while(cursor.moveToNext()) {
            Date startDate = Date.valueOf(cursor.getString(cursor.getColumnIndex("start_date")));
            Date endDate = Date.valueOf(cursor.getString(cursor.getColumnIndex("end_date")));
            term = new TermDAO(cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")), startDate, endDate);
        }
        return term;
    }

    public void addTerm(String title, Date startDate, Date endDate) {
        this.getWritableDatabase().execSQL("INSERT INTO terms (title, start_date, end_date) VALUES ('"+ title +"', '"+ startDate +"', '"+ endDate +"')");
    }

    public void updateTerm(TermDAO term) {
        this.getWritableDatabase().execSQL("UPDATE terms SET title = '"+ term.getTitle() +"', start_date = '"+ term.getStartDate() +
                "', end_date = '"+ term.getEndDate() +"' WHERE id = "+ term.getId());
    }
    public void populateDatabase() {
        addTerm("Science Term", Date.valueOf("2020-01-01"), Date.valueOf("2020-06-30"));
        addTerm("Arts Term", Date.valueOf("2020-07-01"), Date.valueOf("2021-12-31"));

    }

}
