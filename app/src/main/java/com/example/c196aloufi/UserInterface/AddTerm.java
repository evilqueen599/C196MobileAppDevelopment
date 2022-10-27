package com.example.c196aloufi.UserInterface;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.MainScreenCourseAdapter;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.MyBroadcastReceiver;
import com.example.c196aloufi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AddTerm extends AppCompatActivity {

    Terms terms;

    Integer termId;

    String termTitle;

    String startDate;

    String endDate;

    String editTermTitle;

    String editStartDate;

    String editEndDate;

    AppRepo appRepo;

    EditText termTitleTxt;

    Button startDatePickerButton;

    Button endDatePickerButton;

    Button createBtn;

    FloatingActionButton addCoursesBtn;

    DatePickerDialog datePickerDialog;

    DatePickerDialog endDatePickerDialog;

    List<Courses> assocCourses;

    List<Courses> coursesInTerm;

    List<Courses> unassignedCourses;

    MainScreenCourseAdapter courseAdapter;

    SimpleDateFormat formatter;

    String format;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        format = "MM/dd/yyyy";
        formatter = new SimpleDateFormat(format, Locale.US);
        courseAdapter = new MainScreenCourseAdapter(this);
        appRepo = new AppRepo(getApplication());
        addCoursesBtn = findViewById(R.id.addCoursesBtn);
        termId = getIntent().getIntExtra("termId", -1);
        editTermTitle = getIntent().getStringExtra("termName");
        editStartDate = getIntent().getStringExtra("startDate");
        editEndDate = getIntent().getStringExtra("endDate");


        if (termId == -1) {
            setUpView();
        } else {
            termTitleTxt = findViewById(R.id.courseTitleTxt);
            termTitleTxt.setText(editTermTitle);
            startDatePickerButton = findViewById(R.id.startDatePickerButton);
            startDatePickerButton.setText(editStartDate);
            endDatePickerButton = findViewById(R.id.endDatePickerButton);
            endDatePickerButton.setText(editEndDate);
            initDatePicker();
            initEndDatePicker();
            addNewTerm();
            assocCourseList();
        }
    }

    private void setUpView() {
        termTitleTxt = findViewById(R.id.courseTitleTxt);
        initDatePicker();
        startDatePickerButton = findViewById(R.id.startDatePickerButton);
        startDatePickerButton.setText(getEndDate());
        initEndDatePicker();
        endDatePickerButton = findViewById(R.id.endDatePickerButton);
        endDatePickerButton.setText(getTodaysDate());
        addNewTerm();
    }

    private void assocCourseList() {
        // sets up recyclerview
        RecyclerView recyclerView = findViewById(R.id.courseViewRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assocCourses = appRepo.getAllCourses();
        coursesInTerm = new ArrayList<>();
        for (Courses courses : assocCourses) {
            if (courses.getTermId() == termId) {
                coursesInTerm.add(courses);
            }
        }
        courseAdapter.setCourses(coursesInTerm);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                    DialogInterface.OnClickListener courseDeleteClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Courses courses = courseAdapter.getCourse(viewHolder.getAbsoluteAdapterPosition());
                                    coursesInTerm.remove(courses);
                                    courseAdapter.notifyItemRemoved(which);
                                    courseAdapter.setCourses(coursesInTerm);
                                    overWriteCourse(courses, -1);
                                    Toast.makeText(AddTerm.this, "Course has been removed from this term.", Toast.LENGTH_SHORT).show();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    courseAdapter.notifyDataSetChanged();
                                    Toast.makeText(AddTerm.this, "Course has not been removed from this term.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(AddTerm.this);
                    alert.setMessage("Do you want to remove this course from this term?").setPositiveButton("Yes", courseDeleteClickListener)
                            .setNegativeButton("No", courseDeleteClickListener).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void addNewTerm() {
        createBtn = findViewById(R.id.createBtn);
        createBtn.setOnClickListener(v -> {
            termTitle = termTitleTxt.getText().toString();
            if (isNull()) {
                return;
            }
            startDate = startDatePickerButton.getText().toString();
            endDate = endDatePickerButton.getText().toString();

            if (termId == -1) {
                int newTermId = appRepo.getAllTerms().get(appRepo.getAllTerms().size() - 1).getTermId() + 1;
                terms = new Terms(newTermId, termTitle, startDate, endDate);
                appRepo.insert(terms);
                Toast.makeText(AddTerm.this, "New Term Created.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
                startActivity(intent);
            } else {
                terms = new Terms(termId, termTitle, startDate, endDate);
                appRepo.update(terms);
                Toast.makeText(AddTerm.this, "Term has been updated.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
                startActivity(intent);
            }

        });
    }

    public boolean isNull() {
        if (termTitleTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The new term must have a name.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (getTodaysDate() == null) {
            Toast.makeText(this, "The new term must have a start date.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (getEndDate() == null) {
            Toast.makeText(this, "The new term must have an end date.", Toast.LENGTH_SHORT).show();
            return true;
        } else return false;
    }

    public void addCoursesToTerm(View view) {
        unassignedCourses = new ArrayList<>();
        for (Courses courses : assocCourses) {
            if (courses.getTermId() <= -1) {
                unassignedCourses.add(courses);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Course A Course To This Term");
        builder.setMessage("Do you want to select an existing course to add to this term or create a new course for this term?");
        builder.setIcon(R.drawable.ic_round_add_task_24);
        builder.setPositiveButton("New Course", (dialog, id) -> {
            dialog.dismiss();
            Intent intent = new Intent(this, AddCourse.class);
            intent.putExtra("termId", termId);
            this.startActivity(intent);
        });
        builder.setNegativeButton("Existing Course",(dialog, id) -> {
          if (unassignedCourses.size() >= 1) {
              final CourseDropDownMenu courseMenu = new CourseDropDownMenu(this, unassignedCourses);
              courseMenu.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
              courseMenu.setWidth(getPxFromDisplay(250));
              courseMenu.setOutsideTouchable(true);
              courseMenu.setFocusable(true);
              courseMenu.showAsDropDown(addCoursesBtn);
              courseMenu.setSelectedCourseListener((position, courses) -> {
                  coursesInTerm.add(courses);
                  courseAdapter.notifyItemInserted(position);
                  courseAdapter.setCourses(coursesInTerm);
                  courseMenu.dismiss();
                  courses.setTermId(termId);
                  overWriteCourse(courses, termId);
                  Toast.makeText(getApplicationContext(), "Course has been assigned to this term.", Toast.LENGTH_SHORT).show();
              });
          }else {
              Toast.makeText(getApplicationContext(), "There are no unassigned courses. Please create a new course to add to this term.", Toast.LENGTH_SHORT).show();
          }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void overWriteCourse(Courses courses, Integer termId) {
        courses.setTermId(termId);
        appRepo.update(courses);
    }

    private int getPxFromDisplay(int dp) {
        return(int) (dp*getResources().getDisplayMetrics().density);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.term_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent intent = new Intent(AddTerm.this, mainScreen.class);
                startActivity(intent);
                return true;

            case R.id.termStartAlert:
                editStartDate = startDatePickerButton.getText().toString();
                Date mStart = null;
                try {
                    mStart = formatter.parse(editStartDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long startTrigger = mStart.getTime();
                Intent intent1=new Intent(AddTerm.this, MyBroadcastReceiver.class);
                intent1.putExtra("key","The start date of Term " + getIntent().getStringExtra("termName") + " is " + getIntent().getStringExtra("startDate") + ".");
                PendingIntent startSender=PendingIntent.getBroadcast(AddTerm .this, (1240000 + termId),intent1,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager1=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager1.set(AlarmManager.RTC_WAKEUP,startTrigger,startSender);
                Toast.makeText(AddTerm.this, "Term start date notification enabled.", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.termEndAlert:
                editEndDate = endDatePickerButton.getText().toString();
                Date mEnd = null;
                try {
                    mEnd = formatter.parse(editEndDate);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Long endTrigger = mEnd.getTime();
                Intent intent2=new Intent(AddTerm.this, MyBroadcastReceiver.class);
                intent2.putExtra("key","The term " + getIntent().getStringExtra("termName") + " finishes today!");
                PendingIntent endSender=PendingIntent.getBroadcast(AddTerm .this,(1250000 + termId),intent2,PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager2=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP,endTrigger,endSender);
                Toast.makeText(AddTerm.this, "Term end date notification enabled.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.refreshPage:
                Intent intent3 = new Intent(AddTerm.this, AddTerm.class);
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
            String start = makeDateString(dayOfMonth, month, year);
            startDatePickerButton.setText(start);
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
            String end = endDateString(dayOfMonth, month, year);
            endDatePickerButton.setText(end);
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

    public void openEndDate(View view) {endDatePickerDialog.show(); }
}
