#ifndef CONTROLLERGUI_H
#define CONTROLLERGUI_H

#include <QWidget>
#include "Model.h"

#include "MainWindow.h"
class MainWindow;
namespace Ui {

/**
 * @class Controller
 * @brief Represents the controller of the game.
 * @author balde boubacar 55870
 */

class ControllerGui;
}

class ControllerGui : public QObject
{
    Q_OBJECT

public:
    /**
     * @brief Constructor for the Controller class.
     * @param model A pointer to the Model object representing the game logic.
     */
    explicit ControllerGui(Model*model, QObject *parent = nullptr);
    ~ControllerGui();

public slots:
    /**
     * @brief slot function to handle the logic of the game
     * @param pos Position of the game board
     */
    void play(Position pos);

signals:
    /**
     * @brief emit a signal erreur when the picker can't be put
     */
    void error();
    /**
     * @brief emit a signal next player to indicate the state next player
     */
    void signalNextPlayer();
    /**
     * @brief emit a signal when state is game over.
     */
    void signalGameOver();
private:
    Ui::ControllerGui *ui;
    /**
     * \brief model of the game
     */
    Model* model;

};

#endif // CONTROLLERGUI_H
