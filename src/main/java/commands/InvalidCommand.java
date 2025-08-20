package commands;

import storage.TaskStorage;

public class InvalidCommand extends Command {
    public InvalidCommand(String argument) {
        super(null, argument);
    }

    @Override
    public String execute() {
        return "[ERROR] Invalid command";
    }
}
