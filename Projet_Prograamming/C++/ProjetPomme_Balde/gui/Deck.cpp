#include "Deck.h"
#include "Apple.h"
#include "Picker.h"
#include <random>

Deck::Deck(bool gameMode)
{
    if(!gameMode)
    {
        for(int i = 0 ; i < 4 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(5,Color::RED));
            cards.push_back(std::make_shared<Apple>(5,Color::GREEN));

        }
        for(int i = 0 ; i < 3 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(3,Color::RED));
            cards.push_back(std::make_shared<Apple>(3,Color::GREEN));

        }
        for(int i = 0 ; i < 3 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(1,Color::RED));
            cards.push_back(std::make_shared<Apple>(1,Color::GREEN));

        }
        for(int i = 0 ; i < 2 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(2,Color::RED));
            cards.push_back(std::make_shared<Apple>(2,Color::GREEN));

        }
        cards.push_back(std::make_shared<Picker>());
        shuffle();
    }
    else
    {
        for(int i = 0 ; i < 4 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(5,Color::RED));
            cards.push_back(std::make_shared<Apple>(5,Color::GREEN));

        }
        for(int i = 0 ; i < 3 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(3,Color::RED));
            cards.push_back(std::make_shared<Apple>(3,Color::GREEN));

        }
        for(int i = 0 ; i < 3 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(1,Color::RED));
            cards.push_back(std::make_shared<Apple>(1,Color::GREEN));

        }
        for(int i = 0 ; i < 2 ; i++)
        {
            cards.push_back(std::make_shared<Apple>(2,Color::RED));
            cards.push_back(std::make_shared<Apple>(2,Color::GREEN));

        }
        shuffle();
        cards.push_back(std::make_shared<Picker>());


    }

}
int Deck::getNbCards()
{
    return cards.size();
}
std::vector<std::shared_ptr<Tile>> Deck::getCardsFaceDown()
{
    return this->cards;
}
std::vector<std::shared_ptr<Tile>> Deck::getCardsFaceUp()
{
    for(int i =0 ; i < getNbCards() ; i++)
    {
        cards.at(i)->flipFaceUp();
    }
    return cards;
}
void Deck::shuffle()
{
    std::random_device rd;
    std::mt19937 generator(rd());
    std::shuffle(cards.begin(), cards.end(), generator);
}

