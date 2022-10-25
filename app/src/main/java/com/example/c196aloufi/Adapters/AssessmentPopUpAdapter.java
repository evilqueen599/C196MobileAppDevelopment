package com.example.c196aloufi.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.R;

import java.util.List;

public class AssessmentPopUpAdapter extends RecyclerView.Adapter<AssessmentPopUpAdapter.AddAssessmentViewHolder> {

    private List<Assessments> mAssessments;

    private SelectedAssessListener selectedAssessListener;

    public CoursePopUpAdapter(List<Assessments> assessments) {
        super();
        this.mAssessments = assessments;
    }

    public void setSelectedAssessListener(AssessmentPopUpAdapter.SelectedAssessListener selectedAssessListener) {
        this.selectedAssessListener = selectedAssessListener;
    }

    @NonNull
    @Override
    public AddAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AddAssessmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_assessment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddAssessmentViewHolder holder, int position) {
        final Assessments assessments = mAssessments.get(position);
        holder.assessTitleTxt.setText(assessments.getAssessmentTitle());
        holder.itemView.setOnClickListener(view -> {
            if (selectedAssessListener != null) {
                selectedAssessListener.onAssessmentSelected(position, assessments);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    static class AddAssessmentViewHolder extends RecyclerView.ViewHolder {
        TextView assessTitleTxt;

        public AddAssessmentViewHolder(View itemView) {
            super(itemView);
            assessTitleTxt = itemView.findViewById(R.id.assessmentTitleTxt);
        }
    }

    public interface SelectedAssessListener {
        void onAssessmentSelected(int position, Assessments assessments);
    }
}

