package g55870.atl.othello.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void testToStringWhite() {
        Pawn pion = new Pawn(new Position(4,4),Color.WHITE);
        assertEquals("O",pion.toString());
    }
    @Test
    public void testToStringBlack() {
        Pawn pion = new Pawn(new Position(4,4),Color.BLACK);
        assertEquals("\u001B[37mO\u001B[0m",pion.toString());
    }


    @Test
    public void flipBlackToWhite() {
        Pawn pion = new Pawn(new Position(4,4),Color.BLACK);
        pion.flipPawn();
        assertEquals(Color.WHITE,pion.getColor());
    }
    @Test
    public void flipWhiteToBlack() {
        Pawn pion = new Pawn(new Position(4,4),Color.WHITE);
        pion.flipPawn();
        assertEquals(Color.BLACK,pion.getColor());
    }
    @Test
    public void flipNone() {
        Pawn pion = new Pawn(new Position(4,4),Color.NONE);
        pion.flipPawn();
        assertNull(pion.getColor());
    }
    @Test
    public void testEquals() {
        Pawn pion = new Pawn(new Position(4,4),Color.NONE);
        Pawn pion2 = new Pawn(new Position(4,4),Color.NONE);
        assertEquals(pion, pion2);
    }
    @Test
    public void testNotEqualsPosition() {
        Pawn pion = new Pawn(new Position(4,1),Color.NONE);
        Pawn pion2 = new Pawn(new Position(4,4),Color.NONE);
        assertNotEquals(pion, pion2);
    }
    @Test
    public void testNotEqualsColor() {
        Pawn pion = new Pawn(new Position(4,4),Color.NONE);
        Pawn pion2 = new Pawn(new Position(4,4),Color.BLACK);
        assertNotEquals(pion, pion2);
    }
    @Test
    public void getPosition() {
        Pawn pion = new Pawn(new Position(4,4),Color.NONE);
        assertEquals(new Position(4,4), pion.getPosition());
    }

    @Test
    public void getColor() {
        Pawn pion = new Pawn(new Position(4,4),Color.BLACK);
        assertEquals(Color.BLACK, pion.getColor());
        pion = new Pawn(new Position(4,4),Color.WHITE);
        assertEquals(Color.WHITE, pion.getColor());
        pion = new Pawn(new Position(4,4),Color.NONE);
        assertEquals(Color.NONE, pion.getColor());

    }
}