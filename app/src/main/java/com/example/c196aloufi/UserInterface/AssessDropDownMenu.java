package com.example.c196aloufi.UserInterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.AssessmentPopUpAdapter;
import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.R;

import java.util.List;

public class AssessDropDownMenu extends PopupWindow {
    private Context context;
    private List<Assessments> assessments;
    private RecyclerView assessPopUp;
    private AssessmentPopUpAdapter assessmentPopUpAdapter;

    public AssessDropDownMenu(Context context, List<Assessments> assessments) {
        super(context);
        this.context = context;
        this.assessments = assessments;
        setupView();
    }

    public void setSelectedAssessListener (AssessmentPopUpAdapter.SelectedAssessListener selectedAssessListener) {
        assessmentPopUpAdapter.setSelectedAssessListener(selectedAssessListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_menu_item, null);
        assessPopUp = view.findViewById(R.id.popUp);
        assessPopUp.setHasFixedSize(true);
        assessPopUp.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        assessPopUp.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        assessmentPopUpAdapter = new AssessmentPopUpAdapter(assessments);
        assessPopUp.setAdapter(assessmentPopUpAdapter);
        setContentView(view);
    }
}
