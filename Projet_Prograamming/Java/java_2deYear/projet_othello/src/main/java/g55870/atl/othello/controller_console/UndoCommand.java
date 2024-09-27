package g55870.atl.othello.controller_console;

import java.util.Objects;
/**
 * Command implementation for undoing the last command.
 */
public class UndoCommand implements Command{
    private final CommandHistory commandHistory;
    /**
     * Constructs a new UndoCommand instance.
     *
     * @param commandHistory The command history to track and manage executed commands.
     * @throws NullPointerException when command history is null
     */
    public UndoCommand(CommandHistory commandHistory) {
        this.commandHistory = Objects.requireNonNull(commandHistory,"Command history requis");
    }
    /**
     * Executes the undo command by undoing the last executed command in the command history.
     */
    @Override
    public void execute() {
        commandHistory.undo();
    }
    /**
     * Undoing the undo command is equivalent to executing it again.
     */
    @Override
    public void undo() {
        this.execute();
    }

    /**
     * Redoing the undo command is not supported.
     */
    @Override
    public void redo() {
        commandHistory.redo();

    }
}
