public abstract class Task {
    private final String name;
    private boolean isCompleted;

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return this.name;
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
        return "%s %s".formatted(status, this.name);
    }

}

