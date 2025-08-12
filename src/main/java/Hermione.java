import java.util.Scanner;

public class Hermione {

    private static final String NAME = "Hermione";
    private static final String DIVIDER = "----------------------------------------";
    private static final String EXIT_COMMAND = "bye";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printMessage(getGreeting());
        echo();
        printMessage(getExit());
    }

    private static void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private static String getGreeting() {
        String greeting = "Hello! I'm %s\n".formatted(NAME)
                        + "What can I do for you?";
        return greeting;
    }

    private static String getExit() {
        String exit = "Bye. Hope to see you soon!";
        return exit;
    }

    private static void echo() {
        // Get user input
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            // Exit if the user types EXIT_COMMAND
            if (input.equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }
            printMessage(input);
        }
    }
}
