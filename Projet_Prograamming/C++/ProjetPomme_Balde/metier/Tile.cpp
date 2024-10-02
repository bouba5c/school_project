#include "Tile.h"

Tile::Tile(Color color, bool isApple, bool isPicker ,int value )
    : color(color), isApple(isApple), isPicker(isPicker),value(value) , faceUp(false)
{
}
Tile::~Tile()
{
}
bool Tile::isFaceUp()
{
 return faceUp;
}
void Tile::flipFaceUp()
{
    this->faceUp = true;
}
