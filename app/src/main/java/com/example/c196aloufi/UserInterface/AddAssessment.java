package com.example.c196aloufi.UserInterface;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.R;

import java.util.Calendar;

public class AddAssessment extends AppCompatActivity {
        private DatePickerDialog datePickerDialog;
        Integer assessmentId;
        String assessmentName;
        String editAssessmentName;
        String editAssessmentType;
        String editEndDate;
        String endDate;
        String assessmentType;
        Spinner assessmentTypeSelect;
        EditText assessNameTxt;
        Button endAssessPickerBtn;
        Button addAssessmentBtn;
        AppRepo appRepo;
        Assessments assessments;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_add_assessment);
                appRepo = new AppRepo(getApplication());
                assessmentId = getIntent().getIntExtra("assessmentId", -1);
                editAssessmentName = getIntent().getStringExtra("assessmentTitle");
                editAssessmentType = getIntent().getStringExtra("assessmentType");
                editEndDate = getIntent().getStringExtra("endDate");
                if (assessmentId == -1) {
                          setUpView();
                } else {
                        assessNameTxt = findViewById(R.id.assessNameTxt);
                        assessNameTxt.setText(editAssessmentName);
                        endAssessPickerBtn = findViewById(R.id.endAssessPickerButton);
                        endAssessPickerBtn.setText(editEndDate);
                        assessmentTypeSelect = findViewById(R.id.assessmentStatusBar);
                        assessmentType = String.valueOf(assessmentTypeSelect.getSelectedItem());
                        initDatePicker();
                        addNewAssessment();

                }
        }

       private void setUpView() {
                assessNameTxt =findViewById(R.id.assessNameTxt);
                initDatePicker();
                endAssessPickerBtn = findViewById(R.id.endAssessPickerButton);
                endAssessPickerBtn.setText(getTodaysDate());
                assessmentTypeSelect = findViewById(R.id.assessmentStatusBar);
                assessmentType = String.valueOf(assessmentTypeSelect.getSelectedItem());
                addNewAssessment();
        }

        private void addNewAssessment() {
                addAssessmentBtn = findViewById(R.id.addAssessmentBtn);
                addAssessmentBtn.setOnClickListener(v -> {
                        assessmentName = assessNameTxt.getText().toString();
                        if (isNull()) {
                                return;
                        }
                        endDate = endAssessPickerBtn.getText().toString();



                        if (assessmentId == -1) {
                                int newAssessId = appRepo.getAllAssessments().get(appRepo.getAllAssessments().size() - 1).getAssessmentId() + 1;
                                assessments = new Assessments(newAssessId, assessmentName, endDate, assessmentTypeSelect.getSelectedItem().toString(), 0);
                                appRepo.insert(assessments);
                                Toast.makeText(AddAssessment.this, "New Assessment Created.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddAssessment.this, AssessmentsList.class);
                                startActivity(intent);
                        } else {
                                assessments = new Assessments(assessmentId, assessmentName, endDate, assessmentTypeSelect.getSelectedItem().toString(), 0);
                                appRepo.update(assessments);
                                Toast.makeText(AddAssessment.this, "Assessment has been updated.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddAssessment.this, AssessmentsList.class);
                                startActivity(intent);
                        }

                });
        }

        public boolean isNull() {
                if (assessNameTxt.getText().toString().isEmpty()) {
                        Toast.makeText(this, "The new assessment must have a name.", Toast.LENGTH_SHORT).show();
                        return true;
                } else if (getTodaysDate() == null) {
                        Toast.makeText(this, "The new assessment must have a completion date.", Toast.LENGTH_SHORT).show();
                        return true;
                } else if (assessmentType == null) {
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
                        endAssessPickerBtn.setText(dueDate);
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
                if (month == 12)
                        return "Dec";
                return "Jan";
        }

        public void openDueDate(View view) {
                datePickerDialog.show();
        }

        public void getAssessType() {

        }
}



