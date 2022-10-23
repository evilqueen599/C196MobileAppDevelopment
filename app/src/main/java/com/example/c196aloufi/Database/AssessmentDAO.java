package com.example.c196aloufi.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196aloufi.Model.Assessments;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Assessments assessments);

    @Update
    void update (Assessments assessments);

    @Delete
    void delete (Assessments assessments);

    @Query("SELECT * FROM assessments ORDER BY assessmentId ASC")
    List<Assessments> getAllAssessments();

    @Query("SELECT * FROM assessments WHERE courseId = courseId")
    List<Assessments> getAssocAssesments();
}
