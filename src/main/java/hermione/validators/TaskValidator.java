package hermione.validators;

import hermione.exceptions.TaskValidationException;

public class TaskValidator {

    public void validateFields(String[] fields) {
        if (fields.length < 1) {
            throw new TaskValidationException("Invalid task format: " + String.join(",", fields));
        }

        String taskType = fields[0];
        switch (taskType) {
            case "T" -> validateTodoFields(fields);
            case "D" -> validateDeadlineFields(fields);
            case "E" -> validateEventFields(fields);
            default -> {
                throw new TaskValidationException("Invalid task type: " + taskType);
            }
        }
    }

    /* Helper functions to validate based on a task type */
    private void validateTodoFields(String[] fields) {
        if (fields.length != 3) {
            throw new TaskValidationException("Invalid number of fields for Todo Task");
        }

        String isCompleted = fields[1];
        validateIsCompleted(isCompleted);

        String description = fields[2];
        validateDescription(description);
    }

    private void validateDeadlineFields(String[] fields) {
        if (fields.length != 4) {
            throw new TaskValidationException("Invalid number of fields for Deadline Task");
        }

        String isCompleted = fields[1];
        validateIsCompleted(isCompleted);

        String description = fields[2];
        validateDescription(description);

        String by = fields[3];
        validateBy(by);
    }

    private void validateEventFields(String[] fields) {
        if (fields.length != 5) {
            throw new TaskValidationException("Invalid number of fields for Event Task");
        }

        String isCompleted = fields[1];
        validateIsCompleted(isCompleted);

        String description = fields[2];
        validateDescription(description);

        String from = fields[3];
        validateFrom(from);

        String to = fields[4];
        validateTo(to);
    }

    /* Helper functions to validate based on the field type */
    private void validateDescription(String description) {
        if (description.isBlank()) {
            throw new TaskValidationException("Description cannot be empty");
        }
    }

    private void validateIsCompleted(String isCompleted) {
        if (!isCompleted.equals("0") && !isCompleted.equals("1")) {
            throw new TaskValidationException("Invalid value for isCompleted field: " + isCompleted);
        }
    }

    private void validateBy(String by) {
        if (by.isBlank()) {
            throw new TaskValidationException("By cannot be empty");
        }
    }

    private void validateFrom(String from) {
        if (from.isBlank()) {
            throw new TaskValidationException("From cannot be empty");
        }
    }

    private void validateTo(String to) {
        if (to.isBlank()) {
            throw new TaskValidationException("To cannot be empty");
        }
    }

}
