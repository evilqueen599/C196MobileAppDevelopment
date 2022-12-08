package com.example.c196MobileAppDevelopment.UserInterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196MobileAppDevelopment.Adapters.CourseAdapter;
import com.example.c196MobileAppDevelopment.Database.AppRepo;
import com.example.c196MobileAppDevelopment.Model.Assessments;
import com.example.c196MobileAppDevelopment.Model.Courses;
import com.example.c196aloufi.R;

import java.util.List;


public class CourseList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Courses> courses = appRepo.getAllCourses();
        final CourseAdapter courseAdapter = new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(courses);

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
                                    Courses deleteCourse = courseAdapter.getCourses(viewHolder.getAbsoluteAdapterPosition());
                                    for(Assessments assessments : appRepo.getAllAssessments()) {
                                        if (assessments.getCourseId() == courseAdapter.getCourses(viewHolder.getAbsoluteAdapterPosition()).getCourseId())
                                            if (courseAdapter.getCourses(viewHolder.getAbsoluteAdapterPosition()).getCourseId() == assessments.getCourseId()) {
                                                Toast.makeText(getApplicationContext(), " This course has assessments assigned to it and cannot be removed. Please remove associated assessments to proceed.", Toast.LENGTH_SHORT).show();
                                                courseAdapter.notifyDataSetChanged();
                                                return;
                                            } else {
                                                appRepo.delete(deleteCourse);
                                                courseAdapter.onAttachedToRecyclerView(recyclerView);
                                                Toast.makeText(CourseList.this, "Course has been deleted.", Toast.LENGTH_SHORT).show();
                                            }
                                    }
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    Courses saveCourse = courseAdapter.getCourses(viewHolder.getAbsoluteAdapterPosition());
                                    appRepo.update(saveCourse);
                                    courseAdapter.notifyDataSetChanged();
                                    Toast.makeText(CourseList.this, "Course has not been deleted.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    AlertDialog.Builder alert = new AlertDialog.Builder(CourseList.this);
                    alert.setMessage("Do you want to delete this course?").setPositiveButton("Yes", courseDeleteClickListener)
                            .setNegativeButton("No", courseDeleteClickListener).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void onClickAddCourse(View view) {
        Intent intent = new Intent(CourseList.this, AddCourse.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_list_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent intent = new Intent(CourseList.this, mainScreen.class);
                startActivity(intent);
                return true;

            case R.id.refreshPage:
                Intent intent1 = new Intent(CourseList.this, CourseList.class);
                startActivity(intent1);
                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
