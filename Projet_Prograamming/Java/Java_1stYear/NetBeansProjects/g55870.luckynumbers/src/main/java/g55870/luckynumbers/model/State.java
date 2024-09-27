package g55870.luckynumbers.model;

/**
 * enum state is the state of the game. At the beginning we are on the state NOT
 * STARTED and the only thing we can do it's to start the game. after we are in
 * the state PICK TILE, the current player pick a tile then we are in the state
 * PLACE TILE the current player can put a tile at the given position according
 * to the rules of the game afterwards we have two options :
 * <li>State GAME over : if the game is over the only option is to start again
 * .</li>
 * <li>State TURN END : if the game is not over ,it'is the turn of the next
 * player.</li>
 * State PLACE_OR_DROP_TILE : if he wants to put the tile at the given position
 * or if he wants to drop the tile.
 */
public enum State {

    NOT_STARTED,
    PICK_TILE,
    PLACE_TILE,
    TURN_END,
    GAME_OVER,
    PLACE_OR_DROP_TILE

}
