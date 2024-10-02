#include "Model.h"
#include <random>
#include <algorithm>


Model::Model(int scorePlayer1,int scorePlayer2)
{
    players.push_back(std::make_shared<Player>(1));
    players.at(0)->setScore(scorePlayer1);
    players.push_back(std::make_shared<Player>(2));
    players.at(1)->setScore(scorePlayer2);
    this->curentPlayer =0;
}

int Model::getBoardSize()
{
    return board->getSize();
}
int Model::getBoardHeigh()
{
    return board->getHeight();
}
int Model::getBoardWidth()
{
    return board->getWidth();

}
State Model::getState()
{
    return this->state;
}
State Model::getGameMode()
{
    return this->gameMode;
}

int Model::GetCurrentPlayer()
{
    return curentPlayer;
}
std::shared_ptr<Tile>&  Model::getTile(Position &pos)
{
    return board->getTile(pos);
}
bool Model::canPickerBeBut(Position&pos)
{
    if(!board->isInside(pos))
    {
        return false;
    }
    return this->board->canBeput(pos);
}
int Model::getWinner()
{
    int winner = -1;
    if(getState() != State::GAME_OVER)
    {
        throw std::invalid_argument("The state is not game over \n");

    }
    int next = (GetCurrentPlayer() ==1) ? 0 : 1;

    bool condWin = (getPlayer()->getGreenBag()->isFull() && getPlayer()->getRedBag()->isFull()) ||
                   ( players.at(next)->getGreenBag()->getCapacity() >11 ||  players.at(next)->getRedBag()->getCapacity()>11);
    if(condWin)
    {

        winner = GetCurrentPlayer();
        players[winner]-> setScore(2);

    }
    bool condLose = getPlayer()->getGreenBag()->getCapacity() >11 ||  getPlayer()->getRedBag()->getCapacity()>11;

    if(condLose)
    {
        winner = next;

        players[winner]-> setScore(1);


    }
    return winner;
}
void Model::start(bool versionGameMode)
{
    // will construct a 5x5 board for normal game version ...

    if (!versionGameMode)
    {
        board = std::make_unique<Board>(5,5);

        deck = Deck( versionGameMode);
        int nbCard = deck.getNbCards()-1;
        std::random_device rd;
        std::mt19937 gen(rd());
        std::shuffle(players.begin(), players.end(), gen);
        std::uniform_int_distribution<int> distrib(0, players.size() - 1);
        curentPlayer = distrib(gen);
        for(int row = 0 ; row <getBoardHeigh() ; row++)
        {
            for(int col = 0 ; col < getBoardWidth() ; col++)
            {
                Position pos(row,col);
                board->put(deck.getCardsFaceUp().at(nbCard--),pos);
            }
        }
    }
    else{

        // will construct a 3x9 board for the allonge game version ...

        board = std::make_unique<Board>(3,9);

        deck = Deck(versionGameMode);
        int nbCard = deck.getNbCards()-2;
        std::random_device rd;
        std::mt19937 gen(rd());
        std::shuffle(players.begin(), players.end(), gen);
        std::uniform_int_distribution<int> distrib(0, players.size() - 1);
        curentPlayer = distrib(gen);
        for(int row = 0 ; row <getBoardHeigh() ; row++)
        {
            for(int col = 0 ; col < getBoardWidth()-1 ; col++)
            {
                Position pos(row,col);
                if(nbCard>=0)
                {
                    board->put(deck.getCardsFaceUp().at(nbCard--),pos);
                }

            }
        }
        std::uniform_int_distribution<int> distribution(0, 2);
        int randomNumber = distribution(gen);
        Position pos(randomNumber,getBoardWidth()-1);
        board->put(deck.getCardsFaceUp().at(deck.getNbCards()-1),pos);  // because le putpicker will be the last card of the deck

    }
    state = State::PLACE_PICKER;

}
std::shared_ptr<Tile> Model::getPickerTile()
{
    Position pos {board-> getPickerPosition()};
    this->pickerTile = getTile(pos);
    return pickerTile;

}
void Model::putPicker(Position &pos)
{
    if(getState() != State::PLACE_PICKER)
    {
        throw std::invalid_argument("The state is not place picker  \n");

    }
    Position OldPos = board->getPickerPosition();
    std::shared_ptr<Tile> elemNull = nullptr;

    pickerTile =getPickerTile();

    addAppleinBag(getTile(pos));
    board->put(this->pickerTile,pos);
    board->put(elemNull,OldPos);

    int next = (GetCurrentPlayer() ==1) ? 0 : 1;

    bool condWin = (getPlayer()->getGreenBag()->isFull() && getPlayer()->getRedBag()->isFull()) ||
                   ( players.at(next)->getGreenBag()->getCapacity() >11 ||  players.at(next)->getRedBag()->getCapacity()>11);

    bool condLose = getPlayer()->getGreenBag()->getCapacity() >11 ||  getPlayer()->getRedBag()->getCapacity()>11;
    state = (condWin || condLose)
                ? State::GAME_OVER : State::TURN_END;



}
void Model::nextPlayer()
{
    if(getState() != State::TURN_END)
    {
        throw std::invalid_argument("The state is not turn end  \n");

    }
    this->curentPlayer = (this->curentPlayer + 1) % players.size();
    this->state = State::PLACE_PICKER;
}
std::shared_ptr<Player> Model::getPlayer()
{

    return players.at(curentPlayer);
}

std::vector<std::shared_ptr<Player>> Model::getPlayers()
{
    return players;
}


void Model::addAppleinBag(std::shared_ptr<Tile>& tile)
{

    if(tile->getIsPicker())
    {
        throw std::invalid_argument("Le cueilleur ne peut pas etre mis dans un sac \n");
    }
    if(players[curentPlayer]->getBags().at(0)->getColor() == tile -> getColor())
    {
        players[curentPlayer]->getBags().at(0)->setCapacity(tile->getValue());
    }
    if(players[curentPlayer]->getBags().at(1)->getColor() == tile -> getColor())
    {
        players[curentPlayer]->getBags().at(1)->setCapacity(tile->getValue());

    }

}
//methode used for the console if they want to play again
void Model::resetBagCapacity()
{
    if(getState() !=State::GAME_OVER)
    {
        throw std::invalid_argument("The state is not game over \n");

    }
    for (std::shared_ptr<Player> player : players)
    {
        player->getGreenBag()->setCapacityAt0();
        player->getRedBag()->setCapacityAt0();
    }
}











