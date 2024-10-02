
#include "catch.hpp"
#include "Bag.h"

TEST_CASE("Testing  Bag")
{
    SECTION("Testing RED BAG  ")
    {
        Bag bag(Color::RED);
        REQUIRE(bag.getColor() == Color::RED);
        REQUIRE(bag.getCapacity() ==0);
        REQUIRE(bag.isFull() ==0);
        int value  = 2;
        bag.setCapacity(value);
        REQUIRE(bag.getCapacity() ==value);


    }

    SECTION("Testing GREEN BAG  ")
    {
        Bag bag(Color::GREEN);
        REQUIRE(bag.getColor() == Color::GREEN);
        REQUIRE(bag.getCapacity() ==0);
        REQUIRE(bag.isFull() ==0);
        int value  = 11;
        bag.setCapacity(value);
        REQUIRE(bag.getCapacity() == value);
        REQUIRE(bag.isFull() == 1);


    }

}

