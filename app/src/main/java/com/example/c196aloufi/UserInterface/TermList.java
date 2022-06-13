package com.example.c196aloufi.UserInterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.c196aloufi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class TermList extends Activity {
    private TextView termListTxt;
    private RadioGroup radioGroup;
    private RadioButton termRadBtn;
    private RadioButton coursesRadBtn;
    private RadioButton assessRadBtn;
    private FloatingActionButton mainAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        final RadioButton termRad = (RadioButton) findViewById(R.id.termRadBtn);
        final RadioButton courseRad = (RadioButton) findViewById(R.id.coursesRadBtn);
        final RadioButton assessRad = (RadioButton) findViewById(R.id.assesRadBtn);

        final Button go = findViewById(R.id.mainAddBtn);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (termRad.isChecked()) {
                    Intent intent = new Intent(TermList.this, DetailedTermList.class);
                    startActivity(intent);
                    setContentView(R.layout.activity_detailed_term_list);
                } else if (courseRad.isChecked()) {
                    Intent intent1 = new Intent(getApplicationContext(), CourseList.class);
                    startActivityForResult(intent1, 0);

                } else if (assessRad.isChecked()) {
                    Intent intent2 = new Intent(getApplicationContext(), AssessmentList.class);
                    startActivityForResult(intent2, 0);
                }
            }
        });
    }

    public void onClickTerms(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Term List");
    }

    public void onClickCourses(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Course List");
    }

    public void onClickAssess(View view) {
        TextView myTextView = (TextView) findViewById(R.id.termListTxt);
        myTextView.setText("Assessment List");
    }
}

