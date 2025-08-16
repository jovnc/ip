package tasks;

public class Event extends Task{
    private final String from;
    private final String to;

    public Event(String description, boolean isCompleted, String from, String to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: %s to: %s)".formatted(this.from, this.to);
    }
}
