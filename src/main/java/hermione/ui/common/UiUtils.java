package hermione.ui.common;

import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.TaskList;

/**
 * Utility class for UI-related functionalities in the Hermione application.
 */
public class UiUtils {

    /**
     * Returns a string to indicate that a task has been added to the task list.
     * This string includes the task details and the total number of tasks in the list.
     *
     * @param task    The task that has been added.
     * @param storage The storage containing the task list.
     * @return Formatted string indicating the task has been added and the current number of tasks in the list.
     */
    public static String getAddTaskString(Task task, TaskStorage storage) {
        TaskList tasks = storage.getTasks();
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have %d tasks in the list.".formatted(tasks.getSize());
    }
}
