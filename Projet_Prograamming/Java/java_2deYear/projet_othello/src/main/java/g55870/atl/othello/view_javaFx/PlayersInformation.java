package g55870.atl.othello.view_javaFx;

import g55870.atl.othello.model.Model;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
/**
 * The PlayersInformation class represents a visual component that displays the players' scores in the Othello game.
 * It extends HBox and is responsible for showing the scores of both players.
 */
public class PlayersInformation extends HBox
{
    private final Label blackScore;
    private final Label whiteScore;
    private final Model model;
    /**
     * Constructs a PlayersInformation object with the specified Model.
     *
     * @param model The Model containing game data.
     */
    public PlayersInformation(Model model){
        this.model = model;
        Label player1 =labelStyle(" joueur 1  : ",1,"black",Color.WHITE);
        blackScore = labelStyle(String.valueOf(model.getBlackScore()),2,"black",Color.WHITE);
        Label player2 =labelStyle(" joueur 2  : ",1,"white",Color.BLACK);
        whiteScore = labelStyle(String.valueOf(model.getWhiteScore()),2,"white",Color.BLACK);
        setAlignment(Pos.CENTER);
        setSpacing(3);
        getChildren().addAll(player1,blackScore, player2,whiteScore);
    }
    /**
     * Creates a styled label with the specified parameters.
     *
     * @param labelName The name of the label.
     * @param target    The target type of label (1 for regular, 2 for circular).
     * @param color     The background color of the label.
     * @param paint     The text color of the label.
     * @return The styled label.
     */
    private Label labelStyle(String labelName,int target,String color,Color paint)
    {
        if(target==1)
        {
            Label label = new Label(labelName);
            label.setFont(new Font(20));
            label.setStyle("-fx-background-color : " + color);
            label.setTextFill(paint);

            return label;
        }
        else{
            Label label = new Label(labelName);

            label.setShape(new Circle(1));
            label.setStyle("-fx-background-color : " + color);
            label.setFont(new Font(20));
            label.setTextFill(paint);

            return label;

        }
    }
    /**
     * Updates the scores displayed in the PlayersInformation component.
     */
    public void update()
    {
        blackScore.setText(String.valueOf(model.getBlackScore()));
        whiteScore.setText(String.valueOf(model.getWhiteScore()));

    }
}
