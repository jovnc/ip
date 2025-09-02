package hermione.ui.common;

import hermione.commands.Command;
import hermione.exceptions.DateUtilsException;
import hermione.exceptions.InvalidCommandException;
import hermione.exceptions.NumberUtilsException;
import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;

/**
 * Responsible for processing user input commands in the Hermione application.
 */
public class InputProcessor {

    private final TaskStorage storage;

    /**
     * Constructs an InputProcessor with the specified TaskStorage.
     *
     * @param storage The TaskStorage instance to be used for task management.
     */
    public InputProcessor(TaskStorage storage) {
        this.storage = storage;
    }

    /**
     * Processes the user input command and returns the response message.
     *
     * @param message The user input command as a string.
     * @return The response message after processing the command.
     */
    public String process(String message) {
        String commandString = getCommandString(message);
        String argument = getArgument(message);
        return executeCommand(commandString, argument);
    }

    private String getCommandString(String message) {
        return message.trim().split(" ")[0];
    }

    private String getArgument(String message) {
        String commandWord = getCommandString(message);
        return message.substring(commandWord.length()).trim();
    }

    private String executeCommand(String commandString, String argument) {
        try {
            Command command = CommandParser.parse(commandString, argument, storage);
            return command.execute();
        } catch (DateUtilsException | InvalidCommandException | TaskValidationException | NumberUtilsException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred. Please try again.";
        }
    }
}
