package g55870.atl.othello.model;

/**
 * Represents the state of an Othello game.
 */
public enum State {
    /**
     * Indicates that the game has not started yet.
     */
    NOT_STARTED,

    /**
     * Indicates that it is the turn to put a pawn on the board.
     */
    PUT,
    /**
     * Indicates that a player's turn has ended.
     */
    TURN_END,

    /**
     * Indicates that the game is in the undo state.
     */
    UNDO,

    /**
     * Indicates that the game is over.
     */
    GAME_OVER,

    /**
     * Indicates that the game is in the redo state.
     */
    REDO
}