package g55870.atl.othello.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerStrategyTest {
    private final PlayerStrategy humanPlayer = new Player(1,Color.BLACK);
    private final PlayerStrategy iaPlayer = new PlayerIA(2,Color.WHITE);
    private final PlayerStrategy randomPlayer = new PlayerRandom(1,Color.BLACK);
    final BoardTest board = new BoardTest();

    @Test
    public void getId() {
        assertEquals(1,humanPlayer.id());
        assertEquals(2,iaPlayer.id());
        assertEquals(1,randomPlayer.id());
        asserInstanceOF(humanPlayer instanceof Player);
        asserInstanceOF(iaPlayer instanceof PlayerIA);
        asserInstanceOF(randomPlayer instanceof PlayerRandom);
        asserInstanceOF(!(randomPlayer instanceof PlayerIA));
    }
    private void asserInstanceOF(boolean b) {
        if (!b) {
            throw new AssertionError("error : not instance of");
        }
    }

    @Test
    public void testGetIdHuman() {
        assertEquals(1,humanPlayer.id());
    }
    @Test
    public void testGetIRandom() {
        assertEquals(10,randomPlayer.id());
    }
    @Test
    public void testGetIdIa() {
        assertEquals(4,iaPlayer.id());
    }
    @Test
    public void testGetColor() {
        assertEquals(Color.BLACK,humanPlayer.color());
        assertEquals(Color.WHITE,iaPlayer.color());
        assertEquals(Color.BLACK,randomPlayer.color());
    }
    @Test
    public void chooseMoveIaTakeBiggerCount() {
        board.initBoard();
        board.board.put(new Pawn(new Position(3,5),Color.BLACK));
        Position position = iaPlayer.chooseMove(board.board.getPossibleWhiteMoves(), Color.WHITE,Color.BLACK,board.board);
        assertEquals(new Position(3,6),position);
    }
    @Test
    public void chooseMoveIAWhen1() {
        board.initBoard();
        Position random = iaPlayer.chooseMove(board.board.getPossibleWhiteMoves(),Color.WHITE,Color.BLACK, board.board);
        assertTrue(board.board.getPossibleWhiteMoves().contains(random));
    }
    @Test
    public void chooseMoveRandom() {
        board.initBoard();
        Position random = randomPlayer.chooseMove(board.board.getPossibleWhiteMoves(),Color.WHITE,Color.BLACK, board.board);
        assertTrue(board.board.getPossibleWhiteMoves().contains(random));
    }

    @Test
    public void chooseMoveHuman() {
        assertNull(humanPlayer.chooseMove(board.board.getPossibleWhiteMoves(),Color.WHITE,Color.BLACK, board.board));
    }
}




