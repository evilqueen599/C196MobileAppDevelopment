package com.example.c196aloufi.UserInterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196aloufi.Adapters.TermAdapter;
import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.R;


import java.util.List;


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
                                    for(Courses course : appRepo.getAllCourses()) {
                                        if (course.getTermId() == termAdapter.getTerm(viewHolder.getAbsoluteAdapterPosition()).getTermId())
                                            if (termAdapter.getTerm(viewHolder.getAbsoluteAdapterPosition()).getTermId() == course.getTermId()) {
                                                Toast.makeText(getApplicationContext(), " This Term has courses assigned to it and cannot be removed. Please remove associated courses to proceed.", Toast.LENGTH_SHORT).show();
                                                termAdapter.notifyDataSetChanged();
                                                return;
                                            } else {
                                                appRepo.delete(deleteTerm);
                                                termAdapter.onAttachedToRecyclerView(recyclerView);
                                                Toast.makeText(DetailedTerm.this, "Term has been deleted.", Toast.LENGTH_SHORT).show();
                                            }
                                    }
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detailed_term_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.homeScreen:
                Intent intent = new Intent(DetailedTerm.this, mainScreen.class);
                startActivity(intent);
                return true;

            case R.id.refreshPage:
                Intent intent1 = new Intent(DetailedTerm.this, DetailedTerm.class);
                startActivity(intent1);
                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
