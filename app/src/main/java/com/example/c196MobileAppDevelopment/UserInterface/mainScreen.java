package com.example.c196MobileAppDevelopment.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196MobileAppDevelopment.Adapters.MainScreenAssessmentAdapter;
import com.example.c196MobileAppDevelopment.Adapters.MainScreenCourseAdapter;
import com.example.c196MobileAppDevelopment.Adapters.MainScreenTermAdapter;
import com.example.c196MobileAppDevelopment.Database.AppRepo;
import com.example.c196MobileAppDevelopment.Model.Assessments;
import com.example.c196MobileAppDevelopment.Model.Courses;
import com.example.c196MobileAppDevelopment.Model.Terms;
import com.example.c196aloufi.R;

import java.util.List;


public class mainScreen extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        final RadioButton termRad = (RadioButton) findViewById(R.id.termRadBtn);
        final RadioButton courseRad = (RadioButton) findViewById(R.id.coursesRadBtn);
        final RadioButton assessRad = (RadioButton) findViewById(R.id.assessRadBtn);
        final Button go = (Button) findViewById(R.id.mainAddButton);

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Terms> terms = appRepo.getAllTerms();
        final MainScreenTermAdapter termAdapter = new MainScreenTermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termRad.isChecked()) {
                    Intent intent = new Intent(mainScreen.this,DetailedTerm.class);
                    startActivity(intent);
                } else if (courseRad.isChecked()) {
                    Intent intent = new Intent(mainScreen.this,CourseList.class);
                    startActivity(intent);
                } else if (assessRad.isChecked()) {
                    Intent intent = new Intent(mainScreen.this,AssessmentsList.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void onClickTerms(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Term List");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("View Terms");

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Terms> terms = appRepo.getAllTerms();
        final MainScreenTermAdapter mainScreenTermAdapter = new MainScreenTermAdapter(this);
        recyclerView.setAdapter(mainScreenTermAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainScreenTermAdapter.setTerms(terms);
    }

    public void onClickCourses(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Course List");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("View Courses");

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Courses> courses = appRepo.getAllCourses();
        final MainScreenCourseAdapter mainScreenCourseAdapter = new MainScreenCourseAdapter(this);
        recyclerView.setAdapter(mainScreenCourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainScreenCourseAdapter.setCourses(courses);
    }

    public void onClickAssess(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Assessment List");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("View Assessments");

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Assessments> assessments = appRepo.getAllAssessments();
        final MainScreenAssessmentAdapter mainScreenAssessmentAdapter = new MainScreenAssessmentAdapter(this);
        recyclerView.setAdapter(mainScreenAssessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainScreenAssessmentAdapter.setAssessments(assessments);
    }
}

