#ifndef PLAYER_H
#define PLAYER_H

#include "Bag.h"
#include <vector>
#include <memory>

/**
 * @class Player
 * @brief Represents a player in the game.
 */
class Player
{
public:
    /**
     * @brief Constructor for the Player class.
     * @param id The unique identifier of the player.
     */
    Player(int id);

    /**
     * @brief Get the id of the player.
     * @return The player's id.
     */
    int getId();

    /**
     * @brief Get the vector of shared pointers to the player's bags.
     * @return A vector of shared pointers to Bag objects representing the player's bags.
     */
    std::vector<std::shared_ptr<Bag>> getBags();

    /**
     * @brief Get the shared pointer to the player's green bag.
     * @return A shared pointer to the Bag object representing the player's green bag.
     */
    std::shared_ptr<Bag> getGreenBag();

    /**
     * @brief Get the shared pointer to the player's red bag.
     * @return A shared pointer to the Bag object representing the player's red bag.
     */
    std::shared_ptr<Bag> getRedBag();

    /**
     * @brief Get the player's current score.
     * @return The player's score.
     */
    int getScore();

    /**
     * @brief Get the number of bags the player has.
     * @return The number of bags the player has.
     */
    int bagsSize();

    /**
     * @brief setScore the player's score by the given value.
     * @param value will add to the score the number of the value
     * @return The player's updated score.
     */
    void setScore(int value);

private:
    int id;
    std::vector<std::shared_ptr<Bag>> bags;
    int score ;
};

#endif // PLAYER_H
