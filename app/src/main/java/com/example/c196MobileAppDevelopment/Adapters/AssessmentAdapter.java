package com.example.c196MobileAppDevelopment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196MobileAppDevelopment.Model.Assessments;
import com.example.c196MobileAppDevelopment.UserInterface.AddAssessment;
import com.example.c196aloufi.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    public Assessments getAssessment(int absoluteAdapterPosition) {
        return massessments.get(absoluteAdapterPosition);
    }

    public void setAssessments(List<Assessments> assessments) {
        massessments = assessments;
        notifyDataSetChanged();
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentTitleTxt;
        private final TextView assessmentTypeTxt;
        private final TextView dueDateTxt;
        private final TextView startDateTxt;

        public AssessmentViewHolder(View courseView) {
            super(courseView);
            assessmentTitleTxt = courseView.findViewById(R.id.assessmentTitleTxt);
            assessmentTypeTxt = courseView.findViewById(R.id.assessmentTypeTxt);
            startDateTxt = courseView.findViewById(R.id.startTxt);
            dueDateTxt = courseView.findViewById(R.id.dueDateTxt);


            courseView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    final Assessments current = massessments.get(position);
                    Intent intent = new Intent(context, AddAssessment.class);
                    intent.putExtra("assessmentId", current.getAssessmentId());
                    intent.putExtra("assessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("assessmentType", current.getAssessmentType());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    intent.putExtra("courseId", current.getCourseId());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Assessments> massessments;
    private final Context context;
    private final LayoutInflater mInflator;

    public AssessmentAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View assessmentView = mInflator.inflate(R.layout.assessment_item,parent, false);
        return new AssessmentViewHolder(assessmentView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (massessments != null) {
            Assessments current = massessments.get(position);
            String name = current.getAssessmentTitle();
            holder.assessmentTitleTxt.setText(name);
            String type =current.getAssessmentType();
            holder.assessmentTypeTxt.setText(type);
            String start = current.getStartDate();
            holder.startDateTxt.setText("Start Date: " +start);
            String dueDate = current.getEndDate();
            holder.dueDateTxt.setText("Due Date: " + dueDate);
        } else {
            holder.assessmentTitleTxt.setText("No Assessments Exist");
        }
    }

    @Override
    public int getItemCount() {
        if (massessments != null) {
            return massessments.size();
        } else return 0;
    }
}
