package com.example.c196aloufi.UserInterface;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;

import java.util.Calendar;

public class AddAssessment extends AppCompatActivity {
        private DatePickerDialog datePickerDialog;
        private Button dueDateButton;
        Integer assessID;
        String assessName;
        String assessType;
        String endDate;
        CheckBox oaBtn;
        CheckBox paBtn;
        EditText assessNameTxt;
        Button endAssessPickerBtn;
        Button addAssessBtn;

        AppRepo appRepo;
        Assessments assessments;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                appRepo = new AppRepo(getApplication());
                assessID = getIntent().getIntExtra("assessmentId", -1);
                assessName = getIntent().getStringExtra("assessmentTitle");
                assessType = getIntent().getStringExtra("assessmentType");
                endDate = getIntent().getStringExtra("endDate");

                setContentView(R.layout.activity_add_assessment);
                initDatePicker();
                dueDateButton = findViewById(R.id.endAssessPickerButton);
                dueDateButton.setText(getTodaysDate());

                if (assessID == -1) {
                        setUpView();
                } else {
                         = findViewById(R.id.assessNameTxt);
                        assessNameTxt.setText(assessName);
                        endAssessPickerBtn = findViewById(R.id.endAssessPickerButton);
                        endAssessPickerBtn.setText(endDate);
                        initDatePicker();
                        addNewAssessment();
                }
        }

        private void setUpView() {
                assessName = findViewById(R.id.assessNameTxt).toString();
                initDatePicker();
                endAssessPickerBtn = findViewById(R.id.endAssessPickerButton);
                endAssessPickerBtn.setText(endDate);
                addNewAssessment();
        }

        private void addNewAssessment() {
                addAssessBtn = findViewById(R.id.addAssessmentBtn);
                addAssessBtn.setOnClickListener(v -> {
                        assessName = assessNameTxt.getText().toString();
                        if (isNull()) {
                                return;
                        }
                        endDate = endAssessPickerBtn.getText().toString();

                        if (assessID == -1) {
                                int newAssessId = appRepo.getAllAssessments().get(appRepo.getAllAssessments().size() -1).getAssessmentId() +1;
                                assessments = new Assessments(newAssessId, assessName, endDate, assessType, null);
                                appRepo.insert(assessments);
                                Toast.makeText(AddAssessment.this, "New Assessment Created.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddAssessment.this, AssessmentsList.class);
                                startActivity(intent);
                        }
                        else {
                                assessments = new Assessments(assessID, assessName, endDate, assessType, null);
                                appRepo.update(assessments);
                                Toast.makeText(AddAssessment.this, "Assessment has been updated.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddAssessment.this, AssessmentsList.class);
                                startActivity(intent);
                        }

                });
        }

        public boolean isNull() {
                if (assessNameTxt.getText().toString().isEmpty()) {
                        Toast.makeText(this, "The new assessment must have a name.", Toast.LENGTH_SHORT).show();
                        return true;
                } else if (endDate == null) {
                        Toast.makeText(this, "The new assessment must have a completion date.", Toast.LENGTH_SHORT).show();
                        return true;
                } else if (assessType == null) {
                        Toast.makeText(this, "The new assessment must have a type.", Toast.LENGTH_SHORT).show();
                        return true;
                } else return false;
        }

        private String getTodaysDate() {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                makeDateString(day, month, year);
                return makeDateString(day, month, year);

        }

        private void initDatePicker() {
                DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
                        String dueDate = makeDateString(dayOfMonth, month, year);
                        dueDateButton.setText(dueDate);
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int style = AlertDialog.THEME_HOLO_DARK;
                datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        }

        private String makeDateString(int dayOfMonth, int month, int year) {
                month = month + 1;
                return getDateFormat(month) + " " + dayOfMonth + " " + year;
        }

        private String getDateFormat(int month) {
                if (month == 1)
                        return "Jan";
                if (month == 2)
                        return "Feb";
                if (month == 3)
                        return "Mar";
                if (month == 4)
                        return "Apr";
                if (month == 5)
                        return "May";
                if (month == 6)
                        return "Jun";
                if (month == 7)
                        return "Jul";
                if (month == 8)
                        return "Aug";
                if (month == 9)
                        return "Sep";
                if (month == 10)
                        return "Oct";
                if (month == 11)
                        return "Nov";
                if (month ==12)
                        return "Dec";
                return "Jan";
        }

        public void openDueDate(View view) {
                datePickerDialog.show();
        }

}



