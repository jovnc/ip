package hermione.tasks;

public abstract class Task {
    private final String description;
    private boolean isCompleted;

    public Task(String name, boolean isCompleted) {
        this.description = name;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        String status = this.isCompleted ? "[X]" : "[ ]";
        return "%s %s".formatted(status, this.description);
    }

}

