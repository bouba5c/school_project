package g55870.atl.othello.launch;

import g55870.atl.othello.controller_console.*;
import g55870.atl.othello.model.*;
import g55870.atl.othello.view_console.MyView;
import g55870.atl.othello.view_console.View;
/**
 * Launches the Othello game in the prompt.
 */
public class ConsoleLaunch {
    public static void main(String[] args)  {
        Model model = new Game();
        View view = new MyView(model);
        Controller controller = new Controller(model, view);
        controller.start();

    }
}
