#include "View.h"
#include <limits>

View::View(Model& model) : model(model){

}

void View::displayWelcome()
{
    cout<< setw(4) << "============================= "<< endl;
    cout<< setw(4) << "|| Bienvenue au 22 Pommes  ||"<< endl;
    cout<< setw(4) << "|| codé par baldé boubacar ||"<< endl;
            cout<< setw(4) << "============================= "<< endl;

}

void View::displayGame()
{

        displayBoard();
        displayCurrentPlayer();
}

void View::displayColumn()
{

    for(int i=0 ; i <model.getBoardWidth()+1 ;i++)
    {
        if(i==0){
            cout << setw(3) << " ";

        }
        else{
            cout << setw(2) <<  i << setw(2) << " " ;
        }

    }



    cout << endl;
}
void View::displayBoard()
{
#ifdef _WIN32
    system("cls");  //delete prompt when windows
#else
    system("clear");  //delete prompt when linux
#endif

    displayColumn();
    cout << setw(4) << "============================= " << endl;

    for (int row = 0; row < this->model.getBoardHeigh(); row++)
    {
        cout << (row + 1) << setw(2) << "||";
        for (int col = 0; col < this->model.getBoardWidth(); col++)
        {
            Position pos(row, col);
            std::shared_ptr<Tile>tile = this->model.getTile(pos);

            if (tile == nullptr)
            {
                cout << setw(3) << " -- ";
            }

            else
            {
                string tileWithValue = " ";
                tileWithValue += to_string(tile->getValue());
                tileWithValue += tile->getName();
                cout << setw(3) << tileWithValue;
            }
        }

        cout << endl;
    }
    cout << endl;
}











void View::displayWinner()
{
    int winner = model.getWinner();
    if(winner == -1){
        cout << "Egalité : il n y a pas de vainqueur";

    }
    else{
        cout << "Le vainqueur est le joueur: " << (winner+1)<< endl ;
        cout << "Score du joueur " << (winner+1) <<" : " << (model.getPlayers().at(winner)->getScore()) << endl ;


    }


}
Position View::askPosition()
{
    int row, col;
    std::cout << "choix : " << std::endl;

    while (true)
    {
        cout << "ligne : ";
        while (!(cin >> row))
        {

            displayError("Format invalide : veuillez entrer un nombre entier pour la ligne.");
            cout << "ligne : ";

            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }

        cout << "colonne : ";
        while (!(cin >> col))
        {
            cout << "rapelle de la ligne : " << to_string(row) <<endl ;

            displayError("Format invalide : veuillez entrer un nombre entier pour la colonne.");
            cout << "colonne : ";

            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }


        return Position(row - 1, col - 1);

    }
}


void  View::displayError(string message)
{
    cerr << message  <<endl;
}
void View::displayCurrentPlayer()
{
    cout << "Joueur  : " << (model.GetCurrentPlayer()+1) << "  " ;
    cout << "\033[31m Red bag\033[0m:" << setw(3) << model.getPlayer()->getRedBag()->getCapacity() << "  " ;
    cout  << "\033[32m Green bag\033[0m:  "  <<model.getPlayer()->getGreenBag()->getCapacity() << " |  " ;
    int next = (model.GetCurrentPlayer() ==1) ? 0 : 1;

    cout << "Joueur  : " << (next+1) << "  " ;
    cout << "\033[31m Red bag\033[0m:" << setw(3) << model.getPlayers().at(next)->getRedBag()->getCapacity() << "  " ;
    cout  << "\033[32m Green bag\033[0m:  "  <<model.getPlayers().at(next)->getGreenBag()->getCapacity() << endl ;
}

bool View::askGameMode()
{
    std::cout << "Choix du jeu : 0 pour la version normale ou 1 pour la version allongée" << std::endl;

        while (true)
    {
        try
        {
            int choice;

            cin >> choice;

            if(choice < 0 || choice > 1 || std::cin.fail())
            {
                throw invalid_argument("Format invalide : entrer 0 pour la version normale ou 1 pour la version allongée. \n");
            }

            return static_cast<bool>(choice);
        }
        catch (invalid_argument &e)
        {
            std::cerr << e.what() << std::endl;
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        }
    }


}
bool View::askPlayAgain()
{
    std::cout << "voulez vous rejouer : oui = 1 , non = 0" << std::endl;

    while(true)
    {
        try
        {
            int choice;

            cin >> choice;

            if(choice < 0 || choice > 1 || std::cin.fail())
            {
                throw invalid_argument("Format invalide : oui = 1 , non = 0  \n");
            }

            return static_cast<bool>(choice);
        }
        catch (invalid_argument &e)
        {
            std::cerr << e.what() << std::endl;
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        }
    }


}
