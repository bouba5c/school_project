package g55870.luckynumbers.view;

import g55870.luckynumbers.model.Position;
import g55870.luckynumbers.model.Tile;

/**
 * Interface for the MyView view.
 */
public interface View {

    /**
     * Display to the players : The game's name,the author's name and the
     * version
     */
    void displayWelcome();

    /**
     * display the board of the current player and the tile to place for more
     * information go to displayBoard()
     */
    void displayGame();

    /**
     * Display the winner of the game.
     */
    void displayWinner();

    /**
     * Will ask to a user how many player will play between 2 and 4.
     *
     * @return the number of player between 2 and 4.
     */
    int askPlayerCount();

    /**
     * Will ask a position to the current player.
     *
     * @return The given position.
     */
    Position askPosition();

    /**
     * Display a error message in the terminal when it doesn't respect the rule
     * of the game or if the paramater of the player is incorrect.
     *
     * @param message the error message
     */
    void displayError(String message);

    /**
     * Will ask to a user what kind of tile he wants between faceDownTile or
     * faceUpTile.
     *
     * @return the tile.
     */
    Tile askTile();

    /**
     * Will ask to the user if he wants to drop or place the tile.
     *
     * @return the option character.
     */
    char askDropOrPlace();

}
