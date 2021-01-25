package com.ystan.schedule.models.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utils for date time objects management.
 */

@Component
public class DateTimeUtils {

    /**
     * Common formatter for a project.
     */
    public static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    /**
     * Parse LocalDateTime.
     *
     * @param string - source.
     * @return - LocalDateTime.
     */
    public LocalDateTime getLocalDateTime(String string) {
        return LocalDateTime.parse(string, formatter);
    }

    public String getString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }
}
