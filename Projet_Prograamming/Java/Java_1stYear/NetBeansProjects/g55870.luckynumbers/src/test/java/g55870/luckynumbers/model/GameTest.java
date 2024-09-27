package g55870.luckynumbers.model;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author balde boubacar <baldeboubacar11 at gmail.com>
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for startTest()
       ===================== */

 /* --- test related to the state --- */
    @Test
    public void startTest_when_game_not_startTested_ok() {
        game.startTest(4);
    }

    @Test
    public void startTest_when_game_over_ok() {
        fullPlay();
        game.startTest(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.startTest(2);
        int value = 1;
        int line = 0;
        int col = 0;
        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickFaceDownTile(value);
                game.putTile(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickFaceDownTile(20);
        game.putTile(new Position(line, col));
    }

    @Test
    public void startTest_when_game_in_progress_ISE() {
        game.startTest(4);
        assertThrows(IllegalStateException.class,
                () -> game.startTest(1));
    }

    @Test
    public void startTest_state_changed_to_PICK_TILE() {
        game.startTest(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void startTest_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.startTest(1));
    }

    @Test
    public void startTest_playerCount_minimum_accepted() {
        game.startTest(2);
    }

    public void State_PLACE_OR_DROP_Tile_Exception() {
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0, 0)));
    }

    @Test
    public void startTest_playerCount_maximum_accepted() {
        game.startTest(4);
    }

    @Test
    public void startTest_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.startTest(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void startTest_playerCount_initialized() {
        game.startTest(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void startTest_current_player_is_player_0() {
        game.startTest(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    @Test
    public void getPickedTile() {
        game.startTest(2);
        game.pickFaceDownTile(3);
        assertEquals(3, game.getPickedTile().getValue());

    }

    /* ===================
        getBoardSize Test
       ===================*/
    @Test
    public void getBoardSize_true_when_same_value_than_method() {
        game.startTest(2);
        int expected = 4;
        assertEquals(expected, game.getBoardSize());
    }

    @Test
    public void getBoardSize_false_when_diff_value_than_method() {
        game.startTest(2);
        int expected = 0;
        assertNotEquals(expected, game.getBoardSize());
    }

    /* =======================
        getCurrentPlayer Test
       =======================*/
    @Test
    public void getCurrentPlayer_if_first_Player_played() {
        game.startTest(2);
        game.pickFaceDownTile();
        Position pos = new Position(0, 0);
        game.putTile(pos);
        game.nextPlayer();
        assertEquals(1, game.getCurrentPlayerNumber());
    }

    @Test
    public void getCurrentPlayer_if_last_Player_played() {
        game.startTest(2);
        for (int i = 0; i < game.getPlayerCount(); i++) {
            game.pickFaceDownTile(2);
            Position pos = new Position(0, 0);
            game.putTile(pos);
            game.nextPlayer();
        }
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    /* ============
        canTileBePut Test
       ============*/
    @Test

    public void canTileBePut_true_when_empty_spots() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertTrue(game.canTileBePut(new Position(0, 0)));
    }

    @Test

    public void canTileBePut_false_when_bigger_value_left() {
        game.startTest(2);
        game.pickFaceDownTile(10);   // 1st player plays the bigger value at left
        game.putTile(new Position(1, 0));
        game.nextPlayer(); // 2de player
        game.pickFaceDownTile(2);
        game.putTile(new Position(3, 3));
        game.nextPlayer();     //1st player plays a small value than the left
        game.pickFaceDownTile(5);
        assertFalse(game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void canTileBePut_false_when_same_value_at_left() {
        game.startTest(2);
        game.pickFaceDownTile(10);              // first player
        game.putTile(new Position(1, 0));
        game.nextPlayer();        //scond player 
        game.pickFaceDownTile(2);
        game.putTile(new Position(3, 3));
        game.nextPlayer();    // first player plays the same value at left
        game.pickFaceDownTile(10);
        assertFalse(game.canTileBePut(new Position(1, 1)));

    }

    @Test
    public void canTileBePut_false_when_same_value_up() {
        game.startTest(2);
        game.pickFaceDownTile(5);        //first player
        game.putTile(new Position(0, 1));
        game.nextPlayer();      // second player
        game.pickFaceDownTile(5);
        game.putTile(new Position(3, 3));
        game.nextPlayer();///first player plays the same value up
        game.pickFaceDownTile(5);
        assertFalse(game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void canTileBePut_false_when_smaller_value_right() {
        game.startTest(2);
        game.pickFaceDownTile(5);        //1st player
        game.putTile(new Position(1, 3));
        game.nextPlayer();      // second player
        game.pickFaceDownTile(5);
        game.putTile(new Position(3, 3));
        game.nextPlayer();     //1st player     
        game.pickFaceDownTile(10);
        assertFalse(game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void canTileBePut_false_when_same_value_right() {
        game.startTest(2);
        game.pickFaceDownTile(10);        //1st player
        game.putTile(new Position(1, 3));
        game.nextPlayer();      // second player
        game.pickFaceDownTile(5);
        game.putTile(new Position(3, 3));
        game.nextPlayer();     //1st player 
        game.pickFaceDownTile(10);
        assertFalse(game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void canTileBePut_false_when_smaller_value_down() {
        game.startTest(2);
        game.pickFaceDownTile(5);        //1st player
        game.putTile(new Position(3, 1));
        game.nextPlayer();      // second player
        game.pickFaceDownTile(5);
        game.putTile(new Position(3, 3));
        game.nextPlayer();     //1st player
        game.pickFaceDownTile(10);
        assertFalse(game.canTileBePut(new Position(1, 1)));
    }

    @Test
    public void canTileBePut_false_when_same_value_down() {
        game.startTest(2);
        game.pickFaceDownTile(20);        //1st player
        game.putTile(new Position(3, 1));
        game.nextPlayer();      // second player
        game.pickFaceDownTile(5);
        game.putTile(new Position(3, 3));
        game.nextPlayer();     //1st player
        game.pickFaceDownTile(20);
        assertFalse(game.canTileBePut(new Position(2, 1)));
    }

    /* ===============
        IsInside Test
       ===============*/
    @Test
    public void isInside_true_when_last_row() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertTrue(game.isInside(new Position(1, game.getBoardSize() - 1)));

    }

    @Test
    public void isInside_true_if_row_is_too_big() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertFalse(game.isInside(new Position(5, game.getBoardSize() - 1)));

    }

    @Test
    public void isInside_true_when_first_row() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertTrue(game.isInside(new Position(0, 2)));
    }

    @Test
    public void isInside_true_when_second_col() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertTrue(game.isInside(new Position(2, 1)));
    }

    @Test
    public void isInside_true_when_last_col() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertTrue(game.isInside(new Position(2, 3)));
    }

    @Test
    public void isInside_position_when_col_is_too_small() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertFalse(game.isInside(new Position(0, -1)));
    }

    @Test
    public void isInside_position_when_col_is_too_big() {
        game.startTest(2);
        game.pickFaceDownTile(1);
        assertFalse(game.isInside(new Position(0, 4)));
    }

    /* ===============
        getTile Test
       ===============*/
    @Test
    public void getTile() {
        game.startTest(3);
        game.pickFaceDownTile(20);        //1st player
        game.putTile(new Position(3, 1));
        game.nextPlayer();      // 2de player
        game.pickFaceDownTile(5);
        game.putTile(new Position(3, 3));
        game.nextPlayer();     //3th player
        game.pickFaceDownTile(20);
        assertEquals(20, game.getTile(0, new Position(3, 1)).getValue());
    }

    /* ===============
        faceDownTileCount Test
       ===============*/
    @Test
    public void faceDownTileCount_when_playercount_is_2() {
        game.startTest(2);
        assertEquals(40, game.faceDownTileCount());
    }

    @Test
    public void faceDownTileCount_when_playercount_is_4() {
        game.startTest(4);
        assertEquals(80, game.faceDownTileCount());
    }

    /* ===============
        picp Test
       ===============*/
    @Test
    public void pickFaceUpTile_when_tile_doesnt_exist_Exception() {
        game.startTest(2);
        assertThrows(IllegalArgumentException.class,
                () -> game.pickFaceUpTile(new Tile(40)));
    }

    /* === Test for dropTile === */
    @Test
    public void dropTile_Exception() {
        assertThrows(IllegalStateException.class,
                () -> game.dropTile());
    }

    @Test
    public void dropTile_1_tile_isUp() {
        game.startTest(2);
        game.pickFaceDownTile();        //1st player
        game.dropTile();
        game.nextPlayer();      // 2de player
        game.pickFaceDownTile();
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertEquals(1, game.faceUpTileCount());
    }

    @Test
    public void dropTile_when_no_dropTile() {
        game.startTest(2);
        game.pickFaceDownTile();        //1st player
        game.putTile(new Position(0, 1));
        game.nextPlayer();      // 2de player
        game.pickFaceDownTile();
        game.putTile(new Position(0, 0));
        game.nextPlayer();
        assertEquals(0, game.faceUpTileCount());
    }

    @Test
    public void dropTile_when_all_tiles_are_faceup() {
        game.startTest(2);
        for (int i = 0; i < 40; i++) {
            game.pickFaceDownTile();        //1st player
            game.dropTile();
            game.nextPlayer();
        }
        assertEquals(40, game.faceUpTileCount());
    }

    /* === Test for pickFaceDownTile === */
    @Test
    public void pickFaceDownTile_Exception() {
        assertThrows(IllegalStateException.class,
                () -> game.pickFaceDownTile());
    }

    @Test
    public void pickFaceDownTile() {

        game.startTest(2);
        assertEquals(getFaceDown(game.faceDownTileCount() - 1), game.pickFaceDownTile().getValue());
    }

    /* === Test for pickFaceDownTile === */
    @Test
    public void faceDownTileCount_when_playercount_is_4_with_diagonal_already_puted_randomly() {
        game.start(4);
        assertEquals(64, game.faceDownTileCount());
    }

    @Test
    public void faceDownTileCount_when_playercount_is_2_with_diagonal_already_puted_randomly() {
        game.start(4);
        assertEquals(64, game.faceDownTileCount());
    }

    int getFaceDown(int nb) {
        return game.getAllfaceDownTiles().get(nb).getValue();

    }

    @Test
    public void faceUpTileCount_when_boardcell_is_occupied() {
        game.start(2);
        game.pickFaceDownTile();        //1st player
        game.dropTile();
        game.nextPlayer();      // 2de player
        game.pickFaceDownTile();
        game.dropTile();
        game.nextPlayer();
        assertEquals(2, game.faceUpTileCount());
    }

}
