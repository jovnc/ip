import exceptions.TaskException;

import java.util.ArrayList;
import java.util.List;

public class InputProcessor {

    private static final String BY_FLAG = "/by";
    private static final String FROM_FLAG = "/from";
    private static final String TO_FLAG = "/to";

    private static final String INVALID_MESSAGE = "Invalid command. Please try again.";

    private final List<Task> tasks;

    public InputProcessor() {
        this.tasks = new ArrayList<>();
    }

    /* Main processing function */
    public String processInput(String message) {
        message = message.trim();
        String commandString = message.split(" ")[0];
        Command command = getCommandFromString(commandString);
        String argument = message.substring(commandString.length()).trim();

        try {
            return switch (command) {
                case LIST -> handleListTasks();
                case MARK -> handleMarkTask(argument);
                case UNMARK -> handleUnmarkTask(argument);
                case TODO -> handleAddTodo(argument);
                case DEADLINE -> handleAddDeadline(argument);
                case EVENT -> handleAddEvent(argument);
                case DELETE -> handleDeleteTask(argument);
                default -> INVALID_MESSAGE;
            };
        } catch (TaskException e) {
            return e.getMessage();
        }
    }

    /* Helper functions */
    private String getAddTaskString(Task task) {
        return "Got it. I've added this task:\n"
                + task.getTaskDescription()
                + "\nNow you have %d tasks in the list.".formatted(tasks.size());
    }

    private Command getCommandFromString(String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    /* Handler functions for the different commands */
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
            Task removedTask = tasks.remove(taskId - 1);
            return "Noted. I've removed this task:\n"
                    + removedTask.getTaskDescription()
                    + "\nNow you have %d tasks in the list.".formatted(tasks.size());
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("Task id does not exist");
        }
    }

}
