 #include "Bag.h"

Bag::Bag(Color color) : color(color) ,capacity(0)
{

}
Color Bag::getColor()
{
    return this->color;
}
int Bag::getCapacity()
{
    return this->capacity;
}
void Bag::setCapacity(int newCapacity) {

    this->capacity += newCapacity;

}
void Bag::setCapacityAt0() {

    this->capacity = 0;

}
bool Bag::isFull()
{
    return this->capacity==11;
}

Bag::~Bag()
{

}
