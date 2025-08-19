package commands;

import storage.TaskStorage;

public abstract class Command {

    protected final TaskStorage storage;
    protected final String argument;

    public Command(TaskStorage storage, String argument) {
        this.storage = storage;
        this.argument = argument;
    }

    public abstract String execute();
}
