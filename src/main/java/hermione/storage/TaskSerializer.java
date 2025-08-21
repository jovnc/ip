package hermione.storage;

import java.time.LocalDateTime;

import hermione.tasks.Deadline;
import hermione.tasks.Event;
import hermione.tasks.Task;
import hermione.tasks.ToDo;
import hermione.utils.DateUtils;
import hermione.validators.TaskValidator;

public class TaskSerializer {

    private static final TaskValidator validator = new TaskValidator();

    public Task deserialize(String line) {
        String[] fields = line.split(",");

        String taskType = fields[0];
        boolean isCompleted = parseBinaryToBoolean(fields[1]);
        String description = fields[2];

        return createTask(taskType, isCompleted, description, fields);
    }

    public String serialize(Task task) {
        String baseFields = buildBaseFields(task);
        String typeSpecificFields = buildTypeSpecificFields(task);
        return baseFields + typeSpecificFields;
    }

    private String buildBaseFields(Task task) {
        String taskType = getTaskType(task);
        String completed = parseBooleanToBinary(task.isCompleted());
        String description = task.getName();
        return String.join(",", taskType, completed, description);
    }

    private String buildTypeSpecificFields(Task task) {
        if (task instanceof Deadline deadline) {
            return "," + DateUtils.formatDate(deadline.getBy());
        } else if (task instanceof Event event) {
            return "," + DateUtils.formatDate(event.getFrom()) + "," + DateUtils.formatDate(event.getTo());
        }
        return "";
    }

    /* Helper functions */
    private boolean parseBinaryToBoolean(String binary) {
        return switch (binary) {
            case "0" -> false;
            case "1" -> true;
            default -> throw new IllegalArgumentException("Invalid binary value: " + binary);
        };
    }

    private String parseBooleanToBinary(boolean bool) {
        return bool ? "1" : "0";
    }

    private String getTaskType(Task task) {
        if (task instanceof ToDo) return "T";
        if (task instanceof Deadline) return "D";
        if (task instanceof Event) return "E";
        throw new IllegalArgumentException("Unknown task type: " + task.getClass());
    }

    private Task createTask(String taskType, boolean isCompleted, String description, String[] fields) {
        return switch (taskType) {
            case "T" -> new ToDo(description, isCompleted);
            case "D" -> createDeadlineTask(description, isCompleted, fields);
            case "E" -> createEventTask(description, isCompleted, fields);
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };
    }

    private Deadline createDeadlineTask(String description, boolean isCompleted, String[] fields) {
        String by = fields.length >= 4 ? fields[3] : "";
        LocalDateTime parsedBy = DateUtils.undoFormatDate(by);
        return new Deadline(description, isCompleted, parsedBy);
    }

    private Event createEventTask(String description, boolean isCompleted, String[] fields) {
        String from = fields.length >= 4 ? fields[3] : "";
        String to = fields.length >= 5 ? fields[4] : "";
        LocalDateTime parsedFrom = DateUtils.undoFormatDate(from);
        LocalDateTime parsedTo = DateUtils.undoFormatDate(to);
        return new Event(description, isCompleted, parsedFrom, parsedTo);
    }
}
