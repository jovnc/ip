package exceptions;

public class DateUtilsException extends RuntimeException {
    public DateUtilsException(String message) {
        super("[ERROR] Date Error: " + message);
    }
}
