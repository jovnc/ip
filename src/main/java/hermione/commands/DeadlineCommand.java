package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Deadline;
import hermione.tasks.Task;
import hermione.utils.DateUtils;
import hermione.utils.UiUtils;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private static final String BY_FLAG = "/by";

    public DeadlineCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        if (!argument.contains(BY_FLAG)) {
            throw new TaskValidationException("Deadline Task must contain '/by' flag");
        }

        String[] split = argument.split(BY_FLAG);

        if (split.length != 2) {
            throw new TaskValidationException("Deadline Task format must be: deadline {description} /by {deadline}");
        }

        String description = argument.split(BY_FLAG)[0].trim();
        String by = argument.split(BY_FLAG)[1].trim();

        if (description.isBlank()) {
            throw new TaskValidationException("Deadline Task description cannot be empty.");
        }

        if (by.isBlank()) {
            throw new TaskValidationException("Deadline Task deadline cannot be empty.");
        }

        LocalDateTime parsedBy = DateUtils.parseDateString(by);

        Task newTask = new Deadline(description, false, parsedBy);
        storage.addTask(newTask);

        return UiUtils.getAddTaskString(newTask, storage);
    }
}
