#include "gamemodedialog.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    GameModeDialog game(0,0);
    game.show();
    return a.exec();
}

