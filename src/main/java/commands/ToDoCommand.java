package commands;

import exceptions.TaskValidationException;
import storage.TaskStorage;
import tasks.Task;
import tasks.ToDo;
import utils.UiUtils;

public class ToDoCommand extends Command{
    public ToDoCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        if (argument.isBlank()) {
            throw new TaskValidationException("Todo Task format must be: todo {description}");
        }

        Task newTask = new ToDo(argument, false);
        storage.addTask(newTask);

        return UiUtils.getAddTaskString(newTask, storage);
    }
}
