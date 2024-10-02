#include "MainWindow.h"
#include "ui_MainWindow.h"


MainWindow::MainWindow(bool mode,Model*model ,QWidget *parent)
    : QMainWindow(parent), model(model),mode(mode)
{
    if(mode){
         setWindowTitle("22 pomme allongé");
   }
    else{
         setWindowTitle("22 pomme normal");
    }
    controller = new ControllerGui(model);

     boutonRageQuit = new QPushButton("Rage quit",this);
     boutonRageQuit->setFixedSize(100,30);
     connect(boutonRageQuit, &QPushButton::clicked, qApp, &QApplication::exit);

     helpButton = new QPushButton("aide",this);
     helpButton->setFixedSize(100,30);
     connect(helpButton, &QPushButton::clicked, this, &MainWindow::on_helpButton);
     firstPlayer = new QLabel(this) ;
     playerInfo1 = new QLabel(this);
     playerInfo2 = new QLabel(this);
     setFixedSize(800, 720);
     QPixmap background(":/image/images/backgroundGaming.jpg");
     background = background.scaled(this->size());
     QPalette palette;
     palette.setBrush(QPalette::Window, background);
     setPalette(palette);
     int boardHeight = model->getBoardHeigh();
     int boardWidth = model->getBoardWidth();
     buttons.resize(boardHeight);
     grid_ = new QGridLayout;
     firstPlayer->setText("1er joueur tiré au hasard : " +QString::number(model->getPlayers().at(model->GetCurrentPlayer())->getId()));

     for ( int row = 0; row < boardHeight; row++)
     {
         buttons[row].resize(boardWidth);

         for ( int col = 0; col < boardWidth; col++)
         {

             buttons[row][col] = new QPushButton;
             buttons[row][col]->setFixedSize(90,90);
             buttons[row][col]->setFlat(true);
             Position pos(row,col);
             connect(buttons[row][col], &QPushButton::clicked, this,  [pos, this] { controller->play(pos);
            });
             grid_->addWidget(buttons[row][col], row, col);
        }
   }
     displayPlayerWithBag();

     connect(controller, &ControllerGui::error, this,  &MainWindow::displayError);
     connect(controller, &ControllerGui::signalNextPlayer, this, &MainWindow::displayPlayerWithBag);
     connect(controller, &ControllerGui::signalGameOver, this, &MainWindow::displayWinner);

     displayPlayerWithBag();

     centerLayout = new QVBoxLayout(this);
     centerLayout->addStretch();
     centerLayout->addLayout(grid_);
     centerLayout->addStretch();
     centerLayout->addWidget(boutonRageQuit);
     centerLayout->addWidget(helpButton);
     centerLayout->addWidget(firstPlayer);
     centerLayout->addWidget(playerInfo1);
     centerLayout->addWidget(playerInfo2);
     centralWidget = new QWidget(this);
     centralWidget->setLayout(centerLayout);
     setCentralWidget(centralWidget);

}


MainWindow::~MainWindow()
{
   delete ui;

}

void MainWindow::displayWinner()
{
     int winner = model->getWinner();
     int idWinner = model->getPlayers().at(winner)-> getId();
     if(winner == -1)
    {
         QMessageBox::information(this, "Égalité", "Il n'y a pas de vainqueur.");

    }
    else
     {
         QString winnerMessage = "Le vainqueur est le joueur : " + QString::number(idWinner) +
                                "\nScore : " + QString::number(model->getPlayers().at(winner)->getScore());

         QMessageBox::information(this, "Vainqueur", winnerMessage);
     }
     int scorePlayer1 = model->getPlayers().at(0)->getScore();
     int scorePlayer2 = model->getPlayers().at(1)->getScore();
     askPlayAgain = new PlayAgain(scorePlayer1,scorePlayer2);
     askPlayAgain->show();
     this->close();

}

void MainWindow::on_helpButton()
{
     HowToPlay *how = new HowToPlay;
     how->show();
}



