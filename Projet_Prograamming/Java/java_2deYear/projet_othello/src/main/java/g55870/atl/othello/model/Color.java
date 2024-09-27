package g55870.atl.othello.model;
/**
 * Enum representing the colors of pawns in Othello.
 */
public enum Color {
    BLACK("\u001B[37mO\u001B[0m"),
    WHITE("O"),

    NONE("\u001B[36m°\u001B[0m");

    private final String label;
    /**
     * Constructor for Color enum.
     *
     * @param label The label representing the color.
     * @throws IllegalArgumentException If the label is empty.
     */
    Color(String label)
    {
        if(label.isEmpty())
        {
            throw new IllegalArgumentException("label requis");
        }
        this.label = label;
    }

    /**
     * Returns the label representing the color.
     * @return The label representing the color.
     */
    @Override
    public String toString() {
        return label;
    }
}
