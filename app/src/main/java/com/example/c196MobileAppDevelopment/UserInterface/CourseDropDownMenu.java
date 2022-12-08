package com.example.c196MobileAppDevelopment.UserInterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196MobileAppDevelopment.Adapters.CoursePopUpAdapter;
import com.example.c196MobileAppDevelopment.Model.Courses;
import com.example.c196aloufi.R;

import java.util.List;

public class CourseDropDownMenu extends PopupWindow {
    private Context context;
    private List<Courses> courses;
    private RecyclerView coursePopup;
    private CoursePopUpAdapter coursePopUpAdapter;

    public CourseDropDownMenu(Context context, List<Courses> courses) {
        super(context);
        this.context = context;
        this.courses = courses;
        setupView();
    }

    public void setSelectedCourseListener (CoursePopUpAdapter.SelectedCourseListener selectedCourseListener) {
        coursePopUpAdapter.setSelectedCourseListener(selectedCourseListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_menu_item, null);
        coursePopup = view.findViewById(R.id.popUp);
        coursePopup.setHasFixedSize(true);
        coursePopup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        coursePopup.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        coursePopUpAdapter = new CoursePopUpAdapter(courses);
        coursePopup.setAdapter(coursePopUpAdapter);
        setContentView(view);
    }
}
