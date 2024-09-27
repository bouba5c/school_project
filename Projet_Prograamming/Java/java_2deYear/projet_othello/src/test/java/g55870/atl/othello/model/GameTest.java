package g55870.atl.othello.model;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void startThrowWhenIllegalArgument()
    {
        Model game = new Game();
        for(int i =4 ; i< 10 ; i++)
        {
            int finalI = i;
            assertThrows(IllegalArgumentException.class, () -> game.start(finalI,10));
        }
    }
    @Test
    public void startThrowWhenIllegalState()
    {
        Model game = new Game();
        game.setState(State.TURN_END);
        assertThrows(IllegalStateException.class, () -> game.start(2,10));
        game.setState(State.REDO);
        assertThrows(IllegalStateException.class, () -> game.start(2,10));
        game.setState(State.UNDO);
        assertThrows(IllegalStateException.class, () -> game.start(2,10));
        game.setState(State.PUT);
        assertThrows(IllegalStateException.class, () -> game.start(2,10));
    }
    @Test
    public void getWinnerWhenPlayer1Surrender() {
        Model game = new Game();
        game.start(1,4);
        game.update();
        game.surrender();
        assertEquals(2,game.getWinner());
    }

    @Test
    public void getWinnerThrowIllegalSate() {
        Model game = new Game();
        assertThrows(IllegalStateException.class, game::getWinner);
    }
    @Test
    public void getWinnerWhenPlayer2Surrender() {
        Model game = new Game();
        game.start(1,4);
        game.update();
        game.nextPlayer();
        game.surrender();
        assertEquals(1,game.getWinner());
    }
    @Test
    public void getCurrentPlayerWhenGameNotStarted() {
        Model game = new Game();
        assertEquals(0,game.getCurrentPlayerId());
    }
    @Test
    public void getCurrentPlayerWhenGameIsStarted() {
        Model game = new Game();
        game.start(1,4);
        assertEquals(1,game.getCurrentPlayerId());
    }
    @Test
    public void getCurrentPlayerWhenPlayer1played() {
        Model game = new Game();
        game.start(1,4);
        game.nextPlayer();
        assertEquals(2,game.getCurrentPlayerId());
    }
    @Test
    public void getCurrentPlayerWhenPlayer2played() {
        Model game = new Game();
        game.start(1,4);
        game.nextPlayer();
        game.nextPlayer();
        assertEquals(1,game.getCurrentPlayerId());
    }
    @Test
    public void getState() {
        Model game = new Game();
        assertEquals(State.NOT_STARTED,game.getState());
    }
    @Test
    public void getStateWhenGameIsStarted() {
        Model game = new Game();
        game.start(1,10);
        assertEquals(State.PUT,game.getState());
    }
    @Test
    public void setState() {
        Model game = new Game();
        game.setState(State.REDO);
        assertEquals(State.REDO,game.getState());
        game.setState(State.PUT);
        assertEquals(State.PUT,game.getState());
    }
    @Test
    public void putPion()
    {
        Model game = new Game();
        game.start(1,4);
        game.update();
        game.putPawn(new Pawn(new Position(1,0),Color.BLACK));
    }
    @Test
    public void putPionWhenNoUpdate()
    {
        Model game = new Game();
        game.start(1,4);
        assertThrows(NullPointerException.class, () -> game.putPawn(new Pawn(new Position(1,0),Color.BLACK)));
    }
    @Test
    public void putPawnThrowWhenIllegalStateEx()
    {
        Model game = new Game();
        assertThrows(IllegalStateException.class, () -> game.putPawn(new Pawn(new Position(3,3),Color.BLACK)));
    }
    @Test
    public void putPawnThrowsWhenIllegalArgumentEx()
    {
        Model game = new Game();
        game.start(1,4);
        assertThrows(IllegalArgumentException.class, () -> game.putPawn(new Pawn(new Position(3,3),Color.BLACK)));
    }

    @Test
    public void playAgainWhenIllegalState()
    {Model game = new Game();
        game.start(1,4);
        assertThrows(IllegalStateException.class, game::playAgain);
        game.setState(State.TURN_END);
        assertThrows(IllegalStateException.class, game::playAgain);
        game.setState(State.UNDO);
        assertThrows(IllegalStateException.class, game::playAgain);
        game.setState(State.REDO);
        assertThrows(IllegalStateException.class, game::playAgain);
        game.setState(State.PUT);
        assertThrows(IllegalStateException.class, game::playAgain);
    }

    @Test
    public void playAgainWhenLegalState()
    {
        Model game = new Game();
        game.start(1,4);
        game.setState(State.GAME_OVER);
        game.playAgain();
     }

    @Test
    public void undoWhenIllegalState()
    {
        Model game = new Game();
        game.start(1,4);
        assertThrows(IllegalStateException.class, () -> {
            game.setUndo(true);
            game.undo(false);
            game.setUndo(true);
            game.undo(true);
        });
    }
    @Test
    public void undoWhenIllegalStateGameOver()
    {
        Model game = new Game();
        game.start(1,4);
        game.setState(State.GAME_OVER);
        assertThrows(IllegalStateException.class, () -> {
            game.setUndo(true);
            game.undo(false);
        });
    }
    @Test
    public void undoWheLegalState()
    {
            Model game = new Game();
            game.start(1,4);
            game.putPawn(new Pawn(new Position(1,0),Color.BLACK));
            game.nextPlayer();
            game.setUndo(true);
            game.update();
            assertThrows(NoSuchElementException.class, () -> game.undo(false));

    }

    @Test
    public void RedoWhenIllegalStateWhenGameOver()
    {
        Model game = new Game();
        game.start(1,4);
        game.setState(State.GAME_OVER);
        assertThrows(IllegalStateException.class, () -> {
            game.setRedo(true);
            game.redoJavaFx();
        });
    }
    @Test
    public void RedoWhenIllegalStateISnotStarted()
    {
        Model game = new Game();
        game.start(1,4);
        game.setState(State.NOT_STARTED);
        assertThrows(IllegalStateException.class, () -> {
            game.setRedo(true);
            game.redoJavaFx();
        });
    }

}