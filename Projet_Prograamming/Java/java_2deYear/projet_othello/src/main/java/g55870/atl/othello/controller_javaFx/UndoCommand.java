package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.view_javaFx.GameScene;

import java.util.Objects;
/**
 * The UndoCommand class represents a command that handles the undo action in the Othello game.
 * It implements the Command interface.
 */
public class UndoCommand implements Command
{
    private final GameScene gameScene;
    private final OnPutPawn putPawn ;
    /**
     * Constructs a UndoCommand object with the specified GameScene.
     *
     * @param gameScene The GameScene where the redo action is performed.
     */
    public UndoCommand(GameScene gameScene) {
        this.gameScene = Objects.requireNonNull(gameScene,"gameScene requis");
         putPawn = new OnPutPawn(gameScene);

    }
    /**
     * Executes the undo action when the undo button is clicked.
     */
    @Override
    public void execute() {

        this.gameScene.getGameView().getActionGame().getUndo().setOnAction(event -> putPawn.undo());
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo()
    {
    }
}
