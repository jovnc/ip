package ui;

import storage.CsvTaskStorage;

import java.util.Scanner;

public class ConsoleUi {

    private static final String DIVIDER = "----------------------------------------";
    private static final String EXIT_COMMAND = "bye";

    private final Scanner scanner;
    private final InputProcessor inputProcessor;
    private final String name;

    public ConsoleUi(String name, InputProcessor inputProcessor) {
        this.scanner = new Scanner(System.in);
        this.inputProcessor = inputProcessor;
        this.name = name;
    }

    public void start() {
        printMessage(getGreeting());

        while (true) {
            String input = getUserInput();
            if (input.equals(EXIT_COMMAND)) {
                break;
            }
            String result = inputProcessor.processInput(input);
            printMessage(result);
        }

        printMessage(getExit());
    }

    private void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private String getGreeting() {
        return "Hello! I'm %s\nWhat can I do for you?".formatted(name);
    }

    private String getExit() {
        return "Bye. Hope to see you soon!";
    }

    private String getUserInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
