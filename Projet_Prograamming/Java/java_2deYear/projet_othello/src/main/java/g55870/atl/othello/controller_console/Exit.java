package g55870.atl.othello.controller_console;
/**
 * Command implementation for exiting the game.
 */
public class Exit implements Command{
    /**
     * Constructs a new Exit command.
     */
    public Exit()
    {
    }
    /**
     * Executes the exit command, terminating the program.
     */
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {

    }
}
