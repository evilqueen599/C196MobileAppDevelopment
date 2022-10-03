package com.example.c196aloufi.Database;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Converters {

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    @TypeConverter
    public static LocalDate fromDateString(String string) {
        return LocalDate.parse(string);
    }

    @TypeConverter
    public static String fromLocalDate(LocalDate date) {
        return dateTimeFormatter.format(date);
    }
}
