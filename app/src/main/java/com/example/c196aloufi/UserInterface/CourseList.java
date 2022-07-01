package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.R;


public class CourseList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
    }

    public void addACourseBtn(View view) {
        Intent intent = new Intent(CourseList.this, AddCourse.class);
        startActivity(intent);
    }

    public void addCourseBackButton(View view) {
        Intent intent = new Intent(CourseList.this, TermList.class);
        startActivity(intent);
    }
}