#include "controllergui.h"

#include "ui_controllergui.h"

ControllerGui::ControllerGui(Model*model,QObject *parent) :
    QObject(parent),
    ui(new Ui::ControllerGui),model(model)
{

}


ControllerGui::~ControllerGui()
{
    delete ui;
}


void ControllerGui::play(Position pos)
{
    switch(model->getState())
    {
    case State::PLACE_PICKER:

        if(model->canPickerBeBut(pos))
        {

            model->putPicker(pos);
            if(model->getState()==State::GAME_OVER)
            {
                emit signalGameOver();
            }
            emit signalNextPlayer();

        }
        else
        {
            emit error();
        }
        break;
    case State::TURN_END:
        model->nextPlayer();
        break;
    case State::GAME_OVER:
        emit signalGameOver();

    }
}



