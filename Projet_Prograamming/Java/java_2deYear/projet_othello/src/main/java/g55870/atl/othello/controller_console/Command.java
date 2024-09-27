package g55870.atl.othello.controller_console;

/**
 * Interface for defining commands in the console-based Othello game.
 */
public interface Command {
    /**
     * Executes the command.
     */
    void execute();

    /**
     * Undoes the command.
     */
    void undo();

    /**
     * Redoes the command.
     */
    void redo();
}
