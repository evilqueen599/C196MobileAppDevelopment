package com.example.c196aloufi.UserInterface;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.c196aloufi.R;


public class TermList extends Activity {
    private TextView termListTxt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
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

    public void onClickMainAddBtn(View view) {
        setContentView(R.layout.activity_detailed_term_list);
    }
}
