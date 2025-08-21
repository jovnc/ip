package hermione.tasks;

public class ToDo extends Task {
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
