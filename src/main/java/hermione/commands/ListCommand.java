package hermione.commands;

import hermione.storage.TaskStorage;
import hermione.tasks.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to list all tasks.
     * This method retrieves the list of tasks from storage and formats them for display.
     *
     * @return A string containing the formatted list of tasks.
     */
    @Override
    public String execute() {
        TaskList tasks = storage.getTasks();
        return "Here are the tasks in your list:\n" + tasks.toString();
    }
}
