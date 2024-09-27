package g55870.atl.othello.view_javaFx;

import g55870.atl.othello.model.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Objects;
/**
 * The BoardPane class represents a visual component that displays the Othello game board.
 * It extends StackPane and contains a GridPane for arranging buttons representing board positions.
 */
public class BoardPane extends StackPane
{

    private Button[][] buttons;
    private final Model model;
    private final GridPane gridPaneBoard;
    /**
     * Constructs a BoardPane object with the specified Model.
     * @param model The Model containing game data.
     */
    public BoardPane(Model model) {
        this.model = Objects.requireNonNull(model, "model requis");
        gridPaneBoard = new GridPane();
        Rectangle rectangleBackground = new Rectangle();
        rectangleBackground.setFill(javafx.scene.paint.Color.LIGHTCYAN);
        this.getChildren().addAll(rectangleBackground, gridPaneBoard);
        rectangleBackground.widthProperty().bind(gridPaneBoard.heightProperty());
        rectangleBackground.heightProperty().bind(gridPaneBoard.heightProperty());
        initBoard();
        gridPaneBoard.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
    }
    /**
     * Creates a styled button based on the provided pawn color.
     *
     * @param pawn The pawn placed on the button.
     * @return The styled button.
     */
    public Button buttonStyle(Pawn pawn)
    {
        Button button = new Button();
        if(pawn ==null)
        {
            button.setShape(new Circle(1));
            button.setPrefSize(35,35);
            button.setStyle("-fx-background-color: grey");

        }
        if(pawn!=null &&pawn.getColor().equals(Color.NONE))
        {
            button.setShape(new Circle(1));
            button.setPrefSize(35,35);
            button.setStyle("-fx-background-color: lightgreen");
        }
        if(pawn!=null &&pawn.getColor().equals(Color.BLACK))
        {
            button.setShape(new Circle(1));
            button.setPrefSize(35,35);
            button.setStyle("-fx-background-color: Black");
        }
        if(pawn!=null &&pawn.getColor().equals(Color.WHITE))
        {
            button.setShape(new Circle(1));
            button.setPrefSize(35,35);
            button.setStyle("-fx-background-color: White");
        }
        return button;
    }
    /**
     * Gets the buttons representing the board positions.
     *
     * @return The buttons array.
     */

    public Button[][] getButtons() {
        return buttons;
    }

    /**
     * Initializes the game board by creating buttons and arranging them in the grid pane.
     */
    private void initBoard()
    {
        model.update();
        int boardSize = model.getBoardSize();
        buttons = new Button[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Button button = buttonStyle(model.getPawn(new Position(i, j)));
                buttons[i][j] = button;
                StackPane stackPane = new StackPane();
                Rectangle rectangleButton = new Rectangle(35,35);
                rectangleButton.setFill(javafx.scene.paint.Color.GRAY);
                stackPane.getChildren().addAll(rectangleButton,buttons[i][j]);
                stackPane.setAlignment(Pos.CENTER);
                gridPaneBoard.add(stackPane, i, j);
                gridPaneBoard.setVgap(2);
                gridPaneBoard.setHgap(2);
            }
        }
    }

    /**
     * Updates the game board by re-initializing it with the current state of the model.
     */
    public void update() {
        model.update();
        initBoard();
    }
}
