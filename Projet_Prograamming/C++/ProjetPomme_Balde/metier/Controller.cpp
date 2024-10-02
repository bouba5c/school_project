#include "Controller.h"
#include <iostream>

Controller::Controller(Model &model , View &view) : model(model) ,view(view)
{

}
void Controller::play()
{
    view.displayWelcome();
    bool gameMode;
    while(true)
    {
        switch(model.getState())
        {
        case State::NOT_STARTED:

            gameMode = view.askGameMode();
            model.start(gameMode);
            cout << "Version " <<( (model.getGameMode()==State::GAME_MODE_ALLONGE) ? "allongé" : "normal" ) << endl;
            view.displayGame();
            break;

        case State::PLACE_PICKER:
            try{
                Position pos = view.askPosition();

                if(model.canPickerBeBut(pos)){
                    model.putPicker(pos);

                }else{
                    view.displayError("Veuillez respecter les regles : Le cuilleur ne peut pas etre placer ici");
                }

            }catch(invalid_argument &e)
            {
                cerr << e.what();
            }
            break;

        case State::TURN_END:
            model.nextPlayer();
            view.displayGame();


            break;
        case State::GAME_OVER:
            view.displayWinner();
            bool playAgain = view.askPlayAgain();
            if (playAgain) {
                model.resetBagCapacity();
                gameMode = view.askGameMode();
                model.start(gameMode);
                cout << "Version " << ((model.getGameMode() == State::GAME_MODE_ALLONGE) ? "allongé" : "normal") << endl;
                view.displayGame();
            } else {
                return exit(0);
            }
            break;
        }




    }
}

