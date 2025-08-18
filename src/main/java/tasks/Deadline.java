package tasks;

public class Deadline extends Task {

    private final String by;

    public Deadline(String description, boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: %s)".formatted(this.by);
    }
}
