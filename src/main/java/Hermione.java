import java.util.Scanner;

public class Hermione {

    private static final String NAME = "Hermione";
    private static final String DIVIDER = "----------------------------------------";
    private static final String EXIT_COMMAND = "bye";

    private static final Scanner scanner = new Scanner(System.in);
    private static final CommandProcessor commandProcessor = new CommandProcessor();

    public static void main(String[] args) {
        printMessage(getGreeting());
        processUserInput();
        printMessage(getExit());
    }

    private static void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private static String getGreeting() {
        return "Hello! I'm %s\n".formatted(NAME)
            + "What can I do for you?";
    }

    private static String getExit() {
        return "Bye. Hope to see you soon!";
    }

    private static void processUserInput() {
        while (true) {
            String input = getUserInput();
            if (input.equals(EXIT_COMMAND)) {
                break;
            }
            String result = commandProcessor.executeCommand(input);
            printMessage(result);
        }
    }

    private static String getUserInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
