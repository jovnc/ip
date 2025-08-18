package storage;

import tasks.Task;
import tasks.TaskList;
import utils.FileUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvTaskStorage {

    private final Path filePath;
    private static final TaskSerializer taskSerializer = new TaskSerializer();
    private TaskList tasks;

    public CsvTaskStorage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.tasks = loadTasks();
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public TaskList loadTasks() {
        List<String> lines = FileUtils.readAllLines(this.filePath);
        List<Task> tasks = lines.stream()
                .map(this::parseTaskSafely)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return new TaskList(tasks);
    }

    public void saveTasks(TaskList tasks) {
        List<String> lines = tasks.getTasks()
                                .stream()
                                .map(taskSerializer::serialize)
                                .toList();
        FileUtils.writeAllLines(this.filePath, lines);
        this.tasks = loadTasks();
    }

    public void addTask(Task task) {
        tasks.addTask(task);
        saveTasks(tasks);
    }

    public Task deleteTask(int index) {
        Task task = tasks.getTask(index);
        tasks.deleteTask(task);
        saveTasks(tasks);
        return task;
    }

    public void setTaskCompletion(Task task, boolean isComplete) {
        task.setCompleted(isComplete);
        saveTasks(tasks);
    }

    private Task parseTaskSafely(String line) {
        return taskSerializer.deserialize(line);
    }
}
