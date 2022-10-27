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
import com.example.c196aloufi.UserInterface.AddCourse;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    public Courses getCourses(int absoluteAdapterPosition) {
        return mcourses.get(absoluteAdapterPosition);
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseName;
        private final TextView courseDateTxt;
        private final TextView endDateTxt;
        private final TextView courseStatusTxt;
        private final TextView instructorNameTxt;
        private final TextView instructorPhoneTxt;
        private final TextView instructorEmailTxt;
        private final TextView courseNoteTxt;


        public CourseViewHolder(View courseView) {
            super(courseView);
            courseName = courseView.findViewById(R.id.courseTitleTxt);
            courseDateTxt = courseView.findViewById(R.id.courseDateTxt);
            endDateTxt = courseView.findViewById(R.id.endDateTxt);
            courseStatusTxt = courseView.findViewById(R.id.courseStatusTxt);
            instructorNameTxt = courseView.findViewById(R.id.instructorNameTxt);
            instructorPhoneTxt = courseView.findViewById(R.id.instructorPhoneTxt);
            instructorEmailTxt = courseView.findViewById(R.id.instructorEmailAddressTxt);
            courseNoteTxt = courseView.findViewById(R.id.courseNoteTxt);

            courseView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    final Courses current = mcourses.get(position);
                    Intent intent = new Intent(context, AddCourse.class);
                    intent.putExtra("courseId", current.getCourseId());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("courseStatus",current.getCourseStatus());
                    intent.putExtra("instructorName",current.getInstructorName());
                    intent.putExtra("instructorPhone", current.getInstructorPhone());
                    intent.putExtra("instructorEmail",current.getInstructorEmail());
                    intent.putExtra("courseNote", current.getCourseNote());
                    intent.putExtra("termId", current.getTermId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Courses> mcourses;
    private final Context context;
    private final LayoutInflater mInflator;

    public CourseAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View courseView = mInflator.inflate(R.layout.course_item,parent, false);
        return new CourseAdapter.CourseViewHolder(courseView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mcourses != null) {
            Courses current = mcourses.get(position);
            String name = current.getCourseName();
            holder.courseName.setText(name);
            String startDate = current.getStartDate();
            String endDate = current.getEndDate();
            holder.courseDateTxt.setText("Start Date: " + startDate);
            holder.endDateTxt.setText("End Date: " + endDate);
            String status = current.getCourseStatus();
            holder.courseStatusTxt.setText(status);
            String instructorName = current.getInstructorName();
            holder.instructorNameTxt.setText(instructorName);
            String instructorPhone = current.getInstructorPhone();
            holder.instructorPhoneTxt.setText(instructorPhone);
            String instructorEmail = current.getInstructorEmail();
            holder.instructorEmailTxt.setText(instructorEmail);

        } else {
            holder.courseName.setText("No Courses Exist");
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
