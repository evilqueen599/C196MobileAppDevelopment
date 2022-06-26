package com.example.c196aloufi.UserInterface;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.Database.CourseDB;
import com.example.c196aloufi.R;

public class AddCourse extends AppCompatActivity {

    private EditText courseName, courseTrack, courseDuration, courseDescription;
    private Button addCourseBtn;
    private CourseDB courseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseName = findViewById(R.id.courseName);
        courseTrack = findViewById(R.id.courseTracks);
        courseDuration = findViewById(R.id.courseDuration);
        courseDescription = findViewById(R.id.courseDescription);
        addCourseBtn = findViewById(R.id.addCourseButton);

        courseDB = new CourseDB(AddCourse.this);

        addCourseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String courseNameTxt = courseName.getText().toString();
                String courseTrackTxt = courseTrack.getText().toString();
                String courseDurationTxt = courseDuration.getText().toString();
                String courseDescriptionTxt = courseDescription.getText().toString();

                if (courseNameTxt.isEmpty() && courseTrackTxt.isEmpty() && courseDurationTxt.isEmpty() && courseDescriptionTxt.isEmpty()) {
                    Toast.makeText(AddCourse.this, "Please Enter appropriate field values before attempting to add the course.", Toast.LENGTH_SHORT).show();
                    return;
                }

                courseDB.addNewCourse(courseNameTxt, courseDurationTxt, courseDescriptionTxt, courseTrackTxt);

                Toast.makeText(AddCourse.this, "The course has been added.", Toast.LENGTH_SHORT).show();

                courseName.setText(" ");
                courseDuration.setText(" ");
                courseTrack.setText(" ");
                courseDescription.setText(" ");
            }

        });


    }
}
