package g55870.atl.othello.controller_console;

import g55870.atl.othello.model.Model;
import java.util.Objects;

/**
 * Command implementation for surrendering the game.
 */
public class Surrender implements Command{
    private final Model model;
    /**
     * Constructs a new Surrender command.
     *
     * @param model The game model.
     * @throws NullPointerException when model is null
     */
    public Surrender(Model model) {
        this.model = Objects.requireNonNull(model,"model requis");
    }
    /**
     * Executes the surrender command, when the current player will surrender.
     */
    @Override
    public void execute() {
        this.model.surrender();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo()
    {
    }
}
