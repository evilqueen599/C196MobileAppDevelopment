package com.example.c196aloufi.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRepo appRepo = new AppRepo(getApplication());
        Terms terms = new Terms(1, "Application Development" , getDate(0), getDate(10));
        appRepo.insert(terms);
    }

        private static Date getDate(int diff) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.add(Calendar.MILLISECOND, diff);
            return cal.getTime();
        }

    public void enterButton(View view) {
        Intent intent = new Intent(MainActivity.this, mainScreen.class);
        startActivity(intent);
    }
}