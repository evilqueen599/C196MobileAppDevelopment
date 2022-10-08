package com.example.c196aloufi.UserInterface;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.TermAdapter;
import com.example.c196aloufi.Adapters.TextFormatter;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Database.TermDAO;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class AddTerm extends AppCompatActivity {

    Terms terms;

    Integer termId;

    String termTitle;

    String startDate;

    String endDate;

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

        setUpView();



    }

    private void setUpView() {
        termTitleTxt = findViewById(R.id.termTitleTxt);
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

            terms = new Terms(termId, termTitle, startDate, endDate);
            appRepo.insert(terms);
            Toast.makeText(AddTerm.this, "New Term Created.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
            startActivity(intent);
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
