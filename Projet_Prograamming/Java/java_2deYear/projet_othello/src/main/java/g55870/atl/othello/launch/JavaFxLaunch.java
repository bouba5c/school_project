package g55870.atl.othello.launch;
import g55870.atl.othello.controller_javaFx.Controller;
import g55870.atl.othello.model.Game;
import g55870.atl.othello.model.Model;
import g55870.atl.othello.view_javaFx.MainMenuScene;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Launches the Othello game with javaFx application .
 */
public class JavaFxLaunch extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        Model model = new Game();
        MainMenuScene scene = new MainMenuScene(500,350);
        Controller controller = new Controller(model,scene,primaryStage);
        controller.start();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Othello game");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
