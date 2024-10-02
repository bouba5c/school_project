#include "HowToPlay.h"
#include "ui_HowToPlay.h"

HowToPlay::HowToPlay(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::HowToPlay)
{
    ui->setupUi(this);
    setWindowTitle("comment jouer");
    setFixedSize(750, 820);
    QPixmap background(":/image/images/But du jeu (1).png");
    background = background.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Window, background);
    setPalette(palette);
}

HowToPlay::~HowToPlay()
{
    delete ui;
}
