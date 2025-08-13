import java.util.ArrayList;
import java.util.List;

public class CommandProcessor {
    private static final String LIST_COMMAND = "list";
    private final List<Task> tasks;

    public CommandProcessor() {
        this.tasks = new ArrayList<>();
    }

    public String executeCommand(String command) {
        command = command.trim();

        if (command.equalsIgnoreCase(LIST_COMMAND)) return handleListTasks();
        if (command.startsWith("mark")) return handleMarkTask(command);
        if (command.startsWith("unmark")) return handleUnmarkTask(command);

        return handleAddTask(command);
    }

    private String handleListTasks() {
        String tasks = this.tasks.stream()
                .map(Task::toString)
                .reduce("", (acc, item) -> acc + "\n" + item);
        return "Here are the tasks in your list:" + tasks;
    }

    private String handleAddTask(String item) {
        Task newTask = new Task(item);
        tasks.add(newTask);
        return "added: %s".formatted(item);
    }

    private String handleMarkTask(String item) {
        int taskId = Integer.parseInt(item.split(" ")[1]);
        Task task = tasks.get(taskId - 1);
        task.setCompleted(true);
        return "Nice! I've marked this task as done:\n" + task;
    }

    private String handleUnmarkTask(String item) {
        int taskId = Integer.parseInt(item.split(" ")[1]);
        Task task = tasks.get(taskId - 1);
        task.setCompleted(false);
        return "OK, I've marked this task as not done yet:\n" + task;
    }

}
