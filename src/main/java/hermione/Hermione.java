package hermione;

import hermione.storage.CsvTaskStorage;
import hermione.storage.TaskStorage;
import hermione.ui.ConsoleUi;
import hermione.ui.InputProcessor;

public class Hermione {

    private static final String NAME = "Hermione";

    private final ConsoleUi consoleUI;

    public Hermione(String filePath) {
        TaskStorage storage = new CsvTaskStorage(filePath);
        InputProcessor inputProcessor = new InputProcessor(storage);
        this.consoleUI = new ConsoleUi(NAME, inputProcessor);
    }

    public void run() {
        this.consoleUI.start();
    }

    public static void main(String[] args) {
        new Hermione("data/tasks.csv").run();
    }
}
