package utils;

import exceptions.DateUtilsException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static LocalDateTime parseDateString(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new DateUtilsException("Failed to parse date: %s. Make sure to follow the format: d/M/yyyy HHmm".formatted(dateString));
        }
    }

    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return date.format(formatter);
    }

    public static LocalDateTime undoFormatDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return LocalDateTime.parse(dateString, formatter);
    }
}
