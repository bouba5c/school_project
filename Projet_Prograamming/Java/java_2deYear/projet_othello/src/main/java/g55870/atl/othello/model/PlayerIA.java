package g55870.atl.othello.model;

import java.util.List;
import java.util.Objects;
import java.util.Random;
/**
 * Represents an AI player strategy for an Othello game.
 */
public record PlayerIA(int id, Color color) implements PlayerStrategy {

    /**
     * Constructs a new PlayerIA object with the specified ID and color.
     *
     * @param id    the ID of the player
     * @param color the color of the player's pawns
     * @throws IllegalArgumentException if the ID is not between 0 and 2 (inclusive)
     * @throws NullPointerException     if the color is null
     */
    public PlayerIA(int id, Color color) {
        if (id < 0 || id > 2) {
            throw new IllegalArgumentException("id must be between 0 and 2");
        }
        this.id = id;
        this.color = Objects.requireNonNull(color, "color requis");
    }
    /**
     * Chooses a move based on the maximum number of opponent  pawn.
     * If it's the same count it will take  a random position
     *
     * @param list      the list of positions representing possible moves of the player
     * @param currentColor the current player's color
     * @param enemyColor   the opponent's color
     * @param board       the game board
     * @return the chosen position of the possibleMoves list
     */
    @Override
    public Position chooseMove(List<Position> list, Color currentColor,Color enemyColor, Board board) {
        Position posMax = null;
        Random random = new Random();
        boolean same = false;
        if (!list.isEmpty()) {
            posMax = list.get(0);

            for (int i = 1; i < list.size(); i++) {
                Position currentPos = list.get(i);
                if (board.countOpponent(currentPos,currentColor,enemyColor) == board.countOpponent(posMax,currentColor,enemyColor)) {
                    same = true;
                }
                if (board.countOpponent(currentPos,currentColor,enemyColor) > board.countOpponent(posMax,currentColor,enemyColor)) {
                    posMax = currentPos;
                    same = false;
                }
            }
            if (same) {
                int randomIndex = random.nextInt(list.size());
                return list.get(randomIndex);
            }
        }

        return posMax;
    }
}
