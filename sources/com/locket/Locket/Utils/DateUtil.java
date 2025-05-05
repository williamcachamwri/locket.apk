package com.locket.Locket.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static int getGregorianYYYYMMDD(LocalDate localDate) {
        return Integer.parseInt(localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
