package com.example.c196MobileAppDevelopment.UserInterface;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196MobileAppDevelopment.Adapters.MainScreenAssessmentAdapter;
import com.example.c196MobileAppDevelopment.Database.AppRepo;
import com.example.c196MobileAppDevelopment.Model.Assessments;
import com.example.c196MobileAppDevelopment.Model.Courses;
import com.example.c196MobileAppDevelopment.MyBroadcastReceiver;
import com.example.c196aloufi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddCourse extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;

    private DatePickerDialog endDatePickerDialog;

    private Button startDateButton;

    private Button endDateButton;

    private Button addCourseBtn;

    private FloatingActionButton addAssessmentsBtn;

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

    MainScreenAssessmentAdapter assessmentAdapter;

    List<Assessments> assocAssess;

    List<Assessments> assessInCourse;

    List<Assessments> unassignedAssessments;

    SimpleDateFormat formatter;

    String format;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        format = "MM/dd/yyyy";
        formatter = new SimpleDateFormat(format, Locale.US);
        assessmentAdapter = new MainScreenAssessmentAdapter(this);
        appRepo = new AppRepo(getApplication());
        addAssessmentsBtn = findViewById(R.id.addAssessmentsBtn);
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
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assocAssess = appRepo.getAllAssessments();
        assessInCourse = new ArrayList<>();
        for (Assessments assessments : assocAssess) {
            if (assessments.getCourseId() == courseId) {
                assessInCourse.add(assessments);
            }
        }
        assessmentAdapter.setAssessments(assessInCourse);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                    DialogInterface.OnClickListener assessDeleteClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Assessments assessments = assessmentAdapter.getAssessment(viewHolder.getAbsoluteAdapterPosition());
                                    assessInCourse.remove(assessments);
                                    assessmentAdapter.notifyItemRemoved(which);
                                    assessmentAdapter.setAssessments(assessInCourse);
                                    overWriteAssessment(assessments, -1);
                                    Toast.makeText(AddCourse.this, "Assessment has been removed from this course.", Toast.LENGTH_SHORT).show();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    assessmentAdapter.notifyDataSetChanged();
                                    Toast.makeText(AddCourse.this, "Assessment has not been removed from this course.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(AddCourse.this);
                    alert.setMessage("Do you want to remove this assessment from this course?").setPositiveButton("Yes", assessDeleteClickListener)
                            .setNegativeButton("No", assessDeleteClickListener).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).attachToRecyclerView(recyclerView);
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
        else if (courseInstructorTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The new course must have an Instructor.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (instructorPhoneTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The new course must have an Instructor phone number.", Toast.LENGTH_SHORT).show();
            return true;
        }else if (instructorEmailAddressTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The new course must have an Instructor email address.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (courseStatusBar == null) {
            Toast.makeText(this, "The new course must have a status.", Toast.LENGTH_SHORT).show();
            return true;
        } else return false;
    }

    public void addAssessmentToCourse(View view) {
        unassignedAssessments = new ArrayList<>();
        for (Assessments assessments : assocAssess) {
            if (assessments.getCourseId() <= -1) {
                unassignedAssessments.add(assessments);
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add An Assessment To This Term");
        builder.setMessage("Do you want to select an existing assessment to add to this term or create a new assessment for this term?");
        builder.setIcon(R.drawable.ic_round_add_task_24);
        builder.setPositiveButton("New Assessment",(dialog, id) -> {
            if (assessInCourse.size() < 5) {
                dialog.dismiss();
                Intent intent = new Intent(this, AddAssessment.class);
                intent.putExtra("courseId", courseId);
                this.startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "There are already five assessments assigned to this course.Remove an assessment if you wish to assign another.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Existing Assessment", (dialog, id) -> {
            if (assessInCourse.size() < 5) {
                if (unassignedAssessments.size() >= 1) {
                    final AssessDropDownMenu assessMenu = new AssessDropDownMenu(this, unassignedAssessments);
                    assessMenu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                    assessMenu.setWidth(getPxFromDisplay(250));
                    assessMenu.setOutsideTouchable(true);
                    assessMenu.setFocusable(true);
                    assessMenu.showAsDropDown(addAssessmentsBtn);
                    assessMenu.setSelectedAssessListener((position, assessments) -> {
                        assessInCourse.add(assessments);
                        assessmentAdapter.notifyItemInserted(position);
                        assessmentAdapter.setAssessments(assessInCourse);
                        assessMenu.dismiss();
                        assessments.setCourseId(courseId);
                        overWriteAssessment(assessments, courseId);
                        Toast.makeText(getApplicationContext(), "Assessment has been assigned to this course.", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "There are no unassigned assessments. Please create a new assessment to add to this course.", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getApplicationContext(), "There are already five assessments assigned to this course.Remove an assessment if you wish to assign another.", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void overWriteAssessment(Assessments assessments, Integer courseId) {
        assessments.setCourseId(courseId);
        appRepo.update(assessments);
    }

    private int getPxFromDisplay(int dp) {
        return(int) (dp*getResources().getDisplayMetrics().density);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent intent = new Intent(AddCourse.this, mainScreen.class);
                startActivity(intent);
                return true;

            case R.id.startDateAlert:
                editStartDate = startDateButton.getText().toString();
                Date mStart = null;
                try {
                    mStart = formatter.parse(editStartDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long startTrigger = mStart.getTime();
                Intent addCourseIntent=new Intent(AddCourse.this, MyBroadcastReceiver.class);
                addCourseIntent.putExtra("key","The Course " + getIntent().getStringExtra("courseName") + " begins today " + getIntent().getStringExtra("startDate"));
                PendingIntent startSender=PendingIntent.getBroadcast(AddCourse .this, (1280000 + courseId),addCourseIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager1=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP,startTrigger,startSender);
                Toast.makeText(AddCourse.this, "Course start date notification enabled.", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.endDateAlert:
                editEndDate = endDateButton.getText().toString();
                Date mEnd = null;
                try {
                    mEnd = formatter.parse(editEndDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTrigger = mEnd.getTime();
                Intent endIntent=new Intent(AddCourse.this, MyBroadcastReceiver.class);
                endIntent.putExtra("key","The course " + getIntent().getStringExtra("courseName") + " finishes today!");
                PendingIntent endSender=PendingIntent.getBroadcast(AddCourse .this,(1290000 + courseId),endIntent,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP,endTrigger,endSender);
                Toast.makeText(AddCourse.this, "Course due date notification enabled.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.shareCourseNotes:
                  Intent sendIntent = new Intent();
                  sendIntent.setAction(Intent.ACTION_SEND);
                  sendIntent.putExtra(Intent.EXTRA_TEXT, (courseNoteTxt.getText().toString()));
                  sendIntent.putExtra(Intent.EXTRA_TITLE, "Share Notes For " + courseTitleTxt.getText().toString());
                  sendIntent.setType("text/plain");
                  Intent shareIntent = Intent.createChooser(sendIntent, null);
                  startActivity(shareIntent);
                return true;

            case R.id.refreshPage:
                Intent intent3 = new Intent(AddCourse.this, AddCourse.class);
                startActivity(intent3);
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
        return month + "/" + dayOfMonth + "/" + year;
    }

    private String endDateString (int dayOfMonth, int month, int year) {
        month = month + 1;
        return month+ "/" + dayOfMonth + "/" + year;
    }

    public void openStartDate(View view) {
        datePickerDialog.show();
    }

    public void openEndDate(View view) {
        endDatePickerDialog.show();
    }

}

