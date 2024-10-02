#ifndef CONTROLLER_H
#define CONTROLLER_H

#include "Model.h"
#include "View.h"

/**
 * @class Controller
 * @brief Represents the controller of the game.
 */
class Controller
{
public:
    /**
     * @brief Constructor for the Controller class.
     * @param model A reference to the Model object representing the game state.
     * @param view A reference to the View object representing the game view.
     */
    Controller(Model& model, View& view);

    /**
     * @brief Start the game
     */
    void play();

private:
    Model& model;
    View& view;
};

#endif // CONTROLLER_H
