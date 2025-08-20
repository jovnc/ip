package storage;

import tasks.Task;
import tasks.TaskList;

public interface TaskStorage {
    TaskList getTasks();
    void addTask(Task task);
    void setTaskCompletion(Task task, boolean isComplete);
    Task deleteTask(int index);
}
