package g55870.atl.othello.model;

import java.util.List;
import java.util.Objects;

/**
 *  * Represents a human player in an Othello game.
*/
public record Player(int id, Color color) implements PlayerStrategy {
    /**
     * Constructs a new PlayerIA object with the specified ID and color.
     *
     * @param id    the ID of the player
     * @param color the color of the player's pawns
     * @throws IllegalArgumentException if the ID is not between 0 and 2
     * @throws NullPointerException     if the color is null
     */
    public Player(int id, Color color) {
        if (id < 0 || id > 2) {
            throw new IllegalArgumentException("id must be between 0 and 2");
        }
        this.color = Objects.requireNonNull(color, "color requis");
        this.id = id;
    }
    /**
     * Returns null because the player does not implement any specific strategy for choosing moves.

     * @param liste      the list of positions representing possible moves
     * @param currentColor the current player's color
     * @param enemyColor   the opponent's color
     * @param board       the game board
     * @return null
     */
    @Override
    public Position chooseMove(List<Position> liste, Color currentColor,Color enemyColor, Board board) {
        return null;

    }

}
