package hermione.validators;

import hermione.exceptions.TaskValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskValidatorTest {

    private TaskValidator validator;

    private String VALID_DATE_STRING  = "1/1/2021 1600";
    private String INVALID_DATE_STRING = "2021-01-01 1600";

    @BeforeEach
    public void setUp() {
        this.validator = new TaskValidator();
    }

    @Test
    public void validateFields_invalidTaskType_throwsException() {
        String[] invalidFields = {"X", "0", "Description"};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validateFields_validToDoFields_noException() {
        String[] validFields = {"T", "0", "Description"};
        Assertions.assertDoesNotThrow(
                () -> this.validator.validateFields(validFields)
        );
    }

    @Test
    public void validateFields_emptyToDoTaskDescription_throwsException() {
        String[] invalidFields = {"T", "0", ""};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validateFields_invalidToDoTaskIsComplete_throwsException() {
        String[] invalidFields = {"T", "Yes", "Description"};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validateFields_validDeadlineFields_noException() {
        String[] validFields = {"D", "0", "Description", VALID_DATE_STRING};
        Assertions.assertDoesNotThrow(
                () -> this.validator.validateFields(validFields)
        );
    }

    @Test
    public void validateFields_emptyDeadlineTaskDescription_throwsException() {
        String[] invalidFields = {"D", "0", "", VALID_DATE_STRING};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validateFields_invalidDeadlineTaskIsComplete_throwsException() {
        String[] invalidFields = {"D", "Yes", "Description", VALID_DATE_STRING};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validateFields_invalidDeadlineTaskByDate_throwsException() {
        String[] invalidFields = {"D", "0", "Description", INVALID_DATE_STRING};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validatefields_validEventFields_noException() {
        String[] validFields = {"E", "0", "Description", VALID_DATE_STRING, VALID_DATE_STRING};
        Assertions.assertDoesNotThrow(
                () -> this.validator.validateFields(validFields)
        );
    }

    @Test
    public void validateFields_emptyEventTaskDescription_throwsException() {
        String[] invalidFields = {"E", "0", "", VALID_DATE_STRING, VALID_DATE_STRING};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validateFields_invalidEventTaskFromDate_throwsException() {
        String[] invalidFields = {"E", "0", "Description", INVALID_DATE_STRING, VALID_DATE_STRING};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }

    @Test
    public void validateFields_invalidEventTaskToDate_throwsException() {
        String[] invalidFields = {"E", "0", "Description", VALID_DATE_STRING, INVALID_DATE_STRING};
        Assertions.assertThrows(
                TaskValidationException.class,
                () -> this.validator.validateFields(invalidFields)
        );
    }


}
