package com.example.c196aloufi.UserInterface;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;


public class AddAssessment extends AppCompatActivity {
        private DatePickerDialog datePickerDialog;
        private DatePickerDialog startPickerDialog;
        Integer assessmentId;
        Integer editCourseId;
        String assessmentName;
        String editAssessmentName;
        String editAssessmentType;
        String editStartDate;
        String editEndDate;
        String startDate;
        String endDate;
        String assessmentType;
        Spinner assessmentTypeSelect;
        EditText assessNameTxt;
        Button endAssessPickerBtn;
        Button startAssessPickerBtn;
        Button addAssessmentBtn;
        AppRepo appRepo;
        Assessments assessments;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_add_assessment);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                appRepo = new AppRepo(getApplication());
                assessmentId = getIntent().getIntExtra("assessmentId", -1);
                editAssessmentName = getIntent().getStringExtra("assessmentTitle");
                editAssessmentType = getIntent().getStringExtra("assessmentType");
                editStartDate = getIntent().getStringExtra("startDate");
                editEndDate = getIntent().getStringExtra("endDate");
                editCourseId = getIntent().getIntExtra("courseId", -1);
                if (assessmentId == -1) {
                          setUpView();
                } else {
                        assessNameTxt = findViewById(R.id.assessNameTxt);
                        assessNameTxt.setText(editAssessmentName);
                        startAssessPickerBtn = findViewById(R.id.startAssessPickerButton);
                        startAssessPickerBtn.setText(editStartDate);
                        endAssessPickerBtn = findViewById(R.id.endAssessPickerButton);
                        endAssessPickerBtn.setText(editEndDate);
                        assessmentTypeSelect = findViewById(R.id.assessmentStatusBar);
                        initDatePicker();
                        initStartPicker();
                        addNewAssessment();
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.assessmentSpinnerAdapter, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
                        assessmentTypeSelect.setAdapter(adapter);
                        if (editAssessmentType != null) {
                                int spinnerPosition = adapter.getPosition(editAssessmentType);
                                assessmentTypeSelect.setSelection(spinnerPosition);
                        }
                }

                }

       private void setUpView() {
                assessNameTxt =findViewById(R.id.assessNameTxt);
                initDatePicker();
                initStartPicker();
                endAssessPickerBtn = findViewById(R.id.endAssessPickerButton);
                startAssessPickerBtn = findViewById(R.id.startAssessPickerButton);
                startAssessPickerBtn.setText(getTodaysDate());
                endAssessPickerBtn.setText(getTodaysDate());
                assessmentTypeSelect = findViewById(R.id.assessmentStatusBar);
                assessmentType = String.valueOf(assessmentTypeSelect.getSelectedItem());
                addNewAssessment();
        }

        private void addNewAssessment() {
                addAssessmentBtn = findViewById(R.id.addAssessmentBtn);
                addAssessmentBtn.setOnClickListener(v -> {
                        assessmentName = assessNameTxt.getText().toString();
                        startDate = startAssessPickerBtn.getText().toString();
                        endDate = endAssessPickerBtn.getText().toString();
                        assessmentType = assessmentTypeSelect.getSelectedItem().toString();
                        if (isNull()) {
                                return;
                        }
                        if (assessmentId == -1) {
                                int newAssessId = appRepo.getAllAssessments().get(appRepo.getAllAssessments().size() - 1).getAssessmentId() + 1;
                                assessments = new Assessments(newAssessId, assessmentName, startDate, endDate, assessmentType, editCourseId);
                                appRepo.insert(assessments);
                                Toast.makeText(AddAssessment.this, "New Assessment Created.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddAssessment.this, AssessmentsList.class);
                                startActivity(intent);
                        } else {
                                assessments = new Assessments(assessmentId, assessmentName, startDate, endDate, assessmentType, editCourseId);
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
                } else if (startDate == null) {
                        Toast.makeText(this, "The new assessment must have a start date.", Toast.LENGTH_SHORT).show();
                        return true;
                }else if (endDate == null) {
                        Toast.makeText(this, "The new assessment must have a completion date.", Toast.LENGTH_SHORT).show();
                        return true;
                }else if (assessmentType == null) {
                        Toast.makeText(this, "The new assessment must have a type.", Toast.LENGTH_SHORT).show();
                        return true;
                } else return false;
        }

        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.assess_menu, menu);
                return true;
        }

        public boolean onOptionsItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                        case android.R.id.home:
                                this.finish();
                                return true;

                        case R.id.homeScreen:
                                Intent intent = new Intent(AddAssessment.this, mainScreen.class);
                                startActivity(intent);
                                return true;

                        case R.id.assess_start_date_notification:
                                editStartDate = startAssessPickerBtn.getText().toString();
                                DateFormat mStart = null;
                                mStart = SimpleDateFormat.getDateInstance(Integer.parseInt(editStartDate), Locale.US);


                        case R.id.assess_due_date_notification:
                                return true;

                }
                return super.onOptionsItemSelected(menuItem);
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
        private void initStartPicker() {
                DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
                        String startDate = makeDateString(dayOfMonth, month, year);
                        startAssessPickerBtn.setText(startDate);
                };
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int style = AlertDialog.THEME_HOLO_DARK;
                startPickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
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
        public void openStartDate(View view) {
                startPickerDialog.show();
        }
}



