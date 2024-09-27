package g55870.atl.othello.view_javaFx;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class MainMenuScene extends Scene {
    private final MainMenuVbox mainMenu;

    public MainMenuScene(int v1,int v2) {
        super(new StackPane(), v1, v2);
        StackPane stackPane = (StackPane) getRoot();
        this.mainMenu = new MainMenuVbox();
        Image backgroundImage = new Image("reversiMainMenuBackground.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(widthProperty());
        backgroundImageView.fitHeightProperty().bind(heightProperty());
        stackPane.getChildren().addAll(backgroundImageView,mainMenu);
    }

    public MainMenuVbox getMainMenu() {
        return mainMenu;
    }

}
