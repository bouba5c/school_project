#include "Apple.h"
 Apple::Apple(int value, Color color)
  : Tile(color, true, false ,value)
{
 this->name = getName();
}


