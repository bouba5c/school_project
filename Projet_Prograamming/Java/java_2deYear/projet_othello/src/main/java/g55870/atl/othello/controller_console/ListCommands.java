package g55870.atl.othello.controller_console;

import g55870.atl.othello.view_console.View;

import java.util.Objects;

/**
 * Command implementation for listing available commands.
 */
public class ListCommands implements Command{
    private final View view;
    /**
     * Constructs a new ListCommands instance.
     *
     * @param view The view where the commands will be displayed.
     * @throws NullPointerException when view is null
     */
    public ListCommands(View view) {
        this.view = Objects.requireNonNull(view,"view requis");
    }
    /**
     * Executes the list commands , displaying available commands.
     */
    @Override
    public void execute() {
        view.displayCommands();
    }

    @Override
    public void undo() {
    }
    @Override
    public void redo() {

    }
}
