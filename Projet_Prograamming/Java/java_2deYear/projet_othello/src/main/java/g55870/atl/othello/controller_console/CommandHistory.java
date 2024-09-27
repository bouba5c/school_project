package g55870.atl.othello.controller_console;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Class to manage command history in the console-based Othello game.
 */
public class CommandHistory {
    private final LinkedList<Command> undoHistory;
    private final LinkedList<Command> redoHistory;

    /**
     * Constructs a CommandHistory object.
     */
    public CommandHistory() {
        this.undoHistory = new LinkedList<>();
        this.redoHistory = new LinkedList<>();
    }

    /**
     * Adds a command to the undo history.
     * @param cmd the command to add
     * @throws NullPointerException if param is null
     */
    public void add(Command cmd) {
        Objects.requireNonNull(cmd, "command requis");
        undoHistory.addFirst(cmd);
    }

    /**
     * Undoes the last command.
     */
    public void undo() {
        if (!undoHistory.isEmpty()) {
            Command command = undoHistory.removeFirst();
            command.undo();
            redoHistory.addFirst(command);
        }
    }

    /**
     * Redoes the last undone command.
     */
    public void redo() {
        if (!redoHistory.isEmpty()) {
            Command command = redoHistory.removeFirst();
            command.redo();
            undoHistory.addFirst(command);
        }
    }
}
