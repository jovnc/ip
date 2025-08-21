package hermione.parsers;

import hermione.commands.*;
import hermione.exceptions.InvalidCommandException;
import hermione.storage.TaskStorage;

public class CommandParser {
    public static Command parse(String command, String argument, TaskStorage storage) {
        return switch (command) {
            case "deadline" -> new DeadlineCommand(storage, argument);
            case "todo" -> new ToDoCommand(storage, argument);
            case "event" -> new EventCommand(storage, argument);
            case "delete" -> new DeleteCommand(storage, argument);
            case "mark" -> new MarkCommand(storage, argument);
            case "unmark" -> new UnmarkCommand(storage, argument);
            case "list" -> new ListCommand(storage, argument);
            case "find" -> new FindCommand(storage, argument);
            case "bye" -> new ExitCommand(argument);
            default -> throw new InvalidCommandException(command);
        };
    }
}
