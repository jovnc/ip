public class Deadline extends Task {

    private final String by;

    public Deadline(String description, boolean isCompleted, String by) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public String getTaskDescription() {
        return "[D]"
                + super.getTaskDescription()
                + " (by: %s)".formatted(this.by);
    }

    @Override
    public String toString() {
        return "%d. %s".formatted(super.getId(), this.getTaskDescription());
    }
}
