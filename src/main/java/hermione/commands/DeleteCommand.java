package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to delete a task by its ID.
     * Validates the input, checks if the task ID is valid,
     * and removes the task from the storage.
     *
     * @return A confirmation message indicating the task has been removed and the updated task count.
     * @throws TaskValidationException If the task ID is invalid or does not exist.
     */
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
