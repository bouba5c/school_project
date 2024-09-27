package g55870.atl.othello.controller_console;

import java.util.Objects;
/**
 * Command implementation for redoing a command from the command history.
 */
public class RedoCommand implements Command{
    private final CommandHistory commandHistory;
    /**
     * Constructs a new RedoCommand instance.
     *
     * @param commandHistory The command history to redo commands from.
     * @throws NullPointerException when command history is null
     */
    public RedoCommand(CommandHistory commandHistory) {
        this.commandHistory = Objects.requireNonNull(commandHistory,"Command history requis");
    }
    /**
     * Executes the redo command by redoing the last undone command in the command history.
     */
    @Override
    public void execute() {
        commandHistory.redo();
    }
    /**
     * Undoing the redo command is equivalent to undoing the command that was redone.
     */
    @Override
    public void undo() {
        commandHistory.undo();

    }
    /**
     * Redoing the redo command is equivalent to executing it again.
     */
    @Override
    public void redo() {
        this.execute();
    }
}
