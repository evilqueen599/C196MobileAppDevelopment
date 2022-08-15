package com.example.c196aloufi.UserInterface;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.R;

import java.util.Calendar;
import java.util.concurrent.Callable;

public class AddTerm extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;

    private DatePickerDialog endDatePickerDialog;

    private Button startDatePickerButton;

    private Button endDateButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        initDatePicker();
        startDatePickerButton = findViewById(R.id.startDatePickerButton);
        startDatePickerButton.setText(getTodaysDate());
        initEndDatePicker();
        endDateButton = findViewById(R.id.endDatePicker);
        endDateButton.setText(getEndDate());
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }

    private String getEndDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        endDateString(day, month, day);
        return endDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String startDate = makeDateString(dayOfMonth, month, year);
            startDatePickerButton.setText(startDate);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month = month +1;
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void initEndDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String endDate = endDateString(dayOfMonth, month, year);
            endDateButton.setText(endDate);
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month = month +1;
        int style = AlertDialog.THEME_HOLO_DARK;
        endDatePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private String makeDateString(int dayOfMonth, int month, int year) {
        return getDateFormat(month) + " " + dayOfMonth + " " + year;
    }

    private String endDateString (int dayOfMonth, int month, int year) {
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

    public void addTermBackBtn(View view) {
        Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
        startActivity(intent);
    }

    public void addTermCreateBtn(View view) {
        Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
        startActivity(intent);
    }

    public void openStartDate(View view) {
        datePickerDialog.show();
    }

    public void openEndDate(View view) {
        endDatePickerDialog.show();
    }
}
