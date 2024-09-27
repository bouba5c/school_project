package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.model.*;
import g55870.atl.othello.view_javaFx.GameScene;
import g55870.atl.othello.view_javaFx.MainMenuScene;

import g55870.atl.othello.view_javaFx.PopUp;
import javafx.stage.Stage;

import java.util.Objects;
/**
 * InitializeGame class represents a command that handles the input grid of the main menu scene to init the game
 * It implements the Command interface.
 */
public class InitializeGame implements Command{
    private final MainMenuScene mainMenu;
    private final Model model;
    private GameScene gameScene;
    private final Stage stage;
    private final Stage inGameStage;

    /**
     * Construct an Initialize game
     * @param view the main menu scene
     * @param model of the othello game
     * @param stage the primary stage of the javafx app
     * @param inGameStage the stage where the game is displayed
     */
    public InitializeGame(MainMenuScene view, Model model,Stage stage,Stage inGameStage) {
        this.mainMenu = Objects.requireNonNull(view,"main menu requis");
        this.model = Objects.requireNonNull(model,"model requis");
        this.stage = Objects.requireNonNull(stage,"stage requis");
        this.inGameStage = Objects.requireNonNull(inGameStage,"stage in game requis");
    }

    /**
     * Executes the initialize game with different parameters depending on the button clicked
     */
    @Override
    public void execute()
    {

        mainMenu.getMainMenu().getInputGrid().getButtonPlayer().setOnAction(
                e -> {
                    try {

                        initGame(1,Integer.parseInt(mainMenu.getMainMenu().getInputGrid().getAskSize().getText()),"player vs player" ,stage,inGameStage);
                    }
                    catch (Exception error)
                    {
                        PopUp.showError(error.getMessage());
                    }
                });
        mainMenu.getMainMenu().getInputGrid().getButtonRandom().setOnAction(

                e -> {
                    try
                    {
                        initGame(2, Integer.parseInt(mainMenu.getMainMenu().getInputGrid().getAskSize().getText()), "player vs Random", stage,inGameStage);
                    }
                    catch (Exception error)
                    {
                        PopUp.showError(error.getMessage());
                    }                    });
        mainMenu.getMainMenu().getInputGrid().getButtonIa().setOnAction(
                e -> {try {
                    initGame(3,Integer.parseInt(mainMenu.getMainMenu().getInputGrid().getAskSize().getText()),"player vs ia" ,stage,inGameStage);
                }
                catch (Exception error)
                {
                    PopUp.showError(error.getMessage());
                }
                });
    }

    /**
     * gets the game scene
     * @return the game Scene
     */
    public GameScene getGameScene() {
        return gameScene;
    }

    /**
     *
     * @param gameMode the game mode 1:player vs player 2:player vs random 3:player vs Ia
     * @param size the size of the game board
     * @param title title of the gameScene
     * @param primaryStage the primary stage of javafx
     * @param inGameStage the stage where the game scene is displayed.
     */
    void initGame(int gameMode , int size , String title, Stage primaryStage,Stage inGameStage)
    {
        model.start(gameMode,size);
        this.gameScene = new GameScene(model);
        inGameStage.setScene(gameScene);
        inGameStage.setTitle(title);
        inGameStage.show();
        primaryStage.close();
    }



    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
