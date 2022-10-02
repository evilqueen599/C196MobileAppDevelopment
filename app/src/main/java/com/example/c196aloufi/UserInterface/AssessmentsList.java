package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.AssessmentAdapter;
import com.example.c196aloufi.Adapters.TermAdapter;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;

import java.util.List;

public class AssessmentsList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_list);

        RecyclerView recyclerView = findViewById(R.id.assessmentsRecyclerView);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Assessments> assessments = appRepo.getAllAssessments();
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(assessments);
    }

    public void addAssessBtn(View view) {
        Intent intent = new Intent(AssessmentsList.this, AddAssessment.class);
        startActivity(intent);
    }
}
