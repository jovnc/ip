import ui.ConsoleUi;
import ui.InputProcessor;

public class Hermione {

    private static final String NAME = "Hermione";

    public static void main(String[] args) {
        InputProcessor inputProcessor = new InputProcessor();
        ConsoleUi consoleUI = new ConsoleUi(NAME, inputProcessor);
        consoleUI.start();
    }
}
