
#include "catch.hpp"
#include "Tile.h"
#include "Apple.h"
#include "Picker.h"
#include <memory>

TEST_CASE("Testing  Tile")
{
    SECTION("Testing Red apple Tile ")
    {
        std::shared_ptr<Tile> apple = std::make_shared<Apple>(2, Color::RED);
        REQUIRE(apple-> getValue() ==2 );
        REQUIRE(apple -> getColor() == Color::RED );
        REQUIRE(apple -> getIsapple() == true );


    }

    SECTION("Testing Green apple Tile ")
    {
        std::shared_ptr<Tile> apple = std::make_shared<Apple>(1, Color::GREEN);
        REQUIRE(apple-> getValue() ==1 );
        REQUIRE(apple -> getColor() == Color::GREEN );
        REQUIRE(apple -> getColor() != Color::RED );

        REQUIRE(apple -> getIsapple() == true );


    }
    SECTION("Testing Picker  ")
    {
        std::shared_ptr<Tile> picker = std::make_shared<Picker>();
        REQUIRE(picker-> getValue() == 0 );
        REQUIRE(picker -> getColor() == Color::NONE );
        REQUIRE(picker -> getIsapple() == false );
        REQUIRE(picker -> getIsPicker() == true );


    }

}

