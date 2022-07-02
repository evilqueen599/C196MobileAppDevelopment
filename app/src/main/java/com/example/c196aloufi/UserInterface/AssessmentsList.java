package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AssessmentsList extends AppCompatActivity {
    public void addAssessBackButton(View view) {
        Intent intent = new Intent(AssessmentsList.this, DetailedTerm.class);
        startActivity(intent);
    }

    public void addAssessBtn(View view) {
        Intent intent = new Intent(AssessmentsList.this, AddAssessment.class);
        startActivity(intent);
    }
}
