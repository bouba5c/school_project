#include "Board.h"

Board::Board(int longeur, int largeur) : height(longeur), width(largeur), tiles(height, std::vector<std::shared_ptr<Tile>>(width, nullptr))
{
}
std::vector<std::shared_ptr<Tile>>& Board::operator[](int index)
{
    if (index < 0 || index >= getHeight())
    {
        throw std::out_of_range("Index out of range \n");
    }

    return tiles[index];
}
int Board::getSize()
{
    return getHeight() * getWidth();
}
int Board::getWidth()
{
    return this->width;
}
int Board::getHeight()
{
    return this->height;

}
bool Board::isInside(Position &pos)
{
    return pos.getRow() >=0 && pos.getRow() < getHeight() && pos.getColumn()>=0 && pos.getColumn()<getWidth();
}
bool Board::canBeput( Position & pos)
{
    if(getTile(pos) == nullptr)
    {
        return false;
    }
    return checkPickerMov(pos);
}
bool Board::checkPickerMov(Position &pos)
{
    bool leftPicker = pos.getRow() == getPickerPosition().getRow() && pos.getColumn() < getPickerPosition().getColumn();
    bool rightPicker = pos.getRow() == getPickerPosition().getRow() && pos.getColumn() < getWidth();
    bool upPicker = pos.getColumn() == getPickerPosition().getColumn() && pos.getRow() < getPickerPosition().getRow();
    bool downPicker = pos.getColumn() == getPickerPosition().getColumn() && pos.getRow() < getHeight();
    if(!(leftPicker || rightPicker||upPicker || downPicker))
    {
        return false;
    }
    if(pos.getRow() == getPickerPosition().getRow() && pos.getColumn() == getPickerPosition().getColumn())
    {
        return false;

    }

    return (leftPicker || rightPicker||upPicker || downPicker);

}
Position Board::getPickerPosition()

{
    for (int i = 0; i < getHeight(); i++)
    {
        for (int j = 0; j < getWidth(); j++)
        {
            if (tiles[i][j]!=nullptr && tiles[i][j]->getIsPicker())
            {
                return Position(i, j);
            }
        }
    }
    return Position(-1, -1);
}

std::shared_ptr<Tile>& Board::getTile(Position& pos)
{

    return tiles[pos.getRow()][pos.getColumn()];
}
void Board::put(std::shared_ptr<Tile>& tile, Position& pos)
{

    tiles[pos.getRow()][pos.getColumn()] = tile;
}

