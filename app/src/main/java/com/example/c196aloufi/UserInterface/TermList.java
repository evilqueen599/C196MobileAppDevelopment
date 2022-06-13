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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        final RadioButton termRad = (RadioButton) findViewById(R.id.termRadBtn);
        final RadioButton courseRad = (RadioButton) findViewById(R.id.coursesRadBtn);
        final RadioButton assessRad = (RadioButton) findViewById(R.id.assessRadBtn);
        final FloatingActionButton go = (FloatingActionButton) findViewById(R.id.mainAddBtn);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (termRad.isChecked()) {
                    Intent intent = new Intent(TermList.this,DetailedTerm.class);
                    startActivity(intent);
                } else if (courseRad.isChecked()) {
                    setContentView(R.layout.activity_course_list);
                } else if (assessRad.isChecked()) {
                    setContentView(R.layout.activity_assessments_list);
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

