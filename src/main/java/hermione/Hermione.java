package hermione;

import hermione.storage.CsvTaskStorage;
import hermione.storage.TaskStorage;
import hermione.ui.common.InputProcessor;
import hermione.ui.console.ConsoleUi;
import hermione.ui.fx.Main;
import javafx.application.Application;

/**
 * The main class for the Hermione application.
 */
public class Hermione {

    private static final String NAME = "Hermione";

    private final InputProcessor inputProcessor;

    /**
     * Initializes the Hermione application with a specified file path for task storage.
     * This constructor sets up the task storage using a CSV file and initializes the input processor
     * and console UI for user interaction.
     *
     * @param filePath The path to the CSV file where tasks will be stored.
     */
    public Hermione(String filePath) {
        TaskStorage storage = new CsvTaskStorage(filePath);
        this.inputProcessor = new InputProcessor(storage);
    }

    /**
     * Main method to run the Hermione application.
     * This method creates an instance of Hermione with the specified task storage file path
     * and starts the application by calling the run method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Usage: java -jar hermione.jar [--console]");
        } else if (args.length == 1 && args[0].equals("--console")) {
            startConsole();
        } else {
            startGui();
        }
    }

    private static void startConsole() {
        new ConsoleUi(NAME).start();
    }

    private static void startGui() {
        Application.launch(Main.class);
    }

    /**
     * Gets response from the input processor for the given input.
     *
     * @param input The user input command as a string.
     * @return The response message after processing the command.
     */
    public String getResponse(String input) {
        return inputProcessor.process(input);
    }
}
