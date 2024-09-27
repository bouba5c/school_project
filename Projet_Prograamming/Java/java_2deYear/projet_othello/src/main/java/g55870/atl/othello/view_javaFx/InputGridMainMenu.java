package g55870.atl.othello.view_javaFx;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
/**
 * The InputGridMainMenu class represents a grid layout for input elements in the main menu of the Othello game.
 * It includes text fields for entering game settings and buttons for selecting game modes.
 */
public class InputGridMainMenu extends GridPane
{
    private final TextField askSize ;
    private final Button buttonIa;
    private final Button buttonPlayer;
    private final Button buttonRandom;
    /**
     * Constructs an InputGridMainMenu object.
     */

    public InputGridMainMenu()
    {
        askSize = createTextField("entrer la taille du plateau");
        askSize.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        add(askSize,15,3);
        VBox buttonVbox= new VBox();
        buttonPlayer = createButton("JOUEUR VS JOUEUR");
        buttonRandom = createButton("JOUEUR VS RANDOM");
        buttonIa = createButton("JOUEUR VS IA");
        buttonVbox.setSpacing(10);
        buttonVbox.getChildren().addAll(buttonPlayer,buttonRandom,buttonIa);
        add(buttonVbox,15,5);
        setAlignment(Pos.CENTER);
        setVgap(10);
    }
    /**
     * Gets the text field for entering the size of the game board.
     * @return The TextField object.
     */
    public TextField getAskSize() {
        return askSize;
    }
    /**
     * Gets the button for selecting the player vs IA game mode.
     
     *
     * @return The Button object.
     */
    public Button getButtonIa() {

        return buttonIa;
    }
    /**
     * Gets the button for selecting the player vs player game mode.
     *
     * @return The Button object.
     */
    public Button getButtonPlayer() {
        return buttonPlayer;
    }
    /**
     * Gets the button for selecting the player vs randomIA game mode.
     *
     * @return The Button object.
     */
    public Button getButtonRandom() {
        return buttonRandom;
    }
    /**
     * Creates a text field with the specified nameField.
     *
     * @param msg The name of the text field.
     * @return The created TextField object.
     * @throws IllegalArgumentException if the msg is empty
     */
    public TextField createTextField(String msg)
    {
        if(msg.isEmpty())
        {
            throw new IllegalArgumentException("text field name requis");
        }
        TextField textfield = new TextField(msg);
        textfield.setStyle("-fx-text-fill: grey;");
        textfield.setOnMouseClicked(e -> {
            textfield.setText("");
            textfield.setStyle("-fx-text-fill: black;");

        });
        return textfield;
    }
    /**
     * Creates a button with the specified text.
     *
     * @param text The text displayed on the button.
     * @return The created Button object.
     * @throws IllegalArgumentException if the text is empty
     */
    public Button createButton(String text)
    {
        if(text.isEmpty())
        {
            throw new IllegalArgumentException("button name requis");
        }
        Button button = new Button(text);
        button.setPrefSize(250,0);
        return button;
    }
}
