package g55870.luckynumbers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    private Tile tile;

    @BeforeEach     // Exécutée avant chaque test
    public void setUp() {
        tile = new Tile(4);
    }

    /* === Tests for flipFaceUp === */
    @Test
    public void flipFaceUpTrue_when_theTile_isNotVisible() {
        tile.flipFaceUp();
        assertTrue(tile.isFaceUp());
    }

    @Test
    public void flipFaceUpTrue_when_theTile_AlreadyVisible() {
        tile.flipFaceUp();
        tile.flipFaceUp();
        assertTrue(tile.isFaceUp());
    }
}
