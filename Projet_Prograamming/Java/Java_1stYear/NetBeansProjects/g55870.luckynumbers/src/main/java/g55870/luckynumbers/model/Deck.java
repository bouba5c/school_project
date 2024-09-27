package g55870.luckynumbers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author balde boubacar <baldeboubacar11 at gmail.com>
 */
public class Deck {

    private final List<Tile> faceUpTiles;
    private final List<Tile> faceDownTiles;

    /**
     * Constructor of the deck class Will create a initial deck as stated in the
     * rules.
     *
     * @param playerCount number of player
     */
    public Deck(int playerCount) {
        this.faceDownTiles = new ArrayList<>();
        this.faceUpTiles = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            for (int j = 1; j <= 20; j++) {
                this.faceDownTiles.add(new Tile(j));
            }
        }
        Collections.shuffle(this.faceDownTiles);
    }

    /**
     * Give the number of face down cards
     *
     * @return the size of the face down tile
     */
    public int faceDownCount() {
        return this.faceDownTiles.size();
    }

    public Tile pickFaceDown() {
        Tile tile = this.faceDownTiles.get(faceDownCount() - 1);
        this.faceDownTiles.remove(faceDownCount() - 1);
        tile.flipFaceUp();
        this.faceUpTiles.add(tile);
        return tile;
    }

    /**
     * Give the number of face up cards
     *
     * @return the size of the face up tile
     */
    public int faceUpCount() {
        return this.faceUpTiles.size();
    }

    /**
     * the list of the face up tiles
     *
     * @return the list of the face up tiles
     */
    public List<Tile> getAllFaceUp() {
        return this.faceUpTiles;
    }

    /**
     * the list of the face up tiles
     *
     * @return the list of the face up tiles
     */
    List<Tile> getAllFaceDown() {
        return this.faceDownTiles;
    }

    /**
     * Check if the tile is has face up .
     *
     * @param tile given tile of a player
     * @return true if the tile exist in the deck and if the tile is visible in
     * the deck false otherwise
     */
    public boolean hasFaceFaceUp(Tile tile) {
        for (Tile element : this.faceUpTiles) {
            if (element.getValue() == tile.getValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Will remove the tile in the deck
     *
     * @param tile given tile of a player
     */
    public void pickFaceUp(Tile tile) {
        if (hasFaceFaceUp(tile)) {
            for (Tile element : this.faceUpTiles) {
                if (element.getValue() == tile.getValue()) {
                    tile = element;
                }
            }
            this.faceUpTiles.remove(tile);
        }
    }

    /**
     * Will put back the tile in the deck
     *
     * @param tile given tile of a player
     */
    public void putBack(Tile tile) {
        if (faceUpCount() > 0) {
            this.faceUpTiles.remove(faceUpCount() - 1);
        }
        this.faceUpTiles.add(tile);
    }
}
