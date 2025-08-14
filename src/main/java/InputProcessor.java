import exceptions.TaskException;

import java.util.ArrayList;
import java.util.List;

public class InputProcessor {
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

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

        try {
            return switch (command) {
                case LIST_COMMAND -> handleListTasks();
                case MARK_COMMAND -> handleMarkTask(argument);
                case UNMARK_COMMAND -> handleUnmarkTask(argument);
                case TODO_COMMAND -> handleAddTodo(argument);
                case DEADLINE_COMMAND -> handleAddDeadline(argument);
                case EVENT_COMMAND -> handleAddEvent(argument);
                case DELETE_COMMAND -> handleDeleteTask(argument);
                default -> INVALID_MESSAGE;
            };
        } catch (TaskException e) {
            return e.getMessage();
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

    private String handleAddTodo(String argument) throws TaskException{
        if (argument.isBlank()) {
            throw new TaskException("Todo Task format must be: todo {description}");
        }
        Task newTask = new ToDo(argument);
        tasks.add(newTask);
        return getAddTaskString(newTask);
    }

    private String handleAddDeadline(String argument) throws TaskException{

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

        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        return getAddTaskString(newTask);
    }

    private String handleAddEvent(String argument) throws TaskException{
        int fromIndex = argument.indexOf(FROM_FLAG);
        int toIndex = argument.indexOf(TO_FLAG);

        if (fromIndex == -1 || toIndex == -1) {
            throw new TaskException("Event Task must contain '/from' and '/to' flags.");
        }

        if (fromIndex >= toIndex) {
            throw new TaskException("Event Task '/from' flag must come before '/to' flag");
        }

        String description = argument.substring(0, fromIndex).trim();
        String from = argument.substring(fromIndex + FROM_FLAG.length(), toIndex).trim();
        String to = argument.substring(toIndex + TO_FLAG.length()).trim();

        if (description.isBlank()) {
            throw new TaskException("Event Task description cannot be empty.");
        }

        if (from.isBlank()) {
            throw new TaskException("Event Task start time cannot be empty.");
        }

        if (to.isBlank()) {
            throw new TaskException("Event Task end time cannot be empty.");
        }

        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        return getAddTaskString(newTask);
    }

    private String handleMarkTask(String argument) throws TaskException{
        if (argument.isBlank()) {
            throw new TaskException("Task id cannot be empty");
        }
        try {
            int taskId = Integer.parseInt(argument);
            Task task = tasks.get(taskId - 1);
            task.setCompleted(true);
            return "Nice! I've marked this task as done:\n" + task.getTaskDescription();
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Task id does not exist");
        }
    }

    private String handleUnmarkTask(String argument) throws TaskException {
        if (argument.isBlank()) {
            throw new TaskException("Task id cannot be empty");
        }
        try {
            int taskId = Integer.parseInt(argument);
            Task task = tasks.get(taskId - 1);
            task.setCompleted(false);
            return "OK, I've marked this task as not done yet:\n" + task.getTaskDescription();
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Task id does not exist");
        }
    }

    private String handleDeleteTask(String argument) throws TaskException {
        if (argument.isBlank()) {
            throw new TaskException("Task id cannot be empty");
        }
        try {
            int taskId = Integer.parseInt(argument);
            tasks.remove(taskId - 1);
            return "Noted. I've removed this task:\n"
                    + tasks.get(taskId - 1).getTaskDescription()
                    + "\nNow you have %d tasks in the list.".formatted(tasks.size());
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Task id does not exist");
        }
    }

}
