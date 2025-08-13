import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CommandProcessor {
    private static final String LIST_COMMAND = "list";
    private final List<String> items;

    public CommandProcessor() {
        this.items = new ArrayList<>();
    }

    public String executeCommand(String command) {
        switch (command) {
            case LIST_COMMAND:
                return getListMessage();
            default:
                return addItem(command);
        }
    }

    private String getListMessage() {
        return IntStream.range(0, items.size())
                .mapToObj(i -> (i + 1) + ". " + items.get(i))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No items in the list.");
    }

    private String addItem(String item) {
        items.add(item);
        return "added: %s".formatted(item);
    }
}
