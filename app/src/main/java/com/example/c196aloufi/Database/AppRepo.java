package com.example.c196aloufi.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.c196aloufi.Model.Terms;

import com.example.c196aloufi.Database.TermDAO;
import com.example.c196aloufi.Utility.TestData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepo {

    private static AppRepo ourInstance;

    // LiveData lists of the models to observe

    public LiveData<List<Terms>> mTerms;

    private AppDatabase mDb;

    // To execute database queries off the main thread

    private Executor executor = Executors.newSingleThreadExecutor();


    public static AppRepo getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepo(context);
        }
        return ourInstance;
    }

    private AppRepo(Context context) {
        mDb = AppDatabase.getInstance(context);
        mTerms = getAllTerms();

    }

    /**
     * Adds sample data to the app to make it easier to troubleshoot and visualize
     */
    public void addSampleData() {
        executor.execute(() -> mDb.termDao().insertAll(TestData.getTerms()));
    }

    public LiveData<List<Terms>> getAllTerms() {
        return mDb.termDao().getAll();
    }

    /**
     * Deletes all database data so you can start fresh
     */
    public void deleteAllData() {
        executor.execute(() -> mDb.termDao().deleteAll());

    }

    /**
     * Gets term by unique term ID
     *
     * @param termId ID in database for term
     * @return Term found in database
     */
    public Terms getTermById(int termId) {
        return mDb.termDao().getTermById(termId);
    }

    /**
     * Inserts (and overwrites) term in db
     *
     * @param term Term to insert.
     */
    public void insertTerm(final Terms term) {
        executor.execute(() -> mDb.termDao().insertTerm(term));
    }

    public void deleteTerm(final Terms term) {
        executor.execute(() -> mDb.termDao().deleteTerm(term));
    }
}