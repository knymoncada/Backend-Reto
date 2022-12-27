package com.backend.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class Util {

    // ISO 8601 BASIC is used by the API signature
    private static String ISO_8601BASIC_DATE_PATTERN = "yyyyMMdd'T'HHmmss'Z'";

    public static boolean isIsoTimestamp2(String s) {

        return s.matches("\\d{8}T\\d{6}Z");
    }
    
    public static String applyFormatIsoDateTime(String s){
        SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_8601BASIC_DATE_PATTERN);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
