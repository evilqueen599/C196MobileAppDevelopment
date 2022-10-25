package com.example.c196aloufi.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.R;
import com.example.c196aloufi.UserInterface.AddCourse;

import java.util.List;

public class CoursePopUpAdapter extends RecyclerView.Adapter<CoursePopUpAdapter.AddCourseViewHolder> {

    private List<Courses> mCourses;

    private SelectedCourseListener selectedCourseListener;

    public CoursePopUpAdapter(List<Courses> courses) {
        super();
        this.mCourses = courses;
    }

    public void setSelectedCourseListener (CoursePopUpAdapter.SelectedCourseListener selectedCourseListener) {
        this.selectedCourseListener = selectedCourseListener;
    }

    @NonNull
    @Override

    public AddCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddCourseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddCourseViewHolder holder, int position) {
        final Courses courses = mCourses.get(position);
        holder.courseTitleTxt.setText(courses.getCourseName());
        holder.itemView.setOnClickListener (view -> {
            if (selectedCourseListener != null) {
                selectedCourseListener.onCourseSelected(position, courses);
            }

        });
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    static class AddCourseViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitleTxt;

        public AddCourseViewHolder(View itemView) {
            super(itemView);
            courseTitleTxt = itemView.findViewById(R.id.courseTitleTxt);
        }
    }

    public interface SelectedCourseListener {
        void onCourseSelected(int position, Courses courses);
    }
}
