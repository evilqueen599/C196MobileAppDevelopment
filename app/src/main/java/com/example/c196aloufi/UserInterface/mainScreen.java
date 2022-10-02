package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.TermAdapter;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Terms;
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

        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Terms> terms = appRepo.getAllTerms();
        final TermAdapter termAdapter = new TermAdapter(this);
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
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Terms> terms = appRepo.getAllTerms();
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);
    }

    public void onClickCourses(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Course List");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("View Courses");
    }

    public void onClickAssess(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Assessment List");
        Button mainAddButton = (Button) findViewById(R.id.mainAddButton);
        mainAddButton.setText("View Assessments");
    }
}

