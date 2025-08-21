package hermione;

import hermione.storage.CsvTaskStorage;
import hermione.storage.TaskStorage;
import hermione.ui.ConsoleUi;
import hermione.ui.InputProcessor;

public class Hermione {

    private static final String NAME = "Hermione";

    private final ConsoleUi consoleUI;

    /**
     * Initializes the Hermione application with a specified file path for task storage.
     * This constructor sets up the task storage using a CSV file and initializes the input processor
     * and console UI for user interaction.
     *
     * @param filePath The path to the CSV file where tasks will be stored.
     */
    public Hermione(String filePath) {
        TaskStorage storage = new CsvTaskStorage(filePath);
        InputProcessor inputProcessor = new InputProcessor(storage);
        this.consoleUI = new ConsoleUi(NAME, inputProcessor);
    }

    /**
     * Starts the Hermione application.
     * This method initializes the console UI and begins the user interaction loop.
     * It is the entry point for running the application.
     */
    public void run() {
        this.consoleUI.start();
    }

    /**
     * Main method to run the Hermione application.
     * This method creates an instance of Hermione with the specified task storage file path
     * and starts the application by calling the run method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Hermione("data/tasks.csv").run();
    }
}
