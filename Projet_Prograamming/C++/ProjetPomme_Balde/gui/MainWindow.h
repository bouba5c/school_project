#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QMessageBox>

#include <QLabel>
#include <QString>
#include <QHBoxLayout>
#include <QPushButton>
#include <QGridLayout>
#include <QMessageBox>
#include <QPixmap>
#include "Model.h"
#include "ControllerGui.h"
#include "PlayAgain.h"

class ControllerGui;
class PlayAgain;

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE
/**
 * @class MainWindow
 * @brief The main window of the game mode application.
 *
 * @details This class represents the main window of the game mode application. It provides
 * the game board, player information, buttons, and various displays related to
 * the game's progress and status.
 * @author balde boubacar 55870
 */
class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    /**
     * @brief Constructor for the MainWindow class.
     * @param mode The game mode (normal or version).
     * @param model A pointer to the game's model.
     * @param parent The parent widget. Default is nullptr.
     */
    MainWindow(bool mode,Model* model, QWidget *parent = nullptr);
    /**
     * @brief Destructor for the MainWindow class.
     */
    ~MainWindow();
    /**
     * @brief update the game board display
     */
    void updateBoard();

public slots:
    /**
     * @brief Slot function for the help button click event.
     */
    void on_helpButton();
    /**
     * @brief Slot function for displaying an error message.
     */
    void displayError();
    /**
     * @brief Slot function for displaying the winner of the game.
     */
    void displayWinner();
    /**
     * @brief Slot function for displaying all players information.
     */
    void displayPlayerWithBag();

private:
    Ui::MainWindow *ui;
    /**
     * \brief rage quit button
     */
    QPushButton * boutonRageQuit;
    /**
     * \brief help button
     */
    QPushButton * helpButton;
    /**
     * \brief central Widget of the mainWindow
     */
    QWidget *centralWidget;
    /**
     * \brief grid of the mainWindow
     */
    QGridLayout * grid_;
    /**
     * \brief buttons of the grid corresponding to the game board
     */
    std::vector<std::vector<QPushButton*>> buttons;
    QVBoxLayout *centerLayout;
    ControllerGui *controller;
    /**
     * \brief label of player 1 containing his info
     */
    QLabel* playerInfo1;
    /**
     * \brief label of player 2 containing his info
     */
    QLabel* playerInfo2;
    /**
     * \brief label of the first player rondomly choose
     */
    QLabel* firstPlayer;
    /**
     * \brief model of the game
     */
    Model* model;
    /**
     * \brief mode of the game 1 if "allongé"
     *                         0 if "normal"
     */
    bool mode;

    PlayAgain *askPlayAgain ;




};
#endif // MAINWINDOW_H
