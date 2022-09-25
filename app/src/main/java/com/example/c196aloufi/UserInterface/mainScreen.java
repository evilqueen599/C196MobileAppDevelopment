package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;
import com.example.c196aloufi.Utility.TestData;

import java.util.ArrayList;
import java.util.List;


public class mainScreen extends AppCompatActivity {

    public Button mainAddButton;

    private ListView listView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        final RadioButton termRad = (RadioButton) findViewById(R.id.termRadBtn);
        final RadioButton courseRad = (RadioButton) findViewById(R.id.coursesRadBtn);
        final RadioButton assessRad = (RadioButton) findViewById(R.id.assessRadBtn);
        final Button go = (Button) findViewById(R.id.mainAddButton);


        List<Terms> termData = new ArrayList<>();

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

