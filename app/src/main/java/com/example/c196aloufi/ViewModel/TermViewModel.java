package com.example.c196aloufi.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.c196aloufi.Database.AppRepo;
import com.example.c196aloufi.Model.Terms;

import java.util.List;

public class TermViewModel extends AndroidViewModel {

    public LiveData<List<Terms>> mTerms;
    private AppRepo mRepository;

    public TermViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepo.getInstance(application.getApplicationContext());
        mTerms = mRepository.mTerms;
        }
}
