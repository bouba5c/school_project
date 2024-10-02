#include "Picker.h"
#include "Color.h"
Picker::Picker() : Tile(Color::NONE, false, true ,0)
{
    this->name = Picker::getName();
}

