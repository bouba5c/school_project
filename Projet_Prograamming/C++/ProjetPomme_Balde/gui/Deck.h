#ifndef DECK_H
#define DECK_H

#include "Tile.h"
#include <vector>
#include <memory>

/**
 * @class Deck
 * @brief Represents a deck of tiles in the game.
 * @author balde boubacar 55870
 */
class Deck
{
public:
    /**
     * @brief Constructor for the Deck class.
     * @param gamemode Indicates if the deck is for the version game mode(true) or normal mode (false).
     */
    Deck(bool gamemode = false);

    /**
     * @brief Shuffle the deck
     */
    void shuffle();

    /**
     * @brief Get the cards that are face-down in the deck.
     * @return A vector of shared pointers to Tile objects that are face-down.
     */
    std::vector<std::shared_ptr<Tile>> getCardsFaceDown();

    /**
     * @brief Get the cards that are face-up in the deck.
     * @return A vector of shared pointers to Tile objects that are face-up.
     */
    std::vector<std::shared_ptr<Tile>> getCardsFaceUp();

    /**
     * @brief Get the number of cards in the deck.
     * @return The number of cards in the deck.
     */
    int getNbCards();

private:
    /**
     * \brief cards of the deck
     */
    std::vector<std::shared_ptr<Tile>> cards;
};

#endif // DECK_H
