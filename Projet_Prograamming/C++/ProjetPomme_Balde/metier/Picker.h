#ifndef PICKER_H
#define PICKER_H
#include "Tile.h"
/**
 * @class Picker
 * @brief Represents a tile of type "Picker" with different properties such as value and color.
 */
class Picker : public Tile
{
public:
    /**
     * @brief Constructor for the Picker class.
     */
    Picker();
    /**
     * @brief Get the color name of the Picker tile.
     * @return The color name of the Picker tile, which is always "N" (none).
     */
      std::string getColorName() override
    {

        return "N";
    }
    /**
     * @brief Get the color of the Picker tile.
     * @return The color of the Picker tile.
     */
      Color getColor() override
    {
        return this->color;
    }
    /**
     * @brief Get the name of the Picker tile.
     * @details The name of the Picker tile is represented as "PI" with an ANSI color code for orange.
     * @return The name of the Picker tile.
     */
     std::string getName() override{

        return "\033[38;5;208mPI\033[0m"; //ansi color orange
    }
     /**
     * @brief Check if the tile is of type "Apple".
     * @return False, as a Picker tile is not of type "Apple".
     */
      bool getIsapple() override{
         return false;
     }
      /**
     * @brief Check if the tile is of type "Picker".
     * @return True, as this tile is of type "Picker".
     */
      bool getIsPicker() override{
         return true;
     }
      /**
     * @brief Get the value associated with the Picker tile.
     * @return The value of the Picker tile.
     */
      int getValue() override
      {
          return this->value;
      }


};

#endif // PICKER_H
