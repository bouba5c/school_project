package g55870.atl.othello.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    final Board board;

    public BoardTest() {
        this.board = new Board(8);
    }

    void initBoard()
    {
        Position pos = new Position((board.getSize()/2)-1, (board.getSize()/2)-1);
        Position pos2 = new Position(board.getSize()/2, board.getSize()/2);
        Position pos3 = new Position(board.getSize()/2, (board.getSize()/2)-1);
        Position pos4 = new Position((board.getSize()/2)-1, board.getSize()/2);
        Pawn pion1 = new Pawn(pos, Color.WHITE);
        Pawn pion2 = new Pawn(pos2, Color.WHITE);
        Pawn pion3 = new Pawn(pos3, Color.BLACK);
        Pawn pion4 = new Pawn(pos4, Color.BLACK);
        board.put(pion1);
        board.put(pion2);
        board.put(pion3);
        board.put(pion4);
        board.put(pion4);
    }

    @Test
    public void getSize() {
        assertEquals(8,board.getSize());
    }

    @Test
    public void isFullWhenNotFull() {
        assertFalse(board.isFull());
    }
    @Test
    public void isFull() {
        for(int i  = 0 ; i < board.getSize() ; i++)
        {
            for(int j = 0 ; j< board.getSize(); j++)
            {
                board.put(new Pawn(new Position(i,j),Color.WHITE));
            }
        }
        assertTrue(board.isFull());
    }
    @Test
    public void isFullButContainingNone() {
        for(int i  = 0 ; i < board.getSize() ; i++)
        {
            for(int j = 0 ; j< board.getSize(); j++)
            {
                if(j%2==1)
                {
                    board.put(new Pawn(new Position(i,j),Color.WHITE));
                }
                else
                {
                    board.put(new Pawn(new Position(i,j),Color.BLACK));

                }
            }
        }
        board.put(new Pawn(new Position(3,4),Color.NONE));
        assertFalse(board.isFull());
    }
    @Test
    public void countOpponent()
    {
        initBoard();
        board.put(new Pawn(new Position(3,5),Color.BLACK));
        board.put(new Pawn(new Position(3,6),Color.BLACK));
        assertEquals(1,board.countOpponent(new Position(5,3),Color.WHITE,Color.BLACK)); //up
        assertEquals(3,board.countOpponent(new Position(3,7),Color.WHITE,Color.BLACK)); //left
        assertEquals(1,board.countOpponent(new Position(2,6),Color.WHITE,Color.BLACK));  //diagonal
        board.put(new Pawn(new Position(4,5),Color.BLACK));
        board.put(new Pawn(new Position(2,5),Color.BLACK));
        board.put(new Pawn(new Position(2,4),Color.WHITE));
        assertEquals(2,board.countOpponent(new Position(2,6),Color.WHITE,Color.BLACK)); //diagonal + left

    }
    @Test
    public void countOpponentWhenNoEnemy()
    {
        initBoard();
        board.put(new Pawn(new Position(3,5),Color.BLACK));
        board.put(new Pawn(new Position(3,6),Color.BLACK));
        assertEquals(0,board.countOpponent(new Position(2,3),Color.WHITE,Color.BLACK));
        assertEquals(0,board.countOpponent(new Position(4,5),Color.WHITE,Color.BLACK));
        assertEquals(0,board.countOpponent(new Position(0,0),Color.WHITE,Color.BLACK));
    }
    @Test
    public void put() {
        initBoard();
        Position pos = (new Position(3,5));
        board.put(new Pawn(pos,Color.BLACK));
        assertNotNull(board.getTile(pos));
    }

    @Test
    public void getTile()
    {
        Pawn expected = new Pawn(new Position(3,3),Color.BLACK);
        board.put(new Pawn(new Position(3,3),Color.BLACK));
        assertEquals(expected,board.getTile(new Position(3,3)));
    }
    @Test
    public void getTileWhenNull()
    {
        assertNull(board.getTile(new Position(3,3)));
    }
    @Test
    public void isInside() {
        Position pos = new Position(4,3);
        assertTrue(board.isInside(pos));
    }
    @Test
    public void isNotInside() {
        Position pos = new Position(board.getSize(), 3);
        Position pos2 = new Position(board.getSize(), board.getSize());
        Position pos3 = new Position(4, board.getSize());
        assertFalse(board.isInside(pos));
        assertFalse(board.isInside(pos2));
        assertFalse(board.isInside(pos3));
    }
    @Test
    public void flipPion()
    {
        Pawn pawn = new Pawn(new Position(1,1),Color.WHITE);
        pawn.flipPawn();
        assertEquals(Color.BLACK,pawn.getColor());
    }

    @Test
    public void getPossibleBlackMovesWhenInit() {
        initBoard();
        assertEquals(4,board.getPossibleBlackMoves().size());
    }
    @Test
    public void getPossibleBlackMovesWhenEmpty() {
        assertEquals(0,board.getPossibleBlackMoves().size());
    }
    @Test
    public void getPossibleWhiteMoves() {
        initBoard();
        assertEquals(4,board.getPossibleWhiteMoves().size());
    }

    @Test
    public void getPossibleWhiteMovesWhenNoWhiteMovesAvailable() {
        for(int i  = 0 ; i < board.getSize() ; i++)
        {
            for(int j = 0 ; j< board.getSize(); j++)
            {
                board.put(new Pawn(new Position(i,j),Color.WHITE));
            }
        }
        assertEquals(0,board.getPossibleWhiteMoves().size());
    }
    @Test
    public void getBlackPions() {
        board.put(new Pawn(new Position(1,1),Color.BLACK));
        board.put(new Pawn(new Position(1,2),Color.BLACK));
        board.put(new Pawn(new Position(1,3),Color.BLACK));
        board.put(new Pawn(new Position(1,4),Color.BLACK));
        board.put(new Pawn(new Position(2,1),Color.BLACK));
        board.put(new Pawn(new Position(2,2),Color.NONE));
        board.put(new Pawn(new Position(2,3),Color.WHITE));
        assertEquals(5,board.getBlackPawns().size());


    }
    @Test
    public void getBlackPionsWhenBoardEmpty() {
        assertEquals(0,board.getBlackPawns().size());
    }
    @Test
    public void getWhitePions() {
        board.put(new Pawn(new Position(1,1),Color.WHITE));
        board.put(new Pawn(new Position(1,2),Color.BLACK));
        board.put(new Pawn(new Position(1,3),Color.BLACK));
        board.put(new Pawn(new Position(1,4),Color.WHITE));
        board.put(new Pawn(new Position(2,1),Color.BLACK));
        board.put(new Pawn(new Position(2,2),Color.NONE));
        board.put(new Pawn(new Position(2,3),Color.WHITE));
        assertEquals(3,board.getBlackPawns().size());
    }

    @Test
    public void removePion() {
        Pawn pawn = new Pawn(new Position(1,1),Color.WHITE);
        board.put(pawn);
        assertEquals(new Position(1,1),board.getTile(pawn.getPosition()).getPosition());
        board.removePawn(pawn);
        assertNull(board.getTile(pawn.getPosition()));
    }

    @Test
    public void checkUpWhenInit() {
        initBoard();
        Pawn pawn = new Pawn(new Position(5,3),Color.WHITE);
        Pawn pawn2 = new Pawn(new Position(5,4),Color.BLACK);
        assertTrue(board.checkPossibleMoves(pawn));
        assertTrue(board.checkPossibleMoves(pawn2));
    }
    @Test
    public void checkUpWhenUpTileIsNull() {
        initBoard();
        Pawn pawn = new Pawn(new Position(6,3),Color.WHITE);
        Pawn pawn2 = new Pawn(new Position(7,3),Color.WHITE);
        Pawn pawn3 = new Pawn(new Position(6,4),Color.BLACK);
        Pawn pawn4 = new Pawn(new Position(7,4),Color.BLACK);
        assertFalse(board.checkPossibleMoves(pawn));
        assertFalse(board.checkPossibleMoves(pawn2));
        assertFalse(board.checkPossibleMoves(pawn3));
        assertFalse(board.checkPossibleMoves(pawn4));

    }

    @Test
    public void checkDownWhenInit() {
        initBoard();
        Pawn pawn = new Pawn(new Position(2,3),Color.BLACK);

        Pawn pawn2 = new Pawn(new Position(2,4),Color.WHITE);
        assertTrue(board.checkPossibleMoves(pawn));
        assertTrue(board.checkPossibleMoves(pawn2));
        assertFalse(board.checkPossibleMoves(new Pawn(new Position(2,3),Color.WHITE)));
        assertFalse(board.checkPossibleMoves(new Pawn(new Position(2,4),Color.BLACK)));

    }

    @Test
    public void checkLeftWhenInit() {
        initBoard();
        Pawn pawn = new Pawn(new Position(4,5),Color.BLACK);
        assertTrue(board.checkPossibleMoves(pawn));
    }
    @Test
    public void checkLeftWhenNullAtLeftTile() {
        initBoard();
        Pawn pawn = new Pawn(new Position(4,6),Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(4,7),Color.BLACK);
        assertFalse(board.checkPossibleMoves(pawn));
        assertFalse(board.checkPossibleMoves(pawn2));
    }
    @Test
    public void checkRightWhenInit()
    {
      initBoard();
        Pawn pawn = new Pawn(new Position(3,2),Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(4,2),Color.WHITE);
        assertTrue(board.checkPossibleMoves(pawn));
        assertTrue(board.checkPossibleMoves(pawn2));

    }
    @Test
    public void checkRightWhenNullAtRight()
    {
        initBoard();
        Pawn pawn = new Pawn(new Position(3,1),Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(3,0),Color.BLACK);
        Pawn pawn3 = new Pawn(new Position(3,5),Color.BLACK);
        assertFalse(board.checkPossibleMoves(pawn));
        assertFalse(board.checkPossibleMoves(pawn2));
        assertFalse(board.checkPossibleMoves(pawn3));
    }
    @Test
    public void checkRightWhenNull()
    {
    Pawn pawn = new Pawn(new Position(0,0),Color.BLACK);
    assertFalse(board.checkPossibleMoves(pawn));
    }

    @Test
    public void checkLeftCornerDownWhenInit() {
        initBoard();
        Pawn pawn = new Pawn(new Position(2,5),Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(2,5),Color.WHITE);
        assertFalse(board.checkPossibleMoves(pawn));
        assertFalse(board.checkPossibleMoves(pawn2));

    }

    @Test
    public void checkLeftCornerUPWhenInit() {
        initBoard();
        Pawn pawn = new Pawn(new Position(5,5),Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(5,5),Color.WHITE);
        assertFalse(board.checkPossibleMoves(pawn));
        assertFalse(board.checkPossibleMoves(pawn2));
    }

    @Test
    public void checkRightCornerDownWhenInit() {
        initBoard();
        Pawn pawn = new Pawn(new Position(2,2),Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(2,2),Color.WHITE);
        assertFalse(board.checkPossibleMoves(pawn));
        assertFalse(board.checkPossibleMoves(pawn2));
    }
    @Test
    public void checkRightCornerUPWhenInit() {
        initBoard();
        Pawn pawn = new Pawn(new Position(5,2),Color.BLACK);
        Pawn pawn2 = new Pawn(new Position(5,2),Color.WHITE);
        assertFalse(board.checkPossibleMoves(pawn));
        assertFalse(board.checkPossibleMoves(pawn2));
    }

}