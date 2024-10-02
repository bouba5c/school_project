#ifndef BAG_H
#define BAG_H

#include "Color.h"
#include "Tile.h"
#include <stdexcept>
#include <memory>
/**
 * @class Bag
 * @brief Represents a bag
   @author balde boubacar 55870
 */
class Bag
{
public:
    /**
     * @brief Constructor for the Bag class.
     * @param color of the bag
     */
    Bag(Color color);
    /**
     * @brief Destructor for the Bag class.
     */
    ~Bag();
    /**
     * @brief Get the color of the bag.
     * @return The color of the bag.
     */
    Color getColor();
    /**
     * @brief Get the capacity of the bag.
     * @return The capacity of the bag.
     */
    int getCapacity();
    /**
     * @brief Set the capacity of the bag.
     * @param add the value in the capacity.
     */

    void setCapacity(int value);
    /**
     * @brief Set the capacity of the bag to 0  only used in console.
     */

    void setCapacityAt0();
    /**
     * @brief Check if the bag is full.
     * @return True if the bag is full, False otherwise.
     */
    bool isFull();

private:
    /**
     * \brief color of the bag
     */
    Color color;
    /**
     * \brief capacity of the bag
     */
    int capacity;
};

#endif // BAG_H
