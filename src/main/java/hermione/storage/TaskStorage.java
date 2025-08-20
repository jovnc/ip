package hermione.storage;

import hermione.tasks.Task;
import hermione.tasks.TaskList;

public interface TaskStorage {
    TaskList getTasks();
    void addTask(Task task);
    void setTaskCompletion(Task task, boolean isComplete);
    Task deleteTask(int index);
}
