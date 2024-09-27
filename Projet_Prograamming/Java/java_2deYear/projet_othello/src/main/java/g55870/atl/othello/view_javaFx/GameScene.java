package g55870.atl.othello.view_javaFx;
import g55870.atl.othello.model.Model;
import g55870.atl.othello.model.Observer;
import g55870.atl.othello.model.State;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.util.Objects;
/**
 * The GameScene class represents the main scene of the Othello game.
 * It extends Scene and implements the Observer interface to receive updates from the Model.
 * It contains visual components such as the game view, board pane, and players information.
 */
public class GameScene extends Scene implements Observer
{
    private final GameViewVbox gameViewVbox;
    private final Model model;
    private final BoardPane boardPane;
    private final PlayersInformation playersInformation;
    /**
     * Constructs a GameScene object with the specified 8Model.
     * @param model The Model containing game data.
     */
    public GameScene(Model model) {
        super(new StackPane(), 900, 900);
        this.model = Objects.requireNonNull(model,"model requis");
        StackPane gameSceneStackPane = (StackPane) getRoot();
        Image backgroundImage = new Image("gameSceneBackground.png");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.fitHeightProperty().bind(heightProperty());
        backgroundView.fitWidthProperty().bind(widthProperty());
        this.gameViewVbox = new GameViewVbox(model);
        gameSceneStackPane.getChildren().addAll(backgroundView,gameViewVbox);
        boardPane = gameViewVbox.getBoardPane();
        playersInformation = gameViewVbox.getPlayersInformation();

    }
    /**
     * Gets the game view.
     * @return The game view.
     */
    public GameViewVbox getGameView() {
        return gameViewVbox;
    }
    /**
     * Gets the model.
     * @return The Model containing game data.
     */
    public Model getModel() {
        return model;
    }
    /**
     * Updates the scene based on changes in the Model.
     */
    @Override
    public void update()  {
        if(!(model.getState().equals(State.GAME_OVER) ||model.getState().equals(State.NOT_STARTED)))
        {
            boardPane.update();
            gameViewVbox.updateLabelCurrentPlayer();
            playersInformation.update();
        }
    }
}
