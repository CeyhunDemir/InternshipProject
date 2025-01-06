package com.sd.stockmanagementsystem.domain.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class DateConverter {
    public static Instant convertStringToInstant(String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime.atZone(ZoneId.of("UTC")).toInstant();
    }
}
