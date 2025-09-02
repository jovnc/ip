package hermione.commands;

import hermione.storage.TaskStorage;

/**
 * Represents a command to display help information in the Hermione application.
 */
public class HelpCommand extends Command {

    /**
     * Constructor for the HelpCommand class.
     *
     * @param storage  The TaskStorage instance used to manage tasks.
     * @param argument The argument string that contains the command details.
     */
    public HelpCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to display help information.
     * This method returns a string containing a list of available commands
     * and their descriptions.
     *
     * @return A string containing help information for the user.
     */
    @Override
    public String execute() {
        return """
                Available commands:
                1. help - Show this help message
                2. exit - Exit the application
                3. list - List all items
                4. todo {description} - Add a new ToDo task
                5. deadline {description} /by {date} - Add a new Deadline task
                6. event {description} /from {start date} /to {end date} - Add a new Event task
                7. fixed {description} /duration {duration} - Add a new Fixed Duration task
                7. mark {task number} - Mark a task as completed
                8. unmark {task number} - Unmark a task as not completed
                9. delete {task number} - Delete a task
                10. find {keyword} - Find tasks containing the keyword
                """;
    }
}
