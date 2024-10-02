#ifndef APPLE_H
#define APPLE_H

#include "Tile.h"
     /**
     * @class Apple
     * @brief Represents a tile of type "Apple" with different properties such as value , color and name .
     * @details This class inherits from the Tile class and provides methods to access and manipulate the properties of an apple tile.
     * @author balde boubacar 55870
     */
class Apple : public Tile

{
public:

    /**
     * @brief Constructor for the Apple class.
     * @param value The value associated with the apple tile.
     * @param color The color of the apple tile.
     */
    Apple(int value,Color color);

    /**
     * @brief Get the color of the apple tile.
     * @return The color of the apple tile.
     */
    Color getColor() override
    {
        return this->color;
    }
    /**
     * @brief Get the name of the color as a string.
     * @return The name of the color as a string.
     */
    std::string getColorName() override
    {
        std::string colorName = "";
        switch (color) {
        case GREEN:
            colorName +=   "G" ;
            break;
        case RED:
            colorName +=   "R" ;
            break;
        case NONE:
            colorName += "N" ;
            break;
        default:
            throw std::invalid_argument("color doesn't exist");
            break;
        }
        return colorName;
    }
    /**
     * @brief Get the name of the apple tile.
     * @details The name is a combination of the color name and the letter 'A'.
     * @return The name of the apple tile.
     */
    std::string getName() override{

        return color==Color::RED ? getColorName() +" "
                                   : getColorName() +" ";
    }
    /**
     * @brief Check if the tile is of type "Apple".
     * @return True, as this tile is of type "Apple".
     */
    bool getIsapple() override{
        return true;
    }
    /**
     * @brief Check if the tile is of type "Picker".
     * @return False, as an apple tile is not of type "Picker".
     */
    bool getIsPicker() override{
        return false;
    }
    /**
     * @brief Get the value associated with the apple tile.
     * @return The value of the apple tile.
     */
    int getValue() override
    {
        return this->value;
    }



};


#endif // APPLE_H
