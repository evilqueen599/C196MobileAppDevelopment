package com.example.c196aloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.R;
import com.example.c196aloufi.UserInterface.CourseList;

import java.util.List;

public class MainScreenCourseAdapter extends RecyclerView.Adapter<MainScreenCourseAdapter.MainCourseViewHolder> {
    class MainCourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitleTxt;


        private MainCourseViewHolder(View courseView) {
            super(courseView);
            courseTitleTxt = courseView.findViewById(R.id.courseTitleTxt);

            courseView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Courses current = mcourses.get(position);
                    Intent intent = new Intent(context, CourseList.class);
                    intent.putExtra("courseId", current.getCourseId());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("courseStatus",current.getCourseStatus());
                    intent.putExtra("instructorName",current.getInstructorName());
                    intent.putExtra("instructorPhone", current.getInstructorPhone());
                    intent.putExtra("instructorEmail",current.getInstructorEmail());
                }
            });
        }
    }

    private List<Courses> mcourses;
    private final Context context;
    private final LayoutInflater mInflator;

    public MainScreenCourseAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public MainScreenCourseAdapter.MainCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseView = mInflator.inflate(R.layout.main_course_item,parent, false);
        return new MainScreenCourseAdapter.MainCourseViewHolder(courseView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainScreenCourseAdapter.MainCourseViewHolder holder, int position) {
        if (mcourses != null) {
            Courses current = mcourses.get(position);
            String name = current.getCourseName();
            holder.courseTitleTxt.setText(name);
        } else {
            holder.courseTitleTxt.setText("No Courses Exist");
        }
    }
    public void setCourses(List<Courses> courses) {
        mcourses = courses;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mcourses != null) {
            return mcourses.size();
        } else return 0;
    }
}
