package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.model.Game;
import g55870.atl.othello.model.Model;
import g55870.atl.othello.view_javaFx.MainMenuScene;
import g55870.atl.othello.view_javaFx.PopUp;
import javafx.stage.Stage;

import java.util.Objects;
/**
 * The PlayAgainCommand class represents a command that handles the play again action in the Othello game.
 * It implements the Command interface.
 */
public class PlayAgainCommand implements Command {
    private final Controller controller;

    /**
     * Constructs the PlayAgainCommand
     * @param controller  of the othello game
     */
    public PlayAgainCommand(Controller controller) {

        this.controller = Objects.requireNonNull(controller,"controller requis");
    }

    /**
     * Executes the play again action when the play again button is clicked.
     */
    @Override
    public void execute() {

        this.controller.getGame().getGameScene().getGameView().getActionGame().getPlayAgain().setOnAction(e ->
                {
                    try {
                        this.controller.getModel().playAgain();
                        Model model = new Game();
                        Stage inGameStage = controller.getInGameStage();
                        MainMenuScene scene = new MainMenuScene(500,350);
                        Controller controller = new Controller(model,scene,inGameStage);
                        controller.start();
                        inGameStage.setScene(scene);
                        inGameStage.setTitle("Othello game");
                        inGameStage.setResizable(false);
                        inGameStage.show();
                    }
                    catch (Exception ex)
                    {
                        PopUp.showError("Partie deja en cours");
                    }
                }
        );
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}
