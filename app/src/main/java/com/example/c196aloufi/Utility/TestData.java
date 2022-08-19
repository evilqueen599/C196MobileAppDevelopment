package com.example.c196aloufi.Utility;

import com.example.c196aloufi.Model.Terms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TestData {

    private static final String SAMPLE_TITLE = "Test Term";

    public static List<Terms> getTerms() {
        List<Terms> terms = new ArrayList<>();
        terms.add(new Terms(40000, SAMPLE_TITLE + " 1", newDate(0), (newDate(10))));
        terms.add(new Terms(40001, SAMPLE_TITLE + " 2", newDate(-100), (newDate(10))));
        terms.add(new Terms(40002, SAMPLE_TITLE + " 3", newDate(-1000), (newDate(10))));
        return terms;
    }
    private static Date newDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }



}
