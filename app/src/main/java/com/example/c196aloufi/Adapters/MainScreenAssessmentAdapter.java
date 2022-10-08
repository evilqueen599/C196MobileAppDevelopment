package com.example.c196aloufi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.R;
import com.example.c196aloufi.UserInterface.CourseList;

import java.util.List;

public class MainScreenAssessmentAdapter extends RecyclerView.Adapter<MainScreenAssessmentAdapter.MainScreenAssessmentViewHolder> {

    class MainScreenAssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentTitleTxt;


        private MainScreenAssessmentViewHolder(View assessmentView) {
            super(assessmentView);
            assessmentTitleTxt = assessmentView.findViewById(R.id.assessmentTitleTxt);


            assessmentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAbsoluteAdapterPosition();
                    final Assessments current = massessments.get(position);
                    Intent intent = new Intent(context, CourseList.class);
                    intent.putExtra("assessmentId", current.getAssessmentId());
                    intent.putExtra("assessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("assessmentType", current.getAssessmentType());
                    intent.putExtra("endDate", current.getEndDate());
                }
            });
        }
    }

    private List<Assessments> massessments;
    private final Context context;
    private final LayoutInflater mInflator;

    public MainScreenAssessmentAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public MainScreenAssessmentAdapter.MainScreenAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View assessmentView = mInflator.inflate(R.layout.main_assessment_item,parent, false);
        return new MainScreenAssessmentAdapter.MainScreenAssessmentViewHolder(assessmentView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainScreenAssessmentAdapter.MainScreenAssessmentViewHolder holder, int position) {
        if (massessments != null) {
            Assessments current = massessments.get(position);
            String name = current.getAssessmentTitle();
            holder.assessmentTitleTxt.setText(name);
        } else {
            holder.assessmentTitleTxt.setText("No Assessments Exist");
        }
    }
    public void setAssessments(List<Assessments> assessments) {
        massessments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (massessments != null) {
            return massessments.size();
        } else return 0;
    }
}
