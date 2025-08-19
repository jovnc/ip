package ui;

import commands.Command;
import parsers.CommandParser;
import storage.TaskStorage;

public class InputProcessor {

    private final TaskStorage storage;

    public InputProcessor(TaskStorage storage) {
        this.storage = storage;
    }

    public String process(String message) {
        message = message.trim();
        String commandString = message.split(" ")[0];
        String argument = message.substring(commandString.length()).trim();
        try {
            Command command = CommandParser.parse(commandString, argument, storage);
            return command.execute();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
