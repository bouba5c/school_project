
#include "catch.hpp"
#include "Deck.h"

TEST_CASE("Testing  Deck")
{
    SECTION("Testing normal deck  ")
    {
        Deck deck(false);
        REQUIRE(deck.getNbCards() == 25);
        for(unsigned int i = 0 ; i < deck.getCardsFaceDown().size() ; i++)
        {
            REQUIRE(deck.getCardsFaceDown().at(i)-> isFaceUp() == 0);

        }
    }

    SECTION("Testing version deck  ")
    {
        Deck deck(true);
        REQUIRE(deck.getNbCards() == 25);
        for(unsigned int i = 0 ; i < deck.getCardsFaceDown().size() ; i++)
        {
            REQUIRE(deck.getCardsFaceDown().at(i)-> isFaceUp() == 0);

        }
        // check if the last card is a picker
        REQUIRE(deck.getCardsFaceDown().at(deck.getNbCards()-1)-> getIsPicker() == 1);


    }

}

