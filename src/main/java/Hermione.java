public class Hermione {

    private static String NAME = "Hermione";
    private static String DIVIDER = "----------------------------------------";

    public static void main(String[] args) {
        System.out.println(DIVIDER);
        Hermione.sayGreeting();
        System.out.println(DIVIDER);
        Hermione.sayExit();
        System.out.println(DIVIDER);
    }

    private static void sayGreeting() {
        String greeting = "Hello! I'm %s\n".formatted(NAME)
                        + "What can I do for you?";
        System.out.println(greeting);
    }

    private static void sayExit() {
        String exit = "Bye. Hope to see you soon!";
        System.out.println(exit);
    }
}
