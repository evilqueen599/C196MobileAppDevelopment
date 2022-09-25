package com.example.c196aloufi.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.Model.Terms;

@Database(entities={Terms.class, Assessments.class, Courses.class}, version = 1, exportSchema = false)
public class AppDatabase extends RoomDatabase {
    public abstract TermDAO termDAO();

    public abstract AssessmentDAO assessmentDAO();

    public abstract CourseDAO courseDAO();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized ((AppDatabase.class)) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "myAppDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
