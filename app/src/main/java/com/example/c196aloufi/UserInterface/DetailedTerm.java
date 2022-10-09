package com.example.c196aloufi.UserInterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DetailedTerm extends AppCompatActivity  {

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
                    DialogInterface.OnClickListener termDeleteClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Terms deleteTerm = termAdapter.getTerm(viewHolder.getAbsoluteAdapterPosition());
                                    appRepo.delete(deleteTerm);
                                    Toast.makeText(DetailedTerm.this, "Term has been deleted.", Toast.LENGTH_SHORT).show();
                                    break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        Terms saveTerm = termAdapter.getTerm(viewHolder.getAbsoluteAdapterPosition());
                                        appRepo.update(saveTerm);
                                        termAdapter.notifyDataSetChanged();
                                        Toast.makeText(DetailedTerm.this, "Term has not been deleted.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    AlertDialog.Builder alert = new AlertDialog.Builder(DetailedTerm.this);
                    alert.setMessage("Do you want to delete this term?").setPositiveButton("Yes", termDeleteClickListener)
                            .setNegativeButton("No", termDeleteClickListener).show();

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
}
