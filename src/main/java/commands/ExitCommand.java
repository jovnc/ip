package commands;

import ui.ConsoleUi;

public class ExitCommand extends Command {
    public ExitCommand(String argument) {
        super(null, argument);
    }

    @Override
    public String execute() {
        ConsoleUi.exit();
        return "Bye. Hope to see you soon!";
    }
}
