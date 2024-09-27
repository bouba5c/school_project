package g55870.atl.othello.controller_console;

import g55870.atl.othello.model.Model;

import java.util.Objects;

public class StartCommand implements Command
{
    private final Model model;
    private final int gameMode;
    private final int size;
    public StartCommand(Model model,int gameMode,int size) {
        this.model = Objects.requireNonNull(model,"model requis");
        this.gameMode = gameMode;
        this.size = size;
    }

    @Override
    public void execute() {
        this.model.start(gameMode,size);
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
