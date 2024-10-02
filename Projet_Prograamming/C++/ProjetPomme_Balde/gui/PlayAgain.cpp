#include "PlayAgain.h"
#include "ui_PlayAgain.h"

PlayAgain::PlayAgain(int scorePlayer1, int scorePlayer2, QWidget *parent) :
    QWidget(parent),
    ui(new Ui::PlayAgain) ,scorePlayer1(scorePlayer1),scorePlayer2(scorePlayer2)
{
    ui->setupUi(this);
    setWindowTitle("22 Pommes");
    setFixedSize(400, 320);
    QPixmap background(":image/images/background.png");
    background = background.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Window, background);
    setPalette(palette);
    connect(ui->play_button, &QPushButton::clicked, this, &PlayAgain::on_playAgain);
    connect(ui->quit_button, &QPushButton::clicked, this, &PlayAgain::action_quit);


}

PlayAgain::~PlayAgain()
{
    delete ui;
}


void PlayAgain::on_playAgain()
{
    gameModeD = new GameModeDialog(scorePlayer1,scorePlayer2) ;
    gameModeD->show();
    close();

}

void PlayAgain::action_quit()
{

    QApplication::closeAllWindows();
}
