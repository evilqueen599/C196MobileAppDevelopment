package com.example.c196aloufi.UserInterface;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class AddTerm extends AppCompatActivity {

    Terms terms;

    String termTitle;

    LocalDate startDate;

    LocalDate endDate;

    AppRepo appRepo;

    EditText termTitleTxt;

    Button startDatePickerButton;

    Button endDatePickerButton;

    Button createBtn;

    DatePickerDialog datePickerDialog;

    Calendar myCal;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy") ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        appRepo = new AppRepo(getApplication());

        setUpView();

    }

    private void setUpView() {
        termTitleTxt = findViewById(R.id.termTitleTxt);
        startDatePicker();
        endDatePicker();
        addNewTerm();
    }


    private void addNewTerm() {
        createBtn = findViewById(R.id.createBtn);
        createBtn.setOnClickListener(v -> {
            termTitle = termTitleTxt.getText().toString();
            if (isNull()) {
                return;
            }
            Integer newId = appRepo.getAllTerms().get(appRepo.getAllTerms().size() - 1).getTermId() +1;
            terms = new Terms(newId, termTitle, startDate, endDate);
            appRepo.insert(terms);
            Toast.makeText(AddTerm.this, "New Term Created.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
            startActivity(intent);
        });
    }

    public boolean isNull() {
        if (termTitleTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The new term must have a name.",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (startDate == null) {
            Toast.makeText(this, "The new term must have a start date.",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (endDate == null) {
            Toast.makeText(this, "The new term must have an end date.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else return false;
    }
    private void endDatePicker() {
        endDatePickerButton = findViewById(R.id.endDatePickerButton);
        endDatePickerButton.setOnClickListener(v -> {
            myCal = Calendar.getInstance();
            int day = myCal.get(Calendar.DAY_OF_MONTH);
            int month = myCal.get(Calendar.MONTH);
            int year = myCal.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(AddTerm.this, (view, year1, month1, dayOfMonth) -> {
                endDate = LocalDate.of(year1, (month1 +1), dayOfMonth);
                endDatePickerButton.setText(dateTimeFormatter.format(endDate));
            }, year, month, day);
            datePickerDialog.show();
        });
    }
    private void startDatePicker() {
        startDatePickerButton = findViewById(R.id.startDatePickerButton);
        startDatePickerButton.setOnClickListener(v -> {
            myCal = Calendar.getInstance();
            int day = myCal.get(Calendar.DAY_OF_MONTH);
            int month = myCal.get(Calendar.MONTH);
            int year = myCal.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(AddTerm.this, (view, year1, month1, dayOfMonth) -> {
                startDate = LocalDate.of(year1, (month1 +1), dayOfMonth);
                startDatePickerButton.setText(dateTimeFormatter.format(startDate));
            }, year, month, day);
            datePickerDialog.show();
        });
    }
}
