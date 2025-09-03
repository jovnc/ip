package hermione.ui.javafx;

import hermione.Hermione;
import hermione.utils.UiUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/harry.jpeg"));
    private final Image dukeImage = new Image(getClass().getResourceAsStream("/images/hermione.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Hermione hermione;

    /**
     * Initializes the main window and sets up the scroll pane to follow the dialog
     * container's height.
     * Also adds a greeting dialog from Hermione upon initialization.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(UiUtils.getGreeting("Hermione"), dukeImage));

        // Initialize button as disabled
        sendButton.setDisable(true);

        // Add listener to enable/disable button based on input
        userInput.textProperty().addListener((observable, oldValue, newValue) -> {
            sendButton.setDisable(newValue == null || newValue.trim().isEmpty());
        });
    }

    /**
     * Injects the Hermione instance
     */
    public void setHermione(Hermione hermione) {
        this.hermione = hermione;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            return; // Don't process empty input
        }
        String response = hermione.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
