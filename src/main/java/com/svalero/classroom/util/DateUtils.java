package com.svalero.classroom.util;

import java.util.Date;

public class DateUtils {

    public static String printDate(Date date) {
        Date now = new Date();
        long distance = (now.getTime() - date.getTime()) / 1000;

        if (distance < 60) {
            return "Ahora mismo";
        } else if (distance <= 60 * 60 * 5) {
            return "Hace un rato";
        } else {
            return date.toString();
        }
    }
}
