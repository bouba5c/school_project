
#include "catch.hpp"
#include "Player.h"

TEST_CASE("Testing  Player")
{
    SECTION("Testing Player 1 default  ")
    {
        Player player(2);
        REQUIRE(player.getId() ==2);
        REQUIRE(player.getScore() ==0);
        REQUIRE(player.bagsSize() ==2);
        REQUIRE(player.getGreenBag()->isFull() ==0);
        REQUIRE(player.getRedBag()->isFull() ==0);



    }

    SECTION("Testing Player with content ")
    {
        Player player(2);
        REQUIRE(player.getId() ==2); //get id
        REQUIRE(player.getScore() ==0); //get the score before set
         player.setScore(1);
        REQUIRE(player.getScore() ==1); //get the score after set
        REQUIRE(player.bagsSize() ==2); //get bags
        player.getGreenBag()-> setCapacity(6); //set bag capacity to 6
        REQUIRE(player.getGreenBag()->isFull() ==0); // check if its full
        player.getGreenBag()-> setCapacity(5);
        REQUIRE(player.getGreenBag()->isFull() ==1); //recheck capacity


    }

}

