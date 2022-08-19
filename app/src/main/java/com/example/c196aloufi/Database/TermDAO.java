package com.example.c196aloufi.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.c196aloufi.Model.Terms;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Terms term);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Terms> terms);

    @Delete
    void deleteTerm(Terms term);

    @Query("SELECT * FROM terms WHERE id = :id")
    Terms getTermById(int id);

    @Query("SELECT * FROM terms ORDER BY startDate DESC")
    LiveData<List<Terms>> getAll();

    @Query("DELETE FROM terms")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM terms")
    int getCount();
}
