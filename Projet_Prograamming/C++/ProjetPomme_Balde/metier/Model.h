#ifndef MODEL_H
#define MODEL_H

#include "Player.h"
#include "Board.h"
#include "Deck.h"
#include "State.h"
#include "Picker.h"
#include <vector>
#include <memory>

/**
 * @class Model
 * @brief Represents the game model in the game.
 */
class Model
{
public:
    /**
     * @brief Constructor for the Model class.
     */
    Model();

    /**
     * @brief Get the size (number of tiles) of the game board.
     * @return The size of the game board.
     */
    int getBoardSize();

    /**
     * @brief Get the height of the game board.
     * @return The height of the game board.
     */
    int getBoardHeigh();

    /**
     * @brief Get the width of the game board.
     * @return The width of the game board.
     */
    int getBoardWidth();

    /**
     * @brief Get the current state of the game.
     * @return The current state of the game.
     */
    State getState();

    /**
     * @brief Get the current game mode of the game.
     * @return The current game mode of the game.
     */
    State getGameMode();

    /**
     * @brief Get the current player.
     * @return the current player.
     */
    int GetCurrentPlayer();

    /**
     * @brief Check if a picker can be placed at a given position on the board.
     * @param pos The position to check.
     * @return True if the picker can be placed at the position, False otherwise.
     */
    bool canPickerBeBut(Position& pos);

    /**
     * @brief Get the identifier of the winner player  in the game.
     * @return The identifier of the winner player. Returns -1 if there is no winner.
     */
    int getWinner();

    /**
     * @brief Start the game with the specified game mode.
     * @param gameMode The game mode to start the game with.
     */
    void start(bool gameMode);

    /**
     * @brief Place a picker at a given position on the board.
     * and will remove his previous position
     * @param pos The position to place the picker.
     */
    void putPicker(Position& pos);

    /**
     * @brief next player .
     */
    void nextPlayer();

    /**
     * @brief Get a reference to the tile at a given position on the board.
     * @param pos position of a given tile .
     * @return A reference to the tile at the position.
     */
    std::shared_ptr<Tile>& getTile(Position& pos);

    /**
     * @brief Get the current player.
     * @return The player with the current turn.
     */
    std::shared_ptr<Player> getPlayer();

    /**
     * @brief Get the tile representing the picker.
     * @return a shared pointer to the tile representing the picker.
     */
    std::shared_ptr<Tile> getPickerTile();

    /**
     * @brief Add an apple tile to the player's bag.
     * @param tile a shared pointer to the apple tile to be added to the bag.
     */
    void addAppleinBag(std::shared_ptr<Tile>& tile);
    /**
     * @brief get all player of the game
     * @return a shared pointer of player epresenting the players.

     */
    std::vector<std::shared_ptr<Player>> getPlayers();
    /**
     * @brief reset all player's bag capactity
     */
    void resetBagCapacity();

private:
    std::vector<std::shared_ptr<Player>> players;
    int curentPlayer;
    std::unique_ptr<Board> board;
    Deck deck;
    State state;
    State gameMode;
    std::shared_ptr<Tile> pickerTile;
};

#endif // MODEL_H
