public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
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
