package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.TermAdapter;
import com.example.c196aloufi.Database.AppDatabase;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DetailedTerm extends AppCompatActivity {

    FloatingActionButton termDelete;
    AppRepo appRepo;
    Terms currentTerm;
    int termId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term_list);
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Terms> terms = appRepo.getAllTerms();
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);
        CardView cardView = findViewById(R.id.termsCardView);
        ViewGroup.LayoutParams params = cardView.getLayoutParams();
        params.height = params.width;
        cardView.setLayoutParams(params);

    }
    public void onClickAddTerm(View view) {
        Intent intent = new Intent(DetailedTerm.this, AddTerm.class);
        startActivity(intent);
    }

    public void onClickEditTerm(View view) {
        Intent intent = new Intent(DetailedTerm.this, AddTerm.class);
        startActivity(intent);
    }

    public void onClickDeleteTerm(View view){

    }

}
