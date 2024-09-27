
package g55870.luckynumbers.model;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author balde boubacar <baldeboubacar11 at gmail.com>
 */
public class DeckTest {

    private Deck deck;
    private List<Tile> faceDownTile;
    private List<Tile> faceUpTiles;

    @BeforeEach
    public void setUp() {
        deck = new Deck(1);
        faceDownTile = deck.getAllFaceDown();
        faceUpTiles = deck.getAllFaceUp();
    }

    /* === Test for face down count === */
    @Test
    public void faceDownCount_initalDeck() {
        faceDownTile = deck.getAllFaceDown();
        assertEquals(20, deck.faceDownCount());
    }

    @Test
    public void faceDownCount_when_pickedFaceDown_2times() {
        int actual = deck.pickFaceDown().getValue();
        assertEquals(19, deck.faceDownCount());

    }

    @Test
    public void faceDownCount_when_pickedFaceDown_10time() {
        int actual = 0;
        for (int i = 0; i < 10; i++) {
            actual = deck.pickFaceDown().getValue();
        }
        assertEquals(10, deck.faceDownCount());
    }

    @Test
    public void faceDownCount_when_3_tile_isFaceUp() {
        deck.pickFaceDown();
        deck.pickFaceDown();
        deck.pickFaceDown();
        assertEquals(17, deck.faceDownCount());
    }

    /* === Test for pick Face Down === */
    @Test
    public void pickFaceDown_() {
        assertEquals(getFaceDown(deck.faceDownCount() - 1), deck.pickFaceDown().getValue());
    }

    /* === Test for face up count === */
    @Test
    public void faceUpCount_initalDeck() {
        assertEquals(0, deck.faceUpCount());
    }

    @Test
    public void faceUpCount_when_1_tile_isUp() {
        deck.pickFaceDown();
        assertEquals(1, deck.faceUpCount());
    }

    @Test
    public void faceUpCount_when_3_tile_isFaceUp() {
        deck.pickFaceDown();
        deck.pickFaceDown();
        deck.pickFaceDown();
        assertEquals(3, deck.faceUpCount());
    }

    /* === Test for get all face up === */
    @Test
    public void getAllfaceUp_when_init_Deck() {
        assertEquals(Collections.emptyList(), deck.getAllFaceUp());
    }

    /* === Test for hasFaceUp === */
    @Test
    public void hasFaceUp_False_when_is_0() {
        assertFalse(deck.hasFaceFaceUp(new Tile(0)));
    }

    /* === Test for hasFaceUp === */
    @Test
    public void hasFaceUp_False_when_is_21() {
        assertFalse(deck.hasFaceFaceUp(new Tile(21)));
    }

    @Test
    public void hasFaceUp_true_when_is_in_Deck() {
//        for (int i = 0; i < this.faceDownTile.size(); i++) {
//            System.out.println(this.faceDownTile.get(i).getValue());
//        }
        Tile tile = deck.pickFaceDown();
        assertTrue(deck.hasFaceFaceUp(tile));
    }

    /* === Test for pickFaceUp === */
    @Test
    public void pickFaceUp_when_1_tiles_are_faceUp() {
        Tile tile = deck.pickFaceDown();
        deck.pickFaceUp(tile); // remove the tile
        assertEquals(Collections.emptyList(), faceUpTiles);
    }

    @Test
    public void pickFaceUp_when_2_tiles_are_faceUp() {
        Tile tile = deck.pickFaceDown();
        Tile tile2 = deck.pickFaceDown();
        deck.pickFaceUp(tile);  //remove the tile1 
        assertEquals(getFaceUp(0), tile2.getValue());
    }

    @Test
    public void pickFaceUp_when_3_tiles_are_faceUp() {
        List<Tile> expected = this.faceUpTiles;

        Tile tile = deck.pickFaceDown();
        Tile tile2 = deck.pickFaceDown();
        Tile tile3 = deck.pickFaceDown();
//        for (int i = 0; i < expected.size(); i++) {
//            System.out.println("expectedBeforeTheRemove : " + expected.get(i).getValue());
//        }
        deck.pickFaceUp(tile2);  //remove the tile
//        for (int i = 0; i < expected.size(); i++) {
//            System.out.println("expected  : " + expected.get(i).getValue());
//        }
        List<Tile> actual = deck.getAllFaceUp();
//        for (int i = 0; i < actual.size(); i++) {
//            System.out.println("actual  : " + actual.get(i).getValue());
//        }
        assertEquals(expected, actual);
    }

    /* === Test for putBack === */
    @Test
    public void puckBack() {
        Tile tile = deck.pickFaceDown();
        deck.putBack(tile);
        assertEquals(tile.getValue(), getFaceUp(0));
    }

    int getFaceDown(int nb) {
        return this.faceDownTile.get(nb).getValue();

    }

    int getFaceUp(int nb) {
        return this.faceUpTiles.get(nb).getValue();

    }

}
