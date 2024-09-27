package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.view_javaFx.GameScene;

import java.util.Objects;
/**
 * The RedoCommand class represents a command that handles the redo action in the Othello game.
 * It implements the Command interface.
 */
public class RedoCommand implements Command{
    private final GameScene gameScene;
    private final OnPutPawn putPawn;
    /**
     * Constructs a RedoCommand object with the specified GameScene.
     *
     * @param gameScene The GameScene where the redo action is performed.
     */
    public RedoCommand(GameScene gameScene) {
        this.gameScene = Objects.requireNonNull(gameScene,"gameScene requis");
        putPawn = new OnPutPawn(gameScene);

    }
    /**
     * Executes the redo action when the redo button is clicked.
     */
    @Override
    public void execute()
    {
        this.gameScene.getGameView().getActionGame().getRedo().setOnAction(e -> putPawn.redo()
        );
    }



    @Override
    public void undo() {
    }

    @Override
    public void redo() {

    }
}
