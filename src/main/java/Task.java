public abstract class Task {
    private final String name;
    private final int id;
    private boolean isCompleted;
    private static int idCounter = 1;

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.id = idCounter;
        idCounter++;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getTaskDescription() {
        String status = this.isCompleted ? "[X]" : "[ ]";
        return "%s %s".formatted(status, this.name);
    }

    public int getId() {
        return this.id;
    }
}
