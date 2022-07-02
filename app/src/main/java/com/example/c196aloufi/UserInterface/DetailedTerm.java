package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.c196aloufi.R;

public class DetailedTerm extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term_list);
    }
    public void onClickAddTerm(View view) {
        Intent intent = new Intent(DetailedTerm.this, AddTerm.class);
        startActivity(intent);
    }

    public void backBtn(View view) {
        Intent intent = new Intent(DetailedTerm.this, TermList.class);
        startActivity(intent);
    }
}
