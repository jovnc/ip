public class ToDo extends Task{
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String getTaskDescription() {
        return "[T]" + super.getTaskDescription();
    }

    @Override
    public String toString() {
        return "%d. %s".formatted(super.getId(), this.getTaskDescription());
    }
}
