package com.example.c196aloufi.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c196aloufi.Model.Terms;

@androidx.room.Dao
public interface TermDAO {

    @Insert
    void insert (Terms terms);

    @Update
    void update (Terms terms);

    @Delete
    void delete (Terms terms);

    @Query("DELETE * FROM terms")
    void deleteAllTerms();

    @Query("SELECT * FROM terms")
    void selectAllTerms();
}