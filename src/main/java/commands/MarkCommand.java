package commands;

import exceptions.TaskException;
import storage.TaskStorage;
import tasks.Task;
import tasks.TaskList;

public class MarkCommand extends Command {
    public MarkCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        if (argument.isBlank()) {
            throw new TaskException("Task id cannot be empty");
        }
        try {
            TaskList tasks = storage.getTasks();
            int taskId = Integer.parseInt(argument);
            Task task = tasks.getTask(taskId - 1);
            storage.setTaskCompletion(task, true);

            return "Nice! I've marked this task as done:\n" + task.toString();
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Task id does not exist");
        }
    }
}
