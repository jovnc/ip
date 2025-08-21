package hermione.tasks;

import java.time.LocalDateTime;

import hermione.utils.DateUtils;

public class Deadline extends Task {

    private final LocalDateTime by;

    public Deadline(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: %s)".formatted(DateUtils.formatDate(this.by));
    }
}
