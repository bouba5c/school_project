package g55870.atl.othello.controller_console;

import g55870.atl.othello.view_console.View;

import java.util.Objects;
/**
 * Command implementation for displaying help information.
 */
public class HelpCommand implements Command{
    private final View view;
    /**
     * Constructs a new HelpCommand.
     *
     * @param view The view where the help information will be displayed.
     */
    public HelpCommand(View view)
    {
        this.view = Objects.requireNonNull(view,"view requis");
    }
    /**
     * Executes the help command, displays help information.
     */
    @Override
    public void execute() {
        this.view.displayHelp();
    }

    @Override
    public void undo()
    {
    }
    @Override
    public void redo()
    {


    }
}
