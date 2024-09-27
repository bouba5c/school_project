package g55870.atl.othello.controller_console;

import g55870.atl.othello.model.Model;
import g55870.atl.othello.model.Position;
import g55870.atl.othello.model.State;
import g55870.atl.othello.view_console.View;

/**
 * Class to build commands based on user input for the console-based Othello game.
 */
public class CommandManagement {
    /**
     * Builds a command based on the user input.
     *
     * @param request         the user input
     * @param model           the Othello game model
     * @param view            the Othello game view
     * @param commandHistory  the command history
     * @return the constructed command
     * @throws IllegalArgumentException if the input request is invalid or incomplete
     */
    static Command build(String request,Model model,View view ,CommandHistory commandHistory)
    {
        Command cmd  ;
        String[] tokens = request.split(" ");
        String first = tokens[0];
        switch (first) {
            case "start":
                if(tokens.length != 3)
                {
                    throw new IllegalArgumentException("veuillez tapper : start {1=jouer en local / 2= computer random /3= ia} {taille plateau} ");
                }
                int gameMode = Integer.parseInt(tokens[1]);
                int size = Integer.parseInt(tokens[2]);
                cmd = new StartCommand(model, gameMode, size);
                break;
            case "put":
                if(tokens.length !=3)
                {
                    throw new IllegalArgumentException("veuillez tapper : put {x} {y} ");

                }
                if(model.getState().equals(State.NOT_STARTED) ||model.getState().equals(State.GAME_OVER) )
                {
                    throw new IllegalArgumentException(" vous ne pouvez utiliser cette commande : veuillez lancer une partie ");
                }
                int x = Integer.parseInt(tokens[1]);
                int y = Integer.parseInt(tokens[2]);
                cmd = new PutCommand(model, new Position(x, y), model.getCurrentPlayerColor());
                break;
            case  "help":
                if(tokens.length !=1)
                {
                    throw new IllegalArgumentException("veuillez tapper : help ");
                }

                cmd = new HelpCommand(view);
                break;
            case "surrender":
                if(tokens.length !=1)
                {
                    throw new IllegalArgumentException("veuillez tapper : surrender ");
                }
                if(model.getState().equals(State.NOT_STARTED) ||model.getState().equals(State.GAME_OVER) )
                {
                    throw new IllegalArgumentException(" vous ne pouvez utiliser cette commande : veuillez lancer une partie ");
                }
                cmd = new Surrender(model);
                break;
            case "exit":
                if(tokens.length !=1)
                {
                    throw new IllegalArgumentException("veuillez tapper : exit ");
                }
                cmd = new Exit();
                break;
            case "listCommands":
                if(tokens.length !=1)
                {
                    throw new IllegalArgumentException(" veuillez tapper : listCommands");
                }
                if(model.getState().equals(State.NOT_STARTED))
                {
                    throw new IllegalArgumentException(" vous avez deja les commandes ci dessus ");

                }
                cmd = new ListCommands(view);
                break;
            case "undo":
                if(tokens.length !=1)
                {
                    throw new IllegalArgumentException("veuillez tapper : undo");
                }
                if(model.getState().equals(State.NOT_STARTED) ||model.getState().equals(State.GAME_OVER) )
                {
                    throw new IllegalArgumentException(" vous ne pouvez utiliser cette commande : veuillez lancer une partie ");
                }
                cmd = new UndoCommand(commandHistory);
                break;
            case "redo":
                if(tokens.length !=1)
                {
                    throw new IllegalArgumentException("veuillez tapper : redo");
                }
                if(model.getState().equals(State.NOT_STARTED) ||model.getState().equals(State.GAME_OVER) )
                {
                    throw new IllegalArgumentException(" vous ne pouvez utiliser cette commande : veuillez lancer une partie ");
                }
                cmd = new RedoCommand(commandHistory);
                break;
            default:
                throw new IllegalArgumentException("Invalid command ! ");
        }
        return cmd;
    }
}
