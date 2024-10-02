#ifndef VIEW_H
#define VIEW_H

#include "Model.h"
#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

/**
 * @class View
 * @brief Represents the view of the game, responsible for displaying the game board and interacting with the user.
 */
class View
{
public:
    /**
     * @brief Constructor for the View class.
     * @param model The reference to the Model object representing the game state.
     */
    View(Model& model);

    /**
     * @brief Display a welcome message to the user.
     */
    void displayWelcome();


    /**
     * @brief Display the game board and other information.
     */
    void displayGame();

    /**
     * @brief Display the winner of the game.
     */
    void displayWinner();

    /**
     * @brief Ask the user to input a position on the board.
     * @return The position entered by the user.
     */
    Position askPosition();

    /**
     * @brief Ask the user to choose the game mode.
     * @return True if the user chooses version game mode, False if normal game mode.
     */
    bool askGameMode();

    /**
     * @brief Display an error message to the user.
     * @param message The error message to be showed.
     */
    void displayError(std::string message);

    /**
     * @brief ask the user to play again
     * @param True if the user chooses 1 , false if they want to quit
     */
    bool askPlayAgain();

private:
    Model& model;

    /**
     * @brief Display the game board.
     */
    void displayBoard();

    /**
     * @brief Display the column numbers on the top of the game board.
     */
    void displayColumn();

    /**
     * @brief Display the current player's information, such as their color and name.
     * and also the next player information to have a better viewing of the game.
     */
    void displayCurrentPlayer();
};

#endif // VIEW_H
