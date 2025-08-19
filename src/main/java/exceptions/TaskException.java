package exceptions;

public class TaskException extends RuntimeException {
    public TaskException(String message) {
        super("[ERROR] " + message);
    }
}
