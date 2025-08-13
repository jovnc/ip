public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

    }

    @Override
    public String getTaskDescription() {
        return "[E]"
                + super.getTaskDescription()
                + " (from: %s to: %s)".formatted(this.from, this.to);
    }

    @Override
    public String toString() {
        return "%d. %s".formatted(super.getId(), this.getTaskDescription());
    }
}
