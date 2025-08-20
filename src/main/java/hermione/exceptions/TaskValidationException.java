package hermione.exceptions;

public class TaskValidationException extends RuntimeException {
    public TaskValidationException(String message) {
        super("[ERROR] Task Error: " + message);
    }
}
