package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.view_javaFx.HelpScene;
import javafx.stage.Stage;

import java.util.Objects;
/**
 * The HelpCommand class represents a command that handles the help action in the Othello game.
 * It implements the Command interface.
 */
public class HelpCommand implements Command{

    private final Controller controller;

    private final HelpScene helpScene;

    /**
     * Construct a new help command
     * @param controller of the othello game
     */
    public HelpCommand(Controller controller) {
        this.controller = Objects.requireNonNull(controller,"controller requis");
        helpScene = new HelpScene();
    }
    /**
     * Executes the help command for the main menu and the game scene
     */
    @Override
    public void execute()
    {
        if(controller.getMainMenuScene()!=null)
        {
            this.controller.getMainMenuScene().getMainMenu().getHelpItem().setOnAction(e -> openHelpScene());
        }
        if(controller.getGame().getGameScene()!=null)
        {
            this.controller.getGame().getGameScene().getGameView().getMenuBar().getMenus().get(0).getItems().get(0).setOnAction(e -> openHelpScene());
        }
    }

    /**
     * this method will open a help scene
     */
    private void openHelpScene()
    {
        Stage stage = new Stage();
        stage.setScene(helpScene);
        stage.setTitle("aide");
        stage.show();
    }



    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
