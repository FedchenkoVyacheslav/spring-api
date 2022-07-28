package com.example.spring_api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        String pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}