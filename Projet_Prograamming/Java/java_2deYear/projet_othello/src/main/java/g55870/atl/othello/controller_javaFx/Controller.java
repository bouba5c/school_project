package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.model.*;
import g55870.atl.othello.view_javaFx.MainMenuScene;
import g55870.atl.othello.view_javaFx.PopUp;
import javafx.stage.Stage;
import java.util.Objects;

public class Controller {
    private final Model model;
    private final MainMenuScene mainMenuScene;
    private final Stage primaryStage;
    private InitializeGame game;
    private final Stage inGameStage ;

    public Stage getInGameStage() {
        return inGameStage;
    }

    /**
     * gets the main menu scene
     * @return main menu scene of the othello game
     */
    public MainMenuScene getMainMenuScene() {
        return mainMenuScene;
    }

    /**
     * gets the model
     * @return  model of the othello game
     */
    public Model getModel() {
        return model;
    }

    /**
     * gets initialized game of the othello game
     * @return initialize game
     */
    public InitializeGame getGame() {
        return game;
    }

    /**
     *
     * @param model model of the othello game
     * @param mainMenuScene main menu scene of the othello application
     * @param stage primary stage of the application
     * @throws NullPointerException if the param are null
     */
    public Controller(Model model, MainMenuScene mainMenuScene, Stage stage) {
        this.model =Objects.requireNonNull(model,"model requis");
        this.mainMenuScene = Objects.requireNonNull(mainMenuScene,"scene requis");
        this.primaryStage = Objects.requireNonNull(stage,"stage requis");
        inGameStage = new Stage();
    }

    /**
     * Start method of the othello game
     */
    public void start() {
        this.game = new InitializeGame(mainMenuScene, model, primaryStage,inGameStage);
        this.game.execute();
        ExitCommand exitCommand = new ExitCommand(this);
        exitCommand.execute();
        HelpCommand helpCommand = new HelpCommand(this);
        helpCommand.execute();
        primaryStage.setOnHidden(event -> {
            if(!model.getState().equals(State.NOT_STARTED))
            {
                model.addObserver(game.getGameScene());
                UndoCommand undoCommand = new UndoCommand(game.getGameScene());
                undoCommand.execute();
                RedoCommand redoCommand = new RedoCommand(game.getGameScene());
                redoCommand.execute();
                ExitCommand exitCommandInGame = new ExitCommand(this);
                exitCommandInGame.execute();
                HelpCommand helpCommandInGame = new HelpCommand(this);
                helpCommandInGame.execute();
                SurrenderCommand surrender = new SurrenderCommand(this);
                surrender.execute();
                OnPutPawn onPutPawn = new OnPutPawn(game.getGameScene());
                onPutPawn.execute();
                PlayAgainCommand playAgainCommand = new PlayAgainCommand(this);
                playAgainCommand.execute();

            }
             if(model.getState().equals(State.GAME_OVER))
            {
                PopUp.showInformation((model.getWinner()==-1)? "match nul" : "Le vainqueur est le joueur :" +(model.getWinner()) +
                        "\n" +" joueur 1 : " + model.getBlackScore()  +" pions noirs" +
                        "\n" +" joueur 2 : " + model.getWhiteScore() + " pions blancs" );
            }

        });

        }


}





