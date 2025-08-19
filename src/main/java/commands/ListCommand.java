package commands;

import storage.TaskStorage;
import tasks.TaskList;

public class ListCommand extends Command {

    public ListCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        TaskList tasks = storage.getTasks();
        return "Here are the tasks in your list:\n" + tasks.toString();
    }
}
