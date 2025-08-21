package hermione.ui;

import java.util.Scanner;

public class ConsoleUi {

    private static final String DIVIDER = "----------------------------------------";
    private static boolean isRunning = true;

    private final Scanner scanner;
    private final InputProcessor inputProcessor;
    private final String name;

    public ConsoleUi(String name, InputProcessor inputProcessor) {
        this.scanner = new Scanner(System.in);
        this.inputProcessor = inputProcessor;
        this.name = name;
    }

    public static void exit() {
        isRunning = false;
    }

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
