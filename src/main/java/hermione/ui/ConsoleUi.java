package hermione.ui;

import java.util.Scanner;

/**
 * Represents the console user interface for the Hermione application.
 * Handles user input and output, allowing interaction with the application.
 */
public class ConsoleUi {

    private static final String DIVIDER = "----------------------------------------";
    private static boolean isRunning = true;

    private final Scanner scanner;
    private final InputProcessor inputProcessor;
    private final String name;

    /**
     * Creates a new ConsoleUi instance with the specified name and input processor.
     *
     * @param name           The name of the application or bot.
     * @param inputProcessor The input processor to handle user commands.
     */
    public ConsoleUi(String name, InputProcessor inputProcessor) {
        this.scanner = new Scanner(System.in);
        this.inputProcessor = inputProcessor;
        this.name = name;
    }

    /**
     * Exits the application by setting the running state to false.
     * This will cause the main loop in the start method to terminate.
     */
    public static void exit() {
        isRunning = false;
    }

    /**
     * Starts the console user interface, displaying a greeting message
     * and entering a loop to process user input commands until the application is exited.
     */
    public void start() {
        printMessage(getGreeting());

        while (isRunning) {
            String input = getUserInput();
            String result = inputProcessor.process(input);
            printMessage(result);
        }
    }

    private void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private String getGreeting() {
        return "Hello! I'm %s\nWhat can I do for you?".formatted(name);
    }

    private String getUserInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
