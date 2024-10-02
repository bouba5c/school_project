#ifndef GAMEMODEDIALOG_H
#define GAMEMODEDIALOG_H

#include <QWidget>
#include "Model.h"
#include "MainWindow.h"
#include "HowToPlay.h"
class MainWindow;
class PlayAgain;

namespace Ui {
class GameModeDialog;
}

/**
 * @class GameModeDialog
 * @brief A widget for displaying mode selection or rule of the game
 * @author balde boubacar 55870
*/
class GameModeDialog : public QWidget
{
    Q_OBJECT

public:
    /**
     * @brief Constructor for the GameModeDialog class.
     * @param parent The parent widget.
     */

    explicit GameModeDialog(int scorePlayer1,int scorePlayer2,QWidget *parent = nullptr);

    ~GameModeDialog();

public slots:
    /**
     * @brief Slot function for quitting the application.
     */

    void action_quit();
    /**
     * @brief Slot function for starting the game in normal mode 5x5.
     */

    void action_normalClicked();
    /**
     * @brief Slot function for starting the game in version mode 3X8.
     */
    void action_versionClicked();

    /**
     * @brief Slot function for accessing help information.
     */

    void action_howToPlay();

private:
    Ui::GameModeDialog *ui;
    /**
     * \brief score of the player1
     */
    int scorePlayer1;
    /**
     * \brief score of the player2
     */
    int scorePlayer2;


};

#endif // GAMEMODEDIALOG_H
