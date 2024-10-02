#ifndef HOWTOPLAY_H
#define HOWTOPLAY_H

#include <QWidget>
#include <QIcon>


namespace Ui {
class HowToPlay;
}
/**
 * @brief The HowToPlay class represents a widget for displaying rule of the game
 *
 * @details This class provides a user interface for explaing the rule of the game etc
 * @author balde boubacar 55870
 */
class HowToPlay : public QWidget
{
    Q_OBJECT

public:
    /**
     * @brief Constructs a HowToPlay widget. it will show the rules of the game
     * @param parent The parent widget, if any.
     */
    explicit HowToPlay(QWidget *parent = nullptr);

    /**
     * @brief Destructor for the HowToPlay class.
     */
    ~HowToPlay();

private:
    Ui::HowToPlay *ui;
};

#endif // HOWTOPLAY_H
