package org.example.customerstores.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class utils {

    public static long calculateDifferenceInDays(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date2, date1);
    }

}
