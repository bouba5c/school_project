package g55870.atl.othello.controller_console;

import g55870.atl.othello.model.*;

import java.util.Objects;
/**
 * Command implementation for putting a pawn on the board.
 */
public class PutCommand implements Command{
    private final Model model;
    private final Position pos;
    private final Color color;
    /**
     * Constructs a new PutCommand instance.
     *
     * @param model The model where the pawn will be put.
     * @param pos   The position where the pawn will be put.
     * @param color The color of the pawn to be put.
     */
    public PutCommand(Model model , Position pos, Color color)
    {
        this.model = Objects.requireNonNull(model,"model requis");
        this.pos = Objects.requireNonNull(pos,"position requis");
        this.color = Objects.requireNonNull(color,"color requis");
    }
    /**
     * Executes the put command by putting a pawn on the board.
     */
    @Override
    public void execute() {
        Pawn pion = new Pawn(pos,color);
        this.model.putPawn(pion);
    }
    /**
     * undoing the put command will remove the pawn and flip opponent pawns
     */
    @Override
    public void undo()
    {
        this.model.setUndo(true);

    }
    /**
     * Redoing the put command is equivalent to executing it again.
     */
    @Override
    public void redo()
    {
        this.execute();
    }
}
