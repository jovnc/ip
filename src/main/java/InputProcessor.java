import java.util.ArrayList;
import java.util.List;

public class InputProcessor {
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static final String BY_FLAG = "/by";
    private static final String FROM_FLAG = "/from";
    private static final String TO_FLAG = "/to";

    private static final String INVALID_MESSAGE = "Invalid command. Please try again.";

    private final List<Task> tasks;

    public InputProcessor() {
        this.tasks = new ArrayList<>();
    }

    public String processInput(String message) {
        message = message.trim();
        String command = message.split(" ")[0];
        String argument = message.substring(command.length()).trim();

        switch (command) {
            case LIST_COMMAND:
                return handleListTasks();
            case MARK_COMMAND:
                return handleMarkTask(argument);
            case UNMARK_COMMAND:
                return handleUnmarkTask(argument);
            case TODO_COMMAND:
                return handleAddTodo(argument);
            case DEADLINE_COMMAND:
                return handleAddDeadline(argument);
            case EVENT_COMMAND:
                return handleAddEvent(argument);
            default:
                return INVALID_MESSAGE;
        }
    }

    private String getAddTaskString(Task task) {
        return "Got it. I've added this task:\n"
                + task.getTaskDescription()
                + "\nNow you have %d tasks in the list.".formatted(tasks.size());
    }

    private String handleListTasks() {
        String tasks = this.tasks.stream()
                .map(Task::toString)
                .reduce("", (acc, item) -> acc + "\n" + item);
        return "Here are the tasks in your list:" + tasks;
    }

    private String handleAddTodo(String argument) {
        Task newTask = new ToDo(argument);
        tasks.add(newTask);
        return getAddTaskString(newTask);
    }

    private String handleAddDeadline(String argument) {
        String description = argument.split(BY_FLAG)[0].trim();
        String by = argument.split(BY_FLAG)[1].trim();

        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        return getAddTaskString(newTask);
    }

    private String handleAddEvent(String argument) {
        int fromIndex = argument.indexOf(FROM_FLAG);
        int toIndex = argument.indexOf(TO_FLAG);

        String description = argument.substring(0, fromIndex).trim();
        String from = argument.substring(fromIndex + FROM_FLAG.length(), toIndex).trim();
        String to = argument.substring(toIndex + TO_FLAG.length()).trim();

        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        return getAddTaskString(newTask);
    }

    private String handleMarkTask(String argument) {
        int taskId = Integer.parseInt(argument);
        Task task = tasks.get(taskId - 1);
        task.setCompleted(true);
        return "Nice! I've marked this task as done:\n" + task.getTaskDescription();
    }

    private String handleUnmarkTask(String argument) {
        int taskId = Integer.parseInt(argument);
        Task task = tasks.get(taskId - 1);
        task.setCompleted(false);
        return "OK, I've marked this task as not done yet:\n" + task.getTaskDescription();
    }

}
