#include <iostream>
#include "Color.h"
#include "Apple.h"
#include "Tile.h"
#include "Picker.h"
#include "Bag.h"
#include <memory>
#include "Board.h"
#include "Deck.h"
#include "Model.h"
#include "View.h"
#include "Controller.h"

int main()
{

    Model model{};
    View view{model};
    Controller controller {model,view};
    controller.play();

    return 0;
}
