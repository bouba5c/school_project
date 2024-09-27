package g55870.atl.othello.view_javaFx;

import g55870.atl.othello.model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Objects;
/**
 * The GameViewVbox class represents a vertical box that contains visual elements of the Othello game view.
 * It includes the game board, players information, action buttons, and the current player label.
 */
public class GameViewVbox extends VBox
{
    private final Model model;
    private final BoardPane boardPane;
    private final PlayersInformation playersInformation;
    private final ActionGame actionGame;
    private final Label labelCurrentPlayer;
    private final MenuBar menuBar;

    /**
     * Constructs a GameViewVbox object with the specified Model.
     * @param model The Model containing game data.
     */
    public GameViewVbox(Model model)
    {
        this.model = Objects.requireNonNull(model,"model requis");
        this.boardPane = new BoardPane(model);
        this.playersInformation = new PlayersInformation(model);
        this.actionGame = new ActionGame();
        VBox gameElement = new VBox();
        menuBar = new MainMenuVbox().getMenuBar();
        labelCurrentPlayer = new Label("Tour joueur : " + model.getCurrentPlayerId());
        labelCurrentPlayer.setUnderline(true);
        labelCurrentPlayer.setFont(new Font(30));
        labelCurrentPlayer.setTextFill(Color.LIGHTCYAN);
        gameElement.setSpacing(20);
        gameElement.getChildren().addAll(labelCurrentPlayer,boardPane,playersInformation,actionGame);
        gameElement.setAlignment(Pos.CENTER);
        if(model.getBoardSize()<10)
        {
            gameElement.setPadding(new Insets(150, 0, 0, 0));
        }
        getChildren().addAll(menuBar,gameElement);
        setSpacing(10);
    }
    /**
     * Gets the game board pane.
     * @return The BoardPane object.
     */
    public BoardPane getBoardPane() {
        return boardPane;
    }
    /**
     * Gets the menu bar component.
     * @return The MenuBar object.
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Gets the players information component.
     * @return The PlayersInformation object.
     */
    public PlayersInformation getPlayersInformation() {
        return playersInformation;
    }
    /**
     * Gets the action game component.
     * @return The ActionGame object.
     */
    public ActionGame getActionGame() {
        return actionGame;
    }
    /**
     * Updates the label displaying the current player.
     */
    public void updateLabelCurrentPlayer()
    {
        labelCurrentPlayer.setText("Tour joueur : " +(model.getCurrentPlayerId()));
    }
}
