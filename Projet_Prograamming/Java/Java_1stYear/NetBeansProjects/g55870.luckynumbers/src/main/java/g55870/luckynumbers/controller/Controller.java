package g55870.luckynumbers.controller;

import g55870.luckynumbers.model.Model;
import g55870.luckynumbers.model.Position;
import g55870.luckynumbers.view.View;

public class Controller {

    private final Model model;
    private final View view;

    /**
     * The constructor of the class Controller
     *
     * @param model : the model of the game which contains the rules/the game
     * operation and the state.
     * @param myView : the view of the game
     */
    public Controller(Model model, View myView) {
        this.model = model;
        this.view = myView;
    }

    /**
     * The play method will run the game from start until the game over. It s
     * driven by the state of the game. If the game is over he will restart a
     * new game
     */
    public void play() {
        view.displayWelcome();
        while (true) {
            switch (model.getState()) {
                case NOT_STARTED:
                    int playerCount = view.askPlayerCount();
                    model.start(playerCount);
                    System.out.println("Il y a " + model.getPlayerCount() + " joueurs");
                    System.out.println("-----------------");
                    break;
                case PICK_TILE:
                    try {
                    view.askTile();
                } catch (IllegalArgumentException e) {
                    view.displayError("La tuile est inexistante ou elle n'est pas visible  ");
                }
                view.displayGame();
                break;
                case PLACE_TILE:
                    putInBoard();
                    break;
                case PLACE_OR_DROP_TILE:
                    var choice = view.askDropOrPlace();
                    switch (choice) {
                        case 'p':
                            putInBoard();
                            break;
                        case 'r':
                            this.model.dropTile();
                            break;
                        default:
                            view.displayError("Commande Invalide : veuillez entrer 'r' pour replacer une tuile face visible dans la pioche");
                            view.displayError("Commande Invalide : veuillez entrer 'p' pour placer la tuile dans le plateau  ");
                            break;
                    }
                    break;
                case TURN_END:
                    model.nextPlayer();
                    break;
                case GAME_OVER:
                    view.displayWinner();
                    playerCount = view.askPlayerCount();
                    model.start(playerCount);
                    break;
            }
        }
    }

    /**
     * Will put the tile in the current player's board according to the rules of
     * the game
     */
    private void putInBoard() {
        Position pos = view.askPosition();
        try {
            if (model.canTileBePut(pos)) {
                model.putTile(pos);
            } else {
                view.displayError("Veuillez respecter les régles : la tuile ne peut pas etre placé ici.");
            }
        } catch (IllegalArgumentException e) {
            view.displayError("La position est en dehors du plateau :");
        }
        System.out.println("");
    }

}
