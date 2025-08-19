package commands;

import exceptions.TaskException;
import storage.TaskStorage;
import tasks.Deadline;
import tasks.Task;
import utils.DateUtils;
import utils.UiUtils;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private static final String BY_FLAG = "/by";

    public DeadlineCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        if (!argument.contains(BY_FLAG)) {
            throw new TaskException("Deadline Task must contain '/by' flag");
        }

        String[] split = argument.split(BY_FLAG);

        if (split.length != 2) {
            throw new TaskException("Deadline Task format must be: deadline {description} /by {deadline}");
        }

        String description = argument.split(BY_FLAG)[0].trim();
        String by = argument.split(BY_FLAG)[1].trim();

        if (description.isBlank()) {
            throw new TaskException("Deadline Task description cannot be empty.");
        }

        if (by.isBlank()) {
            throw new TaskException("Deadline Task deadline cannot be empty.");
        }

        LocalDateTime parsedBy = DateUtils.parseDateString(by);

        Task newTask = new Deadline(description, false, parsedBy);
        storage.addTask(newTask);

        return UiUtils.getAddTaskString(newTask, storage);
    }
}
