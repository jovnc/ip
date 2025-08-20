package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.ToDo;
import hermione.utils.UiUtils;

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
