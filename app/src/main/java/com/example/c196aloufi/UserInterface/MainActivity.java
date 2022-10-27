package com.example.c196aloufi.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRepo appRepo = new AppRepo(getApplication());
        Terms terms = new Terms(1, "Test Data","05/09/2022", "12/25/2022");
        appRepo.insert(terms);
        Courses courses = new Courses(1, "Application Development", "Test Instructor",
                "testemail@test.com", "954-778-9008", "InProgress","10/26/2022", "12/26/2022", "Finish this class ASAP", 1);
       appRepo.insert(courses);
       Assessments assessments = new Assessments(1, "Final Project","10/26/2022", "11/28/2023", "Practice Assessment", 1);
       appRepo.insert(assessments);
    }

    public void enterButton(View view) {
        Intent intent = new Intent(MainActivity.this, mainScreen.class);
        startActivity(intent);
    }
}
