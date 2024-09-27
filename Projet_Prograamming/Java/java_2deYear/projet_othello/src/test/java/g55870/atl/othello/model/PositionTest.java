package g55870.atl.othello.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private final Position testPos1 = new Position(4,4);
    private final Position testPos2 = new Position(5,1);
    private final Position testPos3 = new Position(10,4);
    private final Position sameAsPos1 = new Position(4,4);

    @Test
    public void testEquals() {
        assertEquals(testPos1, sameAsPos1);
        assertNotEquals(testPos1, testPos2);
    }

    @Test
    public void getX() {
        int xPos1 = 4;
        int xPos2 = 5;
        int xPos3 = 10;
        assertEquals(xPos1,testPos1.x());
        assertEquals(xPos2,testPos2.x());
        assertEquals(xPos3,testPos3.x());

    }

    @Test
    public void getY() {
        int yPos1 = 4;
        int yPos2 = 1;
        int yPos3 = 4;
        assertEquals(yPos1,testPos1.y());
        assertEquals(yPos2,testPos2.y());
        assertEquals(yPos3,testPos3.y());
    }
}