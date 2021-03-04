package com.codeup.spring.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Time {

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
    return LocalDateTime.ofInstant(
      dateToConvert.toInstant(), ZoneId.systemDefault());
    }

    public static String formatDate(LocalDateTime localDateTime){
        String time = String.valueOf(localDateTime);
        String newTime = time.substring(5,7) + "/" + time.substring(8, 10) +
                "/" + time.substring(0,4) + " " + time.substring(11, 16);
        return newTime;
    }

}
