import storage.CsvTaskStorage;
import storage.TaskStorage;
import ui.ConsoleUi;
import ui.InputProcessor;

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
