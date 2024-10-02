#include "Player.h"
Player::Player(int id) :id(id)
{
    bags.push_back(std::make_shared<Bag>(Color::GREEN));
    bags.push_back(std::make_shared<Bag>(Color::RED));
    this->score = 0;

}

int Player::getId(){
    return this->id;
}
std::shared_ptr<Bag>Player::getGreenBag()
{
    return bags.at(0);
}
std::shared_ptr<Bag> Player::getRedBag()
{
    return bags.at(1);
}
std::vector<std::shared_ptr<Bag>> Player::getBags()
{
    return this->bags;
}
int Player::bagsSize()
{
    return bags.size();
}

int Player::getScore(){
    return this->score;
}
void Player::setScore(int value)
{
    score +=value;

}



