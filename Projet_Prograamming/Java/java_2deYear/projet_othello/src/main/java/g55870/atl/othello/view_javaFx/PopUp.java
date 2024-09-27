package g55870.atl.othello.view_javaFx;

import javafx.scene.control.Alert;
/**
 * The PopUp class provides static methods to display error and information pop-up dialogs in the Othello game.
 */
public class PopUp {
    /**
     * Displays an error pop-up dialog with the specified error message.
     *
     * @param errorMessage The error message to be displayed.
     * @throws IllegalArgumentException if the errorMessage is empty.
     */
    public static void showError(String errorMessage) {
        if(errorMessage.isEmpty()) {
            throw new IllegalArgumentException("Message error requis");
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'encodage");
        alert.setContentText(errorMessage);
        alert.show();
    }

    /**
     * Displays an information pop-up dialog with the specified winner message.
     *
     * @param winnerMessage The message indicating the winner of the game.
     * @throws IllegalArgumentException if the winnerMessage is empty.
     */
    public static void showInformation(String winnerMessage) {
        if(winnerMessage.isEmpty()) {
            throw new IllegalArgumentException("Message error requis");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victoire");
        alert.setContentText(winnerMessage);
        alert.show();

    }
}
