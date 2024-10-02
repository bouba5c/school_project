
#include "catch.hpp"
#include "Board.h"
#include "Picker.h"
#include "Apple.h"

TEST_CASE("Testing Board")
{
    SECTION("Testing Board 5x5  ")
    {
        std::unique_ptr<Board> board = std::make_unique<Board>(5,5);
        REQUIRE(board->getHeight() == 5);
        REQUIRE(board->getWidth() == 5);
        REQUIRE(board->getSize() == 25);
        Position posValide(1,1);
        REQUIRE(board->isInside(posValide) == 1);
        Position posInvalide(9,1);
        REQUIRE(board->isInside(posInvalide) == 0);
        // mettre le picker a (0:0)
        Position pickerPos(0,0);
        std::shared_ptr<Tile> picker = std::make_shared<Picker>();

        board->put(picker,pickerPos);
        //should be true because the tile is a picker

        REQUIRE(board->getTile(pickerPos) -> getIsPicker() == 1 );
        //should be false because is already in the position
        REQUIRE_THROWS( board->canBeput(pickerPos));
        Position positionAdroite(1,0);
        //should be false because cannot be put in a null spot
        REQUIRE_THROWS( board->canBeput(positionAdroite));
        //add a green apple down the picker position
        Position downPos(1,0);
        std::shared_ptr<Tile> greenApple = std::make_shared<Apple>(2,Color::GREEN);
        board->put(greenApple,downPos);
        REQUIRE(board->getTile(downPos) -> getIsPicker() == 0 );
        REQUIRE(board->getTile(downPos) -> getIsapple() == 1 );
        REQUIRE(board->canBeput(downPos) ==1);
        //add a red apple in a diag
        Position diagPos(1,1);
        std::shared_ptr<Tile> redApple = std::make_shared<Apple>(2,Color::RED);
        board->put(redApple,diagPos);
        //should be false because picker can only move vertically or horizontally
        REQUIRE_THROWS(board->canBeput(diagPos));


    }

    SECTION("Testing Board 3x8  ")
    {
        std::unique_ptr<Board> board = std::make_unique<Board>(3,8);
        REQUIRE(board->getHeight() == 3);
        REQUIRE(board->getWidth() == 8);
        REQUIRE(board->getSize() == 24);
        Position posValide(1,1);
        REQUIRE(board->isInside(posValide) == 1);
        Position posInvalide(1,9);
        REQUIRE(board->isInside(posInvalide) == 0);

    }

}

