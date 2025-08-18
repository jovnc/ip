package exceptions;

public class DateUtilsException extends RuntimeException {
    public DateUtilsException(String message) {
        super("[DATE UTILS ERROR] " + message);
    }
}
