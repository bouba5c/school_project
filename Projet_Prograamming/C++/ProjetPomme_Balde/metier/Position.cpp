#include "Position.h"

Position::Position(int x,int y) : row(x),col(y)
{

}
int Position::getRow(){return row;}
int Position::getColumn(){return col;}
