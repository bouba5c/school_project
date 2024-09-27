package g55870.atl.othello.controller_javaFx;

import g55870.atl.othello.view_javaFx.PopUp;

import java.util.Objects;
/**
 * The SurrenderCommand class represents a command that handles the surrender action in the Othello game.
 * It implements the Command interface.
 */
public class SurrenderCommand implements Command{
    private final Controller controller;
    /**
     * Constructs a SurrenderCommand object with the specified Controller.
     *
     * @param controller The Controller instance managing the game.
     */
    public SurrenderCommand(Controller controller) {

        this.controller = Objects.requireNonNull(controller,"controller requis");
    }

    /**
     * Executes the surrender action when the surrender button is clicked.
     */
    @Override
    public void execute() {
        this.controller.getGame().getGameScene().getGameView().getActionGame().getSurrender().setOnAction(actionEvent -> {
            this.controller.getModel().surrender();
            PopUp.showInformation((controller.getModel().getWinner()==-1)? "match nul" : "Le vainqueur est le joueur :" +(controller.getModel().getWinner()) +
                    "\n" +" joueur 1 : " +  controller.getModel().getBlackScore()  +" pions noirs" +
                    "\n" +" joueur 2 : " + controller.getModel().getWhiteScore() + " pions blancs" );
        });
    }
    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
