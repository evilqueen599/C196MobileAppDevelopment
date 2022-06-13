package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196aloufi.R;

public class AddTerm extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
    }

    public void addTermBackBtn(View view) {
        Intent intent = new Intent(AddTerm.this, DetailedTerm.class);
        startActivity(intent);
    }

    public void addTermClearBtn(View view) {
    }

    public void addTermCreateBtn(View view) {
    }
}
