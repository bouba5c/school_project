package g55870.atl.othello.controller_javaFx;

import javafx.application.Platform;

import java.util.Objects;
/**
 * The ExitCommand class represents a command that handles the exit action in the Othello game.
 * It implements the Command interface.
 */
public class ExitCommand implements Command{
    private final Controller controller;

    /**
     * Construct exit command to quit the game
     * @param controller of the othello game
     *
     */
    public ExitCommand(Controller controller ) {

        this.controller = Objects.requireNonNull(controller,"controller requis");
    }

    /**
     * Executes the exit command for the main menu and the game scene
     */
    @Override
    public void execute() {
        if(controller.getMainMenuScene()!=null)
        {
            this.controller.getMainMenuScene().getMainMenu().getExitItem().setOnAction(e -> Platform.exit());
        }
        if(controller.getGame().getGameScene()!=null)
        {
            this.controller.getGame().getGameScene().getGameView().getMenuBar().getMenus().get(0).getItems().get(1).setOnAction(e -> Platform.exit());
        }
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
