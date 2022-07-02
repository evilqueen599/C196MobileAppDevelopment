package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.R;

public class AssessmentsList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_list);
    }

    public void addAssessBackButton(View view) {
        Intent intent = new Intent(AssessmentsList.this, DetailedTerm.class);
        startActivity(intent);
    }

    public void addAssessBtn(View view) {
        Intent intent = new Intent(AssessmentsList.this, AddAssessment.class);
        startActivity(intent);
    }
}
