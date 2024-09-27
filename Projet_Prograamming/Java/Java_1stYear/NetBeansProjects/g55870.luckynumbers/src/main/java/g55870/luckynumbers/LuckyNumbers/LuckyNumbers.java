package g55870.luckynumbers.LuckyNumbers;

import g55870.luckynumbers.controller.Controller;
import g55870.luckynumbers.model.Game;
import g55870.luckynumbers.model.Model;
import g55870.luckynumbers.view.MyView;

public class LuckyNumbers {

    /**
     * The main method who will launch the game with the strict minimun.
     */
    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }

}
