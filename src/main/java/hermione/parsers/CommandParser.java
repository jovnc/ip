package hermione.parsers;

import hermione.commands.Command;
import hermione.commands.DeadlineCommand;
import hermione.commands.DeleteCommand;
import hermione.commands.EventCommand;
import hermione.commands.ExitCommand;
import hermione.commands.ListCommand;
import hermione.commands.MarkCommand;
import hermione.commands.ToDoCommand;
import hermione.commands.UnmarkCommand;
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
            case "bye" -> new ExitCommand(argument);
            default -> throw new InvalidCommandException(command);
        };
    }
}
