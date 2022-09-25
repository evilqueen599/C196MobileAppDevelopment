package com.example.c196aloufi.Database;

import android.app.Application;

import com.example.c196aloufi.Model.Assessments;
import com.example.c196aloufi.Model.Courses;
import com.example.c196aloufi.Model.Terms;
import com.example.c196aloufi.Utility.TestData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRepo {
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;

    private List<Terms> mAllTerms;
    private List<Courses> mAllCourses;
    private List<Assessments> mAllAssessments;

    private AppDatabase mDb;

    private static int NUMBER_OF_THREADS=6;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public AppRepo(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
    }

    public void addSampleData() {
        databaseExecutor.execute(() -> mDb.termDAO().insertAll(TestData.getTerms()));
    }

    public void insert (Terms terms) {
        databaseExecutor.execute(()->{
            mTermDAO.insert(terms);
        });
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    }