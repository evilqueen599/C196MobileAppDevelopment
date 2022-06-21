package com.example.c196aloufi.UserInterface;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.c196aloufi.R;

import java.util.Calendar;

public class AddTerm extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;

    private Button startDateButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        initDatePicker();
        startDateButton = findViewById(R.id.startDatePickerButton);
        startDateButton.setText(getTodaysDate());
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String startDate = makeDateString(dayOfMonth, month, year);
                startDateButton.setText(startDate);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
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

    public void addTermClearBtn(View view) {
    }

    public void addTermCreateBtn(View view) {
    }

    public void openStartDate(View view) {
        datePickerDialog.show();

    }
}
