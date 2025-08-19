package utils;

import storage.TaskStorage;
import tasks.Task;
import tasks.TaskList;

public class UiUtils {
    public static String getAddTaskString(Task task, TaskStorage storage) {
        TaskList tasks = storage.getTasks();
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have %d tasks in the list.".formatted(tasks.getSize());
    }
}
