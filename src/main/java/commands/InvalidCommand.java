package commands;

import storage.TaskStorage;

public class InvalidCommand extends Command {
    public InvalidCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        return "[ERROR] Invalid command";
    }
}
