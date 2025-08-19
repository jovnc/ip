package ui;

import exceptions.DateUtilsException;
import exceptions.TaskException;
import storage.TaskStorage;
import tasks.*;
import commands.Command;
import utils.DateUtils;

import java.time.LocalDateTime;

public class InputProcessor {

    private static final String BY_FLAG = "/by";
    private static final String FROM_FLAG = "/from";
    private static final String TO_FLAG = "/to";

    private static final String INVALID_MESSAGE = "Invalid command. Please try again.";

    private final TaskStorage storage;

    public InputProcessor(TaskStorage storage) {
        this.storage = storage;
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
        } catch (TaskException | DateUtilsException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred. Please try again.";
        }
    }

    /* Helper functions */
    private String getAddTaskString(Task task) {
        TaskList tasks = storage.getTasks();
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have %d tasks in the list.".formatted(tasks.getSize());
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
        TaskList tasks = storage.getTasks();
        return "Here are the tasks in your list:\n" + tasks.toString();
    }

    private String handleAddTodo(String argument) throws TaskException{
        if (argument.isBlank()) {
            throw new TaskException("Todo tasks.Task format must be: todo {description}");
        }

        Task newTask = new ToDo(argument, false);
        storage.addTask(newTask);

        return getAddTaskString(newTask);
    }

    private String handleAddDeadline(String argument) throws TaskException{

        if (!argument.contains(BY_FLAG)) {
            throw new TaskException("tasks.Deadline tasks.Task must contain '/by' flag");
        }

        String[] split = argument.split(BY_FLAG);

        if (split.length != 2) {
            throw new TaskException("tasks.Deadline tasks.Task format must be: deadline {description} /by {deadline}");
        }

        String description = argument.split(BY_FLAG)[0].trim();
        String by = argument.split(BY_FLAG)[1].trim();

        if (description.isBlank()) {
            throw new TaskException("tasks.Deadline tasks.Task description cannot be empty.");
        }

        if (by.isBlank()) {
            throw new TaskException("tasks.Deadline tasks.Task deadline cannot be empty.");
        }

        LocalDateTime parsedBy = DateUtils.parseDateString(by);

        Task newTask = new Deadline(description, false, parsedBy);
        storage.addTask(newTask);

        return getAddTaskString(newTask);
    }

    private String handleAddEvent(String argument) throws TaskException{
        int fromIndex = argument.indexOf(FROM_FLAG);
        int toIndex = argument.indexOf(TO_FLAG);

        if (fromIndex == -1 || toIndex == -1) {
            throw new TaskException("tasks.Event tasks.Task must contain '/from' and '/to' flags.");
        }

        if (fromIndex >= toIndex) {
            throw new TaskException("tasks.Event tasks.Task '/from' flag must come before '/to' flag");
        }

        String description = argument.substring(0, fromIndex).trim();
        String from = argument.substring(fromIndex + FROM_FLAG.length(), toIndex).trim();
        String to = argument.substring(toIndex + TO_FLAG.length()).trim();

        if (description.isBlank()) {
            throw new TaskException("tasks.Event tasks.Task description cannot be empty.");
        }

        if (from.isBlank()) {
            throw new TaskException("tasks.Event tasks.Task start time cannot be empty.");
        }

        if (to.isBlank()) {
            throw new TaskException("tasks.Event tasks.Task end time cannot be empty.");
        }

        LocalDateTime parsedFrom = DateUtils.parseDateString(from);
        LocalDateTime parsedTo = DateUtils.parseDateString(to);

        Task newTask = new Event(description, false, parsedFrom, parsedTo);
        storage.addTask(newTask);

        return getAddTaskString(newTask);
    }

    private String handleMarkTask(String argument) throws TaskException{
        if (argument.isBlank()) {
            throw new TaskException("tasks.Task id cannot be empty");
        }
        try {
            TaskList tasks = storage.getTasks();
            int taskId = Integer.parseInt(argument);
            Task task = tasks.getTask(taskId - 1);
            storage.setTaskCompletion(task, true);

            return "Nice! I've marked this task as done:\n" + task.toString();
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("tasks.Task id does not exist");
        }
    }

    private String handleUnmarkTask(String argument) throws TaskException {
        if (argument.isBlank()) {
            throw new TaskException("tasks.Task id cannot be empty");
        }
        try {
            TaskList tasks = storage.getTasks();
            int taskId = Integer.parseInt(argument);
            Task task = tasks.getTask(taskId - 1);
            storage.setTaskCompletion(task, false);

            return "OK, I've marked this task as not done yet:\n" + task.toString();
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("tasks.Task id does not exist");
        }
    }

    private String handleDeleteTask(String argument) throws TaskException {
        if (argument.isBlank()) {
            throw new TaskException("tasks.Task id cannot be empty");
        }
        try {
            int taskId = Integer.parseInt(argument);
            Task removedTask = storage.deleteTask(taskId - 1);

            TaskList tasks = storage.getTasks();
            return "Noted. I've removed this task:\n"
                    + removedTask.toString()
                    + "\nNow you have %d tasks in the list.".formatted(tasks.getSize());
        } catch (NumberFormatException e) {
            throw new TaskException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskException("tasks.Task id does not exist");
        }
    }

}
