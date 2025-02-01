package com.keepo.springsecurity.utils.date;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final int TOEKN_EXPIRY_DATE = 60 * 24;

    public static String date(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return date.format(formatter);
    }

    public static Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, TOEKN_EXPIRY_DATE);
        return new Date(calendar.getTime().getTime());
    }

    public static LocalDateTime calculateExpirationDate() {
        return LocalDateTime.now().plusMinutes(TOEKN_EXPIRY_DATE);
    }
}
