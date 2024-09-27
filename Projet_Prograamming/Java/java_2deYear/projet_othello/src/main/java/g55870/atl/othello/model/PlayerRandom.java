package g55870.atl.othello.model;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a random player strategy for an Othello game.
 * This player strategy selects a move randomly from the list of possible moves.
 */
public record PlayerRandom(int id, Color color) implements PlayerStrategy {
    /**
     * Constructs a new PlayerRandom object with the specified ID and color.
     *
     * @param id    the ID of the player
     * @param color the color of the player's pawns
     * @throws IllegalArgumentException if the ID is not between 0 and 2
     * @throws NullPointerException     if the color is null
     */
    public PlayerRandom(int id, Color color) {
        if (id < 0 || id > 2) {
            throw new IllegalArgumentException("id must be between 0 and 2");
        }
        this.id = id;
        this.color = Objects.requireNonNull(color, "color requis");
    }
    /**
     * Chooses a move randomly from the given list of positions.
     * @param list      the list of possible moves position
     * @param currentColor the current player's color
     * @param enemyColor   the opponent's color
     * @param board       the game board
     * @return the chosen position of the possibleMoves list
     */
    @Override
    public Position chooseMove(List<Position> list, Color currentColor,Color enemyColor, Board board) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
