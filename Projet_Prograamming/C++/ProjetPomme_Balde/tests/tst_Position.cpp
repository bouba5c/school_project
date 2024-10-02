
#include "catch.hpp"
#include "Position.h"

TEST_CASE("Testing  Position")
{
    SECTION("Testing ROW position ")
    {
        Position pos(3,2);
        REQUIRE(pos.getRow() ==3 );


    }

    SECTION("Testing  COL Position ")
    {
        Position pos(3,2);
        REQUIRE(pos.getColumn() == 2);

    }

}

