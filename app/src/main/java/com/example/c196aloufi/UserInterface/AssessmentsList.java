package com.example.c196aloufi.UserInterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.AssessmentAdapter;
import com.example.c196aloufi.Adapters.TermAdapter;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;

import java.util.List;

public class AssessmentsList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_list);
        RecyclerView recyclerView = findViewById(R.id.assessmentsRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Assessments> assessments = appRepo.getAllAssessments();
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(assessments);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                    DialogInterface.OnClickListener assessmentDeleteClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Assessments deleteAssessment = assessmentAdapter.getAssessment(viewHolder.getAbsoluteAdapterPosition());
                                    appRepo.delete(deleteAssessment);
                                    Toast.makeText(AssessmentsList.this, "Assessment has been deleted.", Toast.LENGTH_SHORT).show();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    Assessments saveAssessment = assessmentAdapter.getAssessment(viewHolder.getAbsoluteAdapterPosition());
                                    appRepo.update(saveAssessment);
                                    assessmentAdapter.notifyDataSetChanged();
                                    Toast.makeText(AssessmentsList.this, "Assessment has not been deleted.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    AlertDialog.Builder alert = new AlertDialog.Builder(AssessmentsList.this);
                    alert.setMessage("Do you want to delete this assessment?").setPositiveButton("Yes", assessmentDeleteClickListener)
                            .setNegativeButton("No", assessmentDeleteClickListener).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void addAssessBtn(View view) {
        Intent intent = new Intent(AssessmentsList.this, AddAssessment.class);
        startActivity(intent);

    }
}
