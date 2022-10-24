package com.example.c196aloufi.UserInterface;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.MainScreenAssessmentAdapter;
import com.example.c196aloufi.Adapters.MainScreenCourseAdapter;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCourse extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;

    private DatePickerDialog endDatePickerDialog;

    private Button startDateButton;

    private Button endDateButton;

    private Button addCourseBtn;

    private Integer courseId;

    private Integer editTermId;

    private Spinner courseStatusBar;

    private EditText courseTitleTxt;

    private EditText courseNoteTxt;

    private EditText courseInstructorTxt;

    private EditText instructorPhoneTxt;

    private EditText instructorEmailAddressTxt;

    private String courseStatus;

    private String startDate;

    private String endDate;

    private String courseName;

    private String instructorName;

    private String instructorEmail;

    private String instructorPhone;

    private String courseNote;

    private String editCourseTitleTxt;

    private String editCourseNoteTxt;

    private String editInstructorTxt;

    private String editInstructorPhoneTxt;

    private String editInstructorEmailTxt;

    private String editCourseStatus;

    private String editEndDate;

    private String editStartDate;

    AppRepo appRepo;

    Courses courses;

    List<Assessments> assocAssess;

    List<Assessments> assessInCourse;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        appRepo = new AppRepo(getApplication());
        courseId = getIntent().getIntExtra("courseId", -1);
        editCourseTitleTxt = getIntent().getStringExtra("courseName");
        editInstructorTxt = getIntent().getStringExtra("instructorName");
        editInstructorPhoneTxt = getIntent().getStringExtra("instructorPhone");
        editInstructorEmailTxt = getIntent().getStringExtra("instructorEmail");
        editCourseNoteTxt = getIntent().getStringExtra("courseNote");
        editStartDate = getIntent().getStringExtra("startDate");
        editEndDate = getIntent().getStringExtra("endDate");
        editCourseStatus = getIntent().getStringExtra("courseStatus");
        editTermId = getIntent().getIntExtra("termId", -1);


        if (courseId == -1) {
            setUpView();
        } else {
            initDatePicker();
            initEndDatePicker();
            startDateButton = findViewById(R.id.startDateButton);
            startDateButton.setText(editStartDate);
            endDateButton = findViewById(R.id.endDateButton);
            endDateButton.setText(editEndDate);
            courseTitleTxt = findViewById(R.id.courseTitleTxt);
            courseTitleTxt.setText(editCourseTitleTxt);
            courseNoteTxt = findViewById(R.id.courseNoteTxt);
            courseNoteTxt.setText(editCourseNoteTxt);
            instructorPhoneTxt = findViewById(R.id.instructorPhoneTxt);
            instructorPhoneTxt.setText(editInstructorPhoneTxt);
            instructorEmailAddressTxt = findViewById(R.id.instructorEmailAddressTxt);
            instructorEmailAddressTxt.setText(editInstructorEmailTxt);
            courseInstructorTxt = findViewById(R.id.courseInstructorTxt);
            courseInstructorTxt.setText(editInstructorTxt);
            courseStatusBar = findViewById(R.id.courseStatusBar);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerAdapter, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            courseStatusBar.setAdapter(adapter);
            if (editCourseStatus != null) {
                int spinnerPosition = adapter.getPosition(editCourseStatus);
                courseStatusBar.setSelection(spinnerPosition);
            }
            addCourse();
            assocAssessments();
        }

    }

    private void setUpView() {
        initDatePicker();
        initEndDatePicker();
        startDateButton = findViewById(R.id.startDateButton);
        startDateButton.setText(getTodaysDate());
        endDateButton = findViewById(R.id.endDateButton);
        endDateButton.setText(getEndDate());
        courseTitleTxt = findViewById(R.id.courseTitleTxt);
        courseNoteTxt = findViewById(R.id.courseNoteTxt);
        instructorPhoneTxt = findViewById(R.id.instructorPhoneTxt);
        instructorEmailAddressTxt = findViewById(R.id.instructorEmailAddressTxt);
        courseInstructorTxt = findViewById(R.id.courseInstructorTxt);
        courseStatusBar = findViewById(R.id.courseStatusBar);
        courseStatus = String.valueOf(courseStatusBar.getSelectedItem());
        addCourse();
    }

    private void assocAssessments() {
        RecyclerView recyclerView = findViewById(R.id.assessViewRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        final MainScreenAssessmentAdapter assessAdapter= new MainScreenAssessmentAdapter(this);
        recyclerView.setAdapter(assessAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assocAssess = appRepo.getAllAssessments();
        assessInCourse = new ArrayList<>();
        for (Assessments assessments : assocAssess) {
            if (assessments.getCourseId() == courseId) {
                assessInCourse.add(assessments);
            }
        }
        assessAdapter.setAssessments(assessInCourse);
    }

    private void addCourse() {
        addCourseBtn = findViewById(R.id.addCourseBtn);
        addCourseBtn.setOnClickListener( v -> {

            courseName = courseTitleTxt.getText().toString();
            instructorName = courseInstructorTxt.getText().toString();
            instructorEmail = instructorEmailAddressTxt.getText().toString();
            instructorPhone = instructorPhoneTxt.getText().toString();
            courseStatus = courseStatusBar.getSelectedItem().toString();
            startDate = startDateButton.getText().toString();
            endDate = endDateButton.getText().toString();
            courseNote = courseNoteTxt.getText().toString();

            if (isNull()) {
                return;
            }
            if (courseId == -1) {
                int newCourseId = appRepo.getAllCourses().get(appRepo.getAllCourses().size() - 1).getCourseId() +1;
                courses = new Courses(newCourseId, courseName, instructorName, instructorEmail, instructorPhone, courseStatus, startDate, endDate, courseNote, editTermId);
                appRepo.insert(courses);
                Toast.makeText(AddCourse.this, "New Course Created.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddCourse.this, CourseList.class);
                startActivity(intent);
            }else {
                courses = new Courses(courseId, courseName, instructorName, instructorEmail, instructorPhone, courseStatus, startDate, endDate, courseNote, editTermId);
                appRepo.update(courses);
                Toast.makeText(AddCourse.this, "Course has been updated.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddCourse.this, CourseList.class);
                startActivity(intent);
            }
        });
    }
    private boolean isNull() {
        if (courseTitleTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The new course must have a name.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (startDate == null) {
            Toast.makeText(this, "The new course must have a start date.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (endDate == null) {
            Toast.makeText(this, "The new course must have a completion date.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (courseInstructorTxt == null) {
            Toast.makeText(this, "The new course must have an Instructor.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (instructorPhoneTxt == null) {
            Toast.makeText(this, "The new course must have an Instructor phone number.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (instructorEmailAddressTxt == null) {
            Toast.makeText(this, "The new course must have an Instructor email address.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (courseStatusBar == null) {
            Toast.makeText(this, "The new course must have a status.", Toast.LENGTH_SHORT).show();
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

    private String getEndDate()  {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        endDateString(day, month, year);
        return endDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String startDate = makeDateString(dayOfMonth, month, year);
            startDateButton.setText(startDate);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String endDate = endDateString(dayOfMonth, month, year);
            endDateButton.setText(endDate);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        endDatePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private String makeDateString(int dayOfMonth, int month, int year) {
        month = month + 1;
        return getDateFormat(month) + " " + dayOfMonth + " " + year;
    }

    private String endDateString (int dayOfMonth, int month, int year) {
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

    public void openStartDate(View view) {
        datePickerDialog.show();
    }

    public void openEndDate(View view) {
        endDatePickerDialog.show();
    }
}

