package com.example.c196aloufi.Database;

import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TypeConverter {
    @androidx.room.TypeConverter
    public static Date toDate(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @androidx.room.TypeConverter
    public static Long toTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @androidx.room.TypeConverter
    public static Date toDate(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDateString(String dateToFormat) {
        String stringDateFormat = null;
        try {
            Date formattedDate = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy").parse(dateToFormat);

            Calendar cal = Calendar.getInstance();
            cal.setTime(formattedDate);
            stringDateFormat = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" +
                    cal.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return stringDateFormat;
        }
    }

    public static void updateDateText(EditText date, Calendar calendar) {
        String dateFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());

        date.setText(sdf.format(calendar.getTime()));
    }
    public static Date calDtToDate(Date calDate){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = dateFormat.format(calDate);

        Date formattedDate = toDate(strDate);

        return formattedDate;
    }
}