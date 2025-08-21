package hermione.tasks;

import java.time.LocalDateTime;

import hermione.utils.DateUtils;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: %s to: %s)".formatted(DateUtils.formatDate(this.from), DateUtils.formatDate(this.to));
    }
}
