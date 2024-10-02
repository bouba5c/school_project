#include "gamemodedialog.h"
#include "ui_gamemodedialog.h"
#include <QDebug>

GameModeDialog::GameModeDialog(int scorePlayer1,int scorePlayer2,QWidget *parent) :
    QWidget(parent),
    ui(new Ui::GameModeDialog),scorePlayer1(scorePlayer1),scorePlayer2(scorePlayer2)
{
    ui->setupUi(this);
    setWindowTitle("22 Pommes");
    setFixedSize(400, 320);
    QPixmap background(":image/images/background.png");
    background = background.scaled(this->size(), Qt::IgnoreAspectRatio);
    QPalette palette;
    palette.setBrush(QPalette::Window, background);
    setPalette(palette);
    connect(ui->button_normal, &QPushButton::clicked, this, &GameModeDialog::action_normalClicked);
    connect(ui->button_allonge, &QPushButton::clicked, this, &GameModeDialog::action_versionClicked);
    connect(ui->quit_button, &QPushButton::clicked, this, &GameModeDialog::action_quit);
    connect(ui->button_help, &QPushButton::clicked, this, &GameModeDialog::action_howToPlay);
}

GameModeDialog::~GameModeDialog()
{
    delete ui;
}


void GameModeDialog::action_quit()
{
    hide();
    QApplication::quit();
}


void GameModeDialog::action_normalClicked()
{
    Model *model = new Model(scorePlayer1,scorePlayer2);
    model->start(false);
    MainWindow *mainwindow = new MainWindow(false,model);
    mainwindow->show();
    close();
}


void GameModeDialog::action_versionClicked()
{
    Model *model = new Model(scorePlayer1,scorePlayer2);
    model->start(true);
    MainWindow *mainwindow = new MainWindow(true,model);
    mainwindow->show();
    close();
}

void GameModeDialog::action_howToPlay()
{
    HowToPlay *how = new HowToPlay;
    how->show();
}


