package g55870.luckynumbers.model;

public class Tile {

    private final int value;
    private boolean faceUp;

    /**
     * The constructor the class Tile
     *
     * @param value : the value of the tile.
     */
    public Tile(int value) {
        this.value = value;
    }

    /**
     * this method is a getter of the tile
     *
     * @return the value of a tile
     */
    public int getValue() {
        return this.value;
    }

    /**
     * this method is a boolean getter of the tile.
     *
     * @return true if the tile is face up and false otherwise ;
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Flip the tile face up in order to make it visible
     */
    void flipFaceUp() {
        if (!isFaceUp()) {
            this.faceUp = true;
        }
    }
}
