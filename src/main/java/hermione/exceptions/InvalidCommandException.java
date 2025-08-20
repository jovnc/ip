package hermione.exceptions;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super("[ERROR] Invalid command: " + message);
    }
}
