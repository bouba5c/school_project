#ifndef PLAYAGAIN_H
#define PLAYAGAIN_H

#include <QWidget>
#include "gamemodedialog.h"

class GameModeDialog;

namespace Ui {
class PlayAgain;
}

/**
 * @class PlayAgain
 * @brief A widget that allows the player to play the game again or quit
 * @author balde boubacar 55870
*/

class PlayAgain : public QWidget
{
    Q_OBJECT

public:
    /**
     * @brief Constructor for the PlayAgain class.
     * @param ScorePlayer1 score of the player1
     * @param ScorePlayer2 score of the second player
     * @param parent The parent widget. Default is nullptr.
     */

    explicit PlayAgain(int scorePlayer1,int scorePlayer2 ,QWidget *parent = nullptr);

    /**
     * @brief Destructor for the PlayAgain class.
     */

    ~PlayAgain();

public slots:
    /**
     * @brief Slot function for the "Play Again" action.
     */
    void on_playAgain();

    /**
     * @brief Slot function for the "Quit" action.
     */
    void action_quit();
private:
    /**
     * \brief  user interface of the widget play again
     */
    Ui::PlayAgain *ui;
    GameModeDialog *gameModeD;
    /**
     * \brief score of the player 1
     */
    int scorePlayer1;
    /**
     * \brief score of the player 2
     */
    int scorePlayer2;


};

#endif // PLAYAGAIN_H
