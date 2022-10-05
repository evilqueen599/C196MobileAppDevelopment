package com.example.c196aloufi.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import java.util.concurrent.ExecutionException;

public class DetailedTerm extends AppCompatActivity {

    FloatingActionButton deleteTerm;
    AppRepo appRepo;
    Terms terms;
    int termId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term_list);
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        AppRepo appRepo = new AppRepo(getApplication());
        List<Terms> terms = appRepo.getAllTerms();
        final TermAdapter termAdapter = new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(terms);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                    Terms deleteTerm = termAdapter.getTerm(viewHolder.getAbsoluteAdapterPosition());
                    appRepo.delete(deleteTerm);
                    Toast.makeText(DetailedTerm.this, "Term has been deleted.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void onClickAddTerm(View view) {
        Intent intent = new Intent(DetailedTerm.this, AddTerm.class);
        startActivity(intent);
    }

    public void onClickEditTerm(View view) {
        Intent intent = new Intent(DetailedTerm.this, AddTerm.class);
        startActivity(intent);
    }

}
