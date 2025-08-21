package hermione.storage;

import hermione.tasks.Task;
import hermione.tasks.TaskList;
import hermione.utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvTaskStorage implements TaskStorage {

    private final Path filePath;
    private static final TaskSerializer taskSerializer = new TaskSerializer();
    private TaskList tasks;

    /**
     * Constructor for CsvTaskStorage.
     * Initializes the storage with the specified file path and loads existing tasks.
     *
     * @param filePath The path to the CSV file where tasks are stored.
     */
    public CsvTaskStorage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.tasks = loadTasks();
    }

    /**
     * Returns the current list of tasks.
     * This method retrieves the tasks stored in this storage instance.
     *
     * @return TaskList containing all tasks.
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Loads tasks from the CSV file.
     * This method reads all lines from the file, parses them into Task objects,
     * and returns a TaskList containing these tasks.
     *
     * @return TaskList containing all tasks loaded from the file.
     */
    public TaskList loadTasks() {
        List<String> lines = FileUtils.readAllLines(this.filePath);
        List<Task> tasks = lines.stream()
                .map(this::parseTaskSafely)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new TaskList(tasks);
    }

    /**
     * Saves the current list of tasks to the CSV file.
     * This method serializes each task in the TaskList and writes them to the file.
     * If the file does not exist, it will be created.
     *
     * @param tasks The TaskList containing tasks to be saved.
     */
    public void saveTasks(TaskList tasks) {
        List<String> lines = tasks.getTasks()
                                .stream()
                                .map(taskSerializer::serialize)
                                .toList();
        FileUtils.writeAllLines(this.filePath, lines);
        this.tasks = loadTasks();
    }

    /**
     * Adds a new task to the storage.
     * This method appends the task to the current TaskList and saves the updated list to the file.
     *
     * @param task The Task object to be added to the storage.
     */
    public void addTask(Task task) {
        tasks.addTask(task);
        saveTasks(tasks);
    }

    /**
     * Deletes a task from the storage.
     * This method removes the task at the specified index from the TaskList and saves the updated
     * list to the file.
     *
     * @param index The index of the task to be deleted.
     * @return The Task object that was deleted.
     */
    public Task deleteTask(int index) {
        Task task = tasks.getTask(index);
        tasks.deleteTask(task);
        saveTasks(tasks);
        return task;
    }

    /**
     * Sets the completion status of a task.
     * This method updates the completion status of the specified task and saves the updated
     * TaskList to the file.
     *
     * @param task The Task object whose completion status is to be updated.
     * @param isComplete The new completion status of the task (true for complete, false for incomplete).
     */
    public void setTaskCompletion(Task task, boolean isComplete) {
        task.setCompleted(isComplete);
        saveTasks(tasks);
    }

    private Task parseTaskSafely(String line) {
        return taskSerializer.deserialize(line);
    }
}
