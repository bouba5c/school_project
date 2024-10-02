#ifndef BOARD_H
#define BOARD_H

#include <vector>
#include "Tile.h"
#include "Position.h"
#include <memory>

/**
 * @class Board
 * @brief Represents the game board of the game.
 */
class Board
{
public:
    /**
     * @brief Constructor for the Board class.
     * @param longeur The length of the board.
     * @param largeur The width of the board.
     */
    Board(int longeur, int largeur);

    /**
     * @brief Destructor for the Board class.
     */
    /**
     * @brief Get the size of the board.
     * @return  size of the board.
     */
    int getSize();

    /**
     * @brief Get the width of the board.
     * @return The width of the board.
     */
    int getWidth();

    /**
     * @brief Get the height of the board.
     * @return The height of the board.
     */
    int getHeight();

    /**
     * @brief Check if a given position is inside the board.
     * @param pos The position to check.
     * @return True if the position is inside the board, False otherwise.
     */
    bool isInside(Position& pos);

    /**
     * @brief Check if a tile can be placed at a given position on the board.
     * @param pos The position to check.
     * @return True if the tile can be placed at the position, False otherwise.
     */
    bool canBeput(Position& pos);

    /**
     * @brief Check if a picker can move to a given position on the board.
     * @param pos The position to check.
     * @return True if the picker can move to the position, False otherwise.
     */
    bool checkPickerMov(Position& pos);

    /**
     * @brief Get the position of the picker on the board.
     * @return The position of the picker.
     */
    Position getPickerPosition();

    /**
     * @brief Get a reference to the tile at a given position on the board.
     * @param pos The position to get the tile from.
     * @return A reference to the tile at the position.
     */
    std::shared_ptr<Tile>& getTile(Position& pos);

    /**
     * @brief Place a tile at a given position on the board.
     * @param tile A shared pointer to the tile to be placed.
     * @param pos The position where the tile should be placed.
     */
    void put(std::shared_ptr<Tile>& tile, Position& pos);

    /**
     * @brief Get a reference to the vector of tiles at a given row index on the board.
     * @param index The row index.
     * @return A reference to the vector of tiles at the row index.
     */
    std::vector<std::shared_ptr<Tile>>& operator[](int index);

private:
    const int height;
    const int width;
    std::vector<std::vector<std::shared_ptr<Tile>>> tiles;
};

#endif // BOARD_H
