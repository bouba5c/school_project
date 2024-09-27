package g55870.atl.othello.view_javaFx;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
/**
 * The HelpScene class represents a scene in the Othello game that displays the game rules.
 * It extends Scene and contains an image view displaying the rules image.
 */
public class HelpScene extends Scene {

    /**
     * Constructs a HelpScene object that displays the game rules.
     */
    public HelpScene() {
        super(new StackPane());
        StackPane stackPane = (StackPane) getRoot();
        Image helpImage = new Image("othelloRule.png");
        ImageView helpImageView = new ImageView(helpImage);
        stackPane.getChildren().add(helpImageView);
    }
}
