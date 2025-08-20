package commands;

import exceptions.TaskValidationException;
import storage.TaskStorage;
import tasks.Task;
import tasks.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        if (argument.isBlank()) {
            throw new TaskValidationException("Task id cannot be empty");
        }
        try {
            int taskId = Integer.parseInt(argument);
            Task removedTask = storage.deleteTask(taskId - 1);

            TaskList tasks = storage.getTasks();
            return "Noted. I've removed this task:\n"
                    + removedTask.toString()
                    + "\nNow you have %d tasks in the list.".formatted(tasks.getSize());
        } catch (NumberFormatException e) {
            throw new TaskValidationException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskValidationException("Task id does not exist");
        }
    }
}
