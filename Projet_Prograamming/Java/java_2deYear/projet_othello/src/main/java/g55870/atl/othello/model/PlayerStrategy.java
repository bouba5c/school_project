package g55870.atl.othello.model;

import java.util.List;

/**
 * Represents a strategy for an Othello player.
 */
public interface PlayerStrategy {
    /**
     * Returns the ID of the player
     * @return the player ID
     */
    int id();
    /**
     * Returns the color of the player
     *
     * @return the player color
     */
    Color color();

    /**
     * Chooses a move from the given list of positions based on the current game state and board configuration.
     *
     * @param list     the list of positions representing possible moves
     * @param currentColor the current player's color
     * @param enemyColor   the opponent's color
     * @param board       the game board
     * @return the chosen position to place a pawn
     */
    Position chooseMove(List<Position> list, Color currentColor, Color enemyColor, Board board);
}