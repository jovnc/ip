package hermione.commands;

import hermione.ui.ConsoleUi;

/**
 * Represents a command to exit the Hermione application.
 */
public class ExitCommand extends Command {
    public ExitCommand(String argument) {
        super(null, argument);
    }

    /**
     * Executes the command to exit the application.
     * This method will close the application and return a goodbye message.
     *
     * @return A goodbye message indicating the application is closing.
     */
    @Override
    public String execute() {
        ConsoleUi.exit();
        return "Bye. Hope to see you soon!";
    }
}