void MainWindow::updateBoard()
{
     QPixmap ra1(":/image/images/RedApple1.png");
     QPixmap ra2(":/image/images/redApple2.png");
     QPixmap ra3(":/image/images/RedApple3.png");
     QPixmap ra5(":/image/images/redApple5.png");

     QPixmap ga1(":/image/images/GreenApple1.png");
     QPixmap ga2(":/image/images/GreenApple2.png");
     QPixmap ga3(":/image/images/GreenApple3.png");
     QPixmap ga5(":/image/images/GreenApple5.png");
     int size = 120;
     ra1 = ra1.scaled(size,size,Qt::KeepAspectRatio);
     ra2 = ra2.scaled(size,size,Qt::KeepAspectRatio);
     ra3 = ra3.scaled(size,size,Qt::KeepAspectRatio);
     ra5 = ra5.scaled(size,size,Qt::KeepAspectRatio);
     ga1 = ga1.scaled(size,size,Qt::KeepAspectRatio);
     ga2 = ga2.scaled(size,size,Qt::KeepAspectRatio);
     ga3 = ga3.scaled(size,size,Qt::KeepAspectRatio);
     ga5 = ga5.scaled(size,size,Qt::KeepAspectRatio);

     QPixmap picker(":/image/images/picker.png");

     picker = picker.scaled(size,size,Qt::KeepAspectRatio);

   for (int row = 0; row < model->getBoardHeigh(); row++)
   {
        for (int col = 0; col < model->getBoardWidth(); col++)
        {
            Position pos(row, col);
            std::shared_ptr<Tile> tile = model->getTile(pos);
            if (tile == nullptr)
            {
                buttons[row][col]->setIcon(QIcon());
                buttons[row][col]->setEnabled(false);

            }
            else
            {
                if(tile->getIsapple()&&  tile->getColor()==Color::RED &&tile->getValue() ==1)
                {
                    buttons[row][col]->setIcon(ra1);

                }
                if(tile->getIsapple() && tile->getColor()==Color::RED && tile->getValue() ==2)
                {
                    buttons[row][col]->setIcon(ra2);


                }
                if(tile->getIsapple() && tile->getColor()==Color::RED && tile->getValue() ==3)
                {
                    buttons[row][col]->setIcon(ra3);

                }
                if(tile->getIsapple() && tile->getColor()==Color::RED && tile->getValue() ==5)
                {
                    buttons[row][col]->setIcon(ra5);

                }
                if(tile->getIsapple() && tile->getColor()==Color::GREEN && tile->getValue() ==1)
                {
                    buttons[row][col]->setIcon(ga1);

                }

                if(tile->getIsapple() && tile->getColor()==Color::GREEN  && tile->getValue() ==2)
                {
                    buttons[row][col]->setIcon(ga2);

                }
                if(tile->getIsapple()&& tile->getColor()==Color::GREEN&& tile->getValue() ==3)
                {
                    buttons[row][col]->setIcon(ga3);

                }
                if(tile->getIsapple()&& tile->getColor()==Color::GREEN && tile->getValue() ==5)
                {
                    buttons[row][col]->setIcon(ga5);

                }
                if(tile->getIsPicker())
                {
                    buttons[row][col]->setIcon(picker);

                }

            }
            buttons[row][col]->setIconSize(buttons[row][col]->size());

        }
   }

}



void MainWindow::displayError()
{

    QMessageBox::critical(this,"erreur" , "Position invalide : Le ceuilleur ne peut pas etre placé ici");
}



void MainWindow::displayPlayerWithBag()
{
   QString player1 = "Joueur : " + QString::number(model->getPlayers().at(0)->getId()) + "  " +
                               "<span style='color:red;'>RED BAG</span>:" +
                               QString::number(model->getPlayers().at(0)->getRedBag()->getCapacity()) + "  " +
                               "<span style='color:green;'>GREEN BAG</span>:" +
                               QString::number(model->getPlayers().at(0)->getGreenBag()->getCapacity()) + " " +
                               "Score : " + QString::number(model->getPlayers().at(0)->getScore());

    QString player2 = "Joueur : " + QString::number(model->getPlayers().at(1)->getId()) + "  " +
                  "<span style='color:red;'>RED BAG</span>:" +
                  QString::number(model->getPlayers().at(1)->getRedBag()->getCapacity()) + "  " +
                  "<span style='color:green;'>GREEN BAG</span>:" +
                  QString::number(model->getPlayers().at(1)->getGreenBag()->getCapacity()) + " "
                  "Score : " + QString::number(model->getPlayers().at(1)->getScore());

     playerInfo1->setText(player1);
     playerInfo2->setText(player2);
     updateBoard();


}









