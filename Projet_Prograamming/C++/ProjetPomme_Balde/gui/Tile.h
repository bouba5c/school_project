#ifndef TILE_H
#define TILE_H

#include "Color.h"
#include <stdexcept>
#include <string>

/**
 * @class Tile
 * @brief Represents a abstracr class which represent the tile
 * @author balde boubacar 55870
 */
class Tile
{
public:
    /**
     * @brief Constructor for the Tile class.
     * @param color The color of the tile.
     * @param isApple Indicates if the tile is of type "Apple".
     * @param isPicker Indicates if the tile is of type "Picker".
     * @param value The value associated with the tile.
     */
    Tile(Color color, bool isApple, bool isPicker, int value);

    /**
     * @brief Virtual destructor for the Tile class.
     * @details Cleans up any resources allocated by the tile.
     */
    virtual ~Tile();

    /**
     * @brief Get the color of the tile.
     * @return The color of the tile.
     */
    virtual Color getColor() = 0;

    /**
     * @brief Get the name of the color as a string.
     * @return The name of the color as a string.
     */
    virtual std::string getColorName() = 0;

    /**
     * @brief Get the name of the tile.
     * @details The name is specific to the tile type and is represented as a string.
     * @return The name of the tile.
     */
    virtual std::string getName() = 0;

    /**
     * @brief Check if the tile is of type "Apple".
     * @return True if the tile is of type "Apple", False otherwise.
     */
    virtual bool getIsapple() = 0;

    /**
     * @brief Check if the tile is of type "Picker".
     * @return True if the tile is of type "Picker", False otherwise.
     */
    virtual bool getIsPicker() = 0;

    /**
     * @brief Get the value associated with the tile.
     * @return The value of the tile.
     */
    virtual int getValue() = 0;

    /**
     * @brief Check if the tile is face-up.
     * @return True if the tile is face-up, False if it is face-down.
     */
    bool isFaceUp();

    /**
     * @brief Flip the tile to be face-up.
     */
    void flipFaceUp();

protected:
    /**
     * \brief color of the tile
     */
    Color color;
    /**
     * \brief type of the tile if apple true else false
     */
    bool isApple;
    /**
     * \brief type of the tile if picker true else false
     */
    bool isPicker;
    /**
     * \brief name of the tile
     */
    std::string name;
    /**
     * \brief value of the tile
     */
    int value;
    /**
     * \brief boolean to indicate if the tile is face um
     */
    bool faceUp;
};

#endif // TILE_H
