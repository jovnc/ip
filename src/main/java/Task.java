public class Task {
    private final String name;
    private final int id;
    private boolean isCompleted;
    private static int idCounter = 1;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        this.id = idCounter;
        idCounter++;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        String status = this.isCompleted ? "[X]" : "[ ]";
        return "%d. %s %s".formatted(this.id, status, this.name);
    }
}
