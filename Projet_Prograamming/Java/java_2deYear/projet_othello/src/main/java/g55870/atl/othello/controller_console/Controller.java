package g55870.atl.othello.controller_console;

import g55870.atl.othello.model.*;
import g55870.atl.othello.view_console.View;

import java.util.Objects;

public class Controller {
    private final Model model;
    private final CommandHistory history;
    private final View view;

    /**
     * Constructs a new Controller with the specified model and view.
     *
     * @param model the Othello game model
     * @param view  the Othello game view
     * @throws NullPointerException if the model or view is null
     */
    public Controller(Model model, View view) {
        this.model = Objects.requireNonNull(model,"model requis");
        this.view = Objects.requireNonNull(view,"view requis");
         history= new CommandHistory();
    }

    /**
     * Starts the console-based Othello game.
     */
    public void start()
    {
        view.displayWelcome();
        String request = "";
        Command command;
        while (!request.equals("exit"))
        {

            try
            {
                switch (model.getState())
                {
                    case NOT_STARTED, PUT:
                        request = view.askCommand();
                        command = CommandManagement.build(request, model, view,history);
                        command.execute();
                        history.add(command);
                        view.displayGame();
                        break;
                    case TURN_END:
                        model.nextPlayer();
                        model.update();
                        view.displayGame();
                        break;
                    case UNDO:
                        model.undo(false);
                        model.update();
                        view.displayGame();
                        break;
                    case GAME_OVER:
                        view.displayWinner();
                        view.displayScore();
                        request = view.askCommand();
                        command = CommandManagement.build(request, model, view,history);
                        command.execute();
                        view.displayGame();
                        break;
                }
            }
            catch (Exception e)
            {
                view.displayError(e.getMessage());
            }
        }
    }
}
