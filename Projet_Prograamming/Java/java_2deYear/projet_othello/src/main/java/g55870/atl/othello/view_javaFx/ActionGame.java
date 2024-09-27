package g55870.atl.othello.view_javaFx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
/**
 * The ActionGame class represents a visual component that contains action buttons for the Othello game.
 * It extends HBox and provides buttons for redo, undo, surrender, and play again actions.
 */
public class ActionGame extends HBox {
    private final Button redo;
    private final Button undo;
    private final Button surrender;
    private final Button playAgain;
    /**
     * Constructs an ActionGame object with action buttons.
     */
    public ActionGame() {
        redo = createButton("refaire");
        undo = createButton("annuler");
        surrender = createButton("abandonner");
        playAgain = createButton("rejouer");
        setAlignment(Pos.BOTTOM_CENTER);
        setSpacing(20);
        this.getChildren().addAll(undo,redo,surrender,playAgain);

    }
    /**
     * Creates a button with the specified name.
     *
     * @param buttonName The name of the button.
     * @return The created button.
     */
    public Button createButton(String buttonName)
    {
        Button button = new Button(buttonName);
        button.setPrefSize(200,20);
        return button;
    }
    /**
     * Gets the play again button.
     *
     * @return The play again button.
     */
    public Button getPlayAgain() {
        return playAgain;
    }
    /**
     * Gets the redo button.
     *
     * @return The redo button.
     */
    public Button getRedo() {
        return redo;
    }

    /**
     * Gets the undo button.
     *
     * @return The undo button.
     */
    public Button getUndo() {
        return undo;
    }

    /**
     * Gets the surrender button.
     *
     * @return The surrender button.
     */
    public Button getSurrender() {
        return surrender;
    }

}
