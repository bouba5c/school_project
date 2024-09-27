package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.model.Color;
import g55870.atl.othello.view_javaFx.PopUp;
import g55870.atl.othello.model.Pawn;
import g55870.atl.othello.model.Position;
import g55870.atl.othello.model.State;
import g55870.atl.othello.view_javaFx.GameScene;
import javafx.animation.PauseTransition;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.Objects;

public class OnPutPawn implements Command {
    private final GameScene gameScene;

    /**
     * Constructs an OnPutPawn object with the specified GameScene.
     *
     * @param gameScene The GameScene where the pawn is put.
     */
    public OnPutPawn(GameScene gameScene) {
        this.gameScene = Objects.requireNonNull(gameScene,"gameScene requis");
    }
    /**
     * Executes the command when a player puts a pawn on the game board. It sets up event handlers for buttons
     * on the game board and updates the game state accordingly.
     */
    @Override
    public void execute() {
        for (int i = 0; i < gameScene.getModel().getBoardSize(); i++) {
            for (int j = 0; j < gameScene.getModel().getBoardSize(); j++) {
                int finalI = i;
                int finalJ = j;
                gameScene.getGameView().getBoardPane().getButtons()[i][j].setOnAction(e -> {
                    try{
                        gameScene.getModel().putPawn(new Pawn(new Position(finalI, finalJ), gameScene.getModel().getCurrentPlayerColor()));
                    }
                    catch (Exception ignored)
                    {
                    }
                    if (gameScene.getModel().getState().equals(State.PUT_WALL)) {
                        gameScene.getModel().putWall(new Pawn(new Position(finalI, finalJ),Color.MUR));
                        this.execute();
                    }
                    gameModePlay(gameScene.getModel().getGameMode());
                });
            }
        }
    }
    /**
     * Handles game mode specific actions after a pawn is put on the game board.
     *
     * @param gameMode The current game mode.
     */
    public void gameModePlay(int gameMode)
    {
            if(gameMode==2 || gameMode==3)
            {
                PauseTransition pauseTransition = getPauseTransition();
                pauseTransition.play();
            }
            else{
                if(gameScene.getModel().getState().equals(State.TURN_END)) {
                    gameScene.getModel().nextPlayer();
                }
                if (gameScene.getModel().getState().equals(State.PUT)) {
                    this.execute();
                }
                else if(gameScene.getModel().getState().equals(State.GAME_OVER))
                {
                    PopUp.showInformation((gameScene.getModel().getWinner()==-1)? "match nul" : "Le vainqueur est le joueur :" +(gameScene.getModel().getWinner()) +
                            "\n" +" joueur 1 : " +  gameScene.getModel().getBlackScore()  +" pions noirs" +
                            "\n" +" joueur 2 : " + gameScene.getModel().getWhiteScore() + " pions blancs" );
                }
            }
    }
    /**
     * Creates a PauseTransition to let the human player see the computer player's move
     *
     * @return The PauseTransition object.
     */
    private PauseTransition getPauseTransition() {
        PauseTransition pauseTransition ;
        if(!gameScene.getModel().getState().equals(State.GAME_OVER))
        {
            pauseTransition = new PauseTransition(Duration.seconds(0.40));
            pauseTransition.setOnFinished(event ->{
                if(gameScene.getModel().getState().equals(State.TURN_END))
                {
                    gameScene.getModel().nextPlayer();
                }
                if (gameScene.getModel().getState().equals(State.PUT)) {
                    this.execute();
                }
                if(gameScene.getModel().getState().equals(State.GAME_OVER))
                {
                    PopUp.showInformation((gameScene.getModel().getWinner()==-1)? "match nul" : "Le vainqueur est le joueur :" +(gameScene.getModel().getWinner()) +
                            "\n" +" joueur 1 : " +  gameScene.getModel().getBlackScore()  +" pions noirs" +
                            "\n" +" joueur 2 : " + gameScene.getModel().getWhiteScore() + " pions blancs" );
                }
            });
        }

        else{
             pauseTransition = new PauseTransition(Duration.seconds(0));
                PopUp.showInformation((gameScene.getModel().getWinner()==-1)? "match nul" : "Le vainqueur est le joueur :" +(gameScene.getModel().getWinner()) +
                        "\n" +" joueur 1 : " +  gameScene.getModel().getBlackScore()  +" pions noirs" +
                        "\n" +" joueur 2 : " + gameScene.getModel().getWhiteScore() + " pions blancs" );

        }


        return pauseTransition;
    }

    /**
     * Undoes the last move made by the player.
     */
    @Override
    public void undo() {
        try {
            this.gameScene.getModel().setUndo(true);
            this.gameScene.getModel().undo(true);
            this.execute();
        }
        catch (Exception ignored)
        {
        }
    }

    /**
     * Redoes the last move made by the player.
     */
    @Override
    public void redo() {
        try {
            gameScene.getModel().setRedo(true);
            gameScene.getModel().redoJavaFx();
            this.execute();
        }
        catch (Exception ignored)
        {
        }
    }
}
