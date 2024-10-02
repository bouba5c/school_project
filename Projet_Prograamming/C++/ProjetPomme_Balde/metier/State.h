#ifndef STATE_H
#define STATE_H

/**
 * @enum State
 * @brief Represents different states of the game.
 */
enum State
{
    NOT_STARTED,       //Represents the state when the game has not started yet.
    GAME_MODE_NORMAL,  // Represents the state of the game in the normal mode.
    GAME_MODE_ALLONGE, // Represents the state of the game in the "allonge" mode.
    PLACE_PICKER,      // Represents the state when a player is placing a picker.
    TURN_END,          // Represents the state when the turn is ending.
    GAME_OVER         // Represents the state when the game is over.
};

#endif // STATE_H
