package g55870.atl.othello.model;

import java.util.Objects;
/**
 * Represents a pawn in an Othello game.
 */
public class Pawn {
    private Color color;
    private final Position position;

    /**
     * Constructs a new Pawn object with the specified position and color.
     *
     * @param pos   the position of the pawn on the game board
     * @param color the color of the pawn
     * @throws NullPointerException if the position or color is null
     */
    public Pawn(Position pos, Color color)
    {
        this.color = Objects.requireNonNull(color,"color requis");
        this.position = Objects.requireNonNull(pos,"position requis");
    }

    /**
     * The representation of the pawn's color.
     * @return a string representation of the pawn's color
     */
    @Override
    public String toString() {
        return color.toString();
    }
    /**
     * Flips the color of the pawn.
     * If the pawn is black, it becomes white. If it's white, it becomes black.
     * otherwise return null
     */
    public void flipPawn() {
        if(color.equals(Color.BLACK))
        {
            this.color = Color.WHITE;
        }
        else if(color.equals(Color.WHITE))
        {
            this.color = Color.BLACK;
        }
        else {
            this.color=null;

        }


    }
    /**
     * Checks if this pawn is equal to another object.
     * Two pawns are considered equal if they have the same color and position.
     *
     * @param o the object to compare to
     * @return true if the pawns are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pion = (Pawn) o;
        return color == pion.color && Objects.equals(position, pion.position);
    }

    /**
     * gets the position of the pawn
     * @return the position of the pawn
     */
    public Position getPosition() {
        return position;
    }
    /**
     * gets the color of the pawn
     * @return the position of the pawn
     */
    public Color getColor() {
        return color;
    }
}
