package com.example.c196aloufi.Database;

import androidx.room.Database;

import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.Model.Terms;

@Database(entities={Terms.class, Assessments.class, Courses.class}, version = 1, exportSchema = false)
public class AppDatabase {
    
}


