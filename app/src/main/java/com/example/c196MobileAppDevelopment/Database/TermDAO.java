package com.example.c196MobileAppDevelopment.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.c196MobileAppDevelopment.Model.Terms;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Terms terms);

    @Update
    void update(Terms terms);

    @Delete
    void delete(Terms terms);

    @Query("SELECT * FROM terms ORDER BY termId ASC")
    List<Terms> getAllTerms();

    @Query("SELECT * FROM terms WHERE termId = :termId")
    Terms getTermById(Integer termId);
}
