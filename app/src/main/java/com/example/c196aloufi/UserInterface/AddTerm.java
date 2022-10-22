package com.example.c196aloufi.UserInterface;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;

import java.util.Calendar;


public class AddTerm extends AppCompatActivity {

    Terms terms;

    Integer termId;

    String termTitle;

    String startDate;

    String endDate;

    String editTermTitle;

    String editStartDate;

    String editEndDate;

    AppRepo appRepo;

    EditText termTitleTxt;

    Button startDatePickerButton;

    Button endDatePickerButton;

    Button createBtn;

    DatePickerDialog datePickerDialog;

    DatePickerDialog endDatePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        appRepo = new AppRepo(getApplication());
        termId = getIntent().getIntExtra("termId", - 1);
        editTermTitle = getIntent().getStringExtra("termName");
        editStartDate = getIntent().getStringExtra("startDate");
        editEndDate = getIntent().getStringExtra("endDate");

        if (termId == -1) {
            setUpView();
        } else {
            termTitleTxt = findViewById(R.id.courseTitleTxt);
            termTitleTxt.setText(editTermTitle);
            startDatePickerButton = findViewById(R.id.startDatePickerButton);
            startDatePickerButton.setText(editStartDate);
            endDatePickerButton = findViewById(R.id.endDatePickerButton);
            endDatePickerButton.setText(editEndDate);
            initDatePicker();
            initEndDatePicker();
            addNewTerm();
        }
    }

    private void setUpView() {
        termTitleTxt = findViewById(R.id.courseTitleTxt);
        initDatePicker();
        startDatePickerButton = findViewById(R.id.startDatePickerButton);
        startDatePickerButton.setText(getEndDate());
        initEndDatePicker();
        endDatePickerButton = findViewById(R.id.endDatePickerButton);
        endDatePickerButton.setText(getTodaysDate());
        addNewTerm();
    }


    private void addNewTerm() {
        createBtn = findViewById(R.id.createBtn);
        createBtn.setOnClickListener(v -> {
            termTitle = termTitleTxt.getText().toString();
            if (isNull()) {
                return;
            }
            startDate = startDatePickerButton.getText().toString();
            endDate = endDatePickerButton.getText().toString();

            if (termId == -1) {
                int newTermId = appRepo.getAllTerms().get(appRepo.getAllTerms().size() -1).getTermId() +1;
                terms = new Terms(newTermId, termTitle, startDate, endDate);
                appRepo.insert(terms);
                Toast.makeText(AddTerm.this, "New Term Created.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
                startActivity(intent);
            }
            else {
                terms = new Terms(termId, termTitle,startDate, endDate);
                appRepo.update(terms);
                Toast.makeText(AddTerm.this, "Term has been updated.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
                startActivity(intent);
                     }

        });
    }

    public boolean isNull() {
        if (termTitleTxt.getText().toString().isEmpty()) {
            Toast.makeText(this, "The new term must have a name.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (getTodaysDate() == null) {
            Toast.makeText(this, "The new term must have a start date.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (getEndDate() == null) {
            Toast.makeText(this, "The new term must have an end date.", Toast.LENGTH_SHORT).show();
            return true;
        } else return false;
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        makeDateString(day, month, year);
        return makeDateString(day, month, year);

    }

    private String getEndDate()  {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        endDateString(day, month, year);
        return endDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String start = makeDateString(dayOfMonth, month, year);
            startDatePickerButton.setText(start);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            String end = endDateString(dayOfMonth, month, year);
            endDatePickerButton.setText(end);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        endDatePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private String makeDateString(int dayOfMonth, int month, int year) {
        month = month + 1;
        return getDateFormat(month) + " " + dayOfMonth + " " + year;
    }

    private String endDateString (int dayOfMonth, int month, int year) {
        month = month + 1;
        return getDateFormat(month) + " " + dayOfMonth + " " + year;
    }
    private String getDateFormat(int month) {
        if (month == 1)
            return "Jan";
        if (month == 2)
            return "Feb";
        if (month == 3)
            return "Mar";
        if (month == 4)
            return "Apr";
        if (month == 5)
            return "May";
        if (month == 6)
            return "Jun";
        if (month == 7)
            return "Jul";
        if (month == 8)
            return "Aug";
        if (month == 9)
            return "Sep";
        if (month == 10)
            return "Oct";
        if (month == 11)
            return "Nov";
        if (month ==12)
            return "Dec";
        return "Jan";
    }

    public void openStartDate(View view) {
        datePickerDialog.show();
    }

    public void openEndDate(View view) {endDatePickerDialog.show();
    }


}
