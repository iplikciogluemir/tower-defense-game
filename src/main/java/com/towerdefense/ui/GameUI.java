package main.java.com.towerdefense.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameUI {

    private Scene endScreen() {

        StackPane pane = new StackPane();

        Color backColor = Color.rgb(250, 241, 218);
        pane.setBackground(new Background(new BackgroundFill(backColor, CornerRadii.EMPTY, Insets.EMPTY)));

        Button btBack = new Button("Back to Main Menu");
        btBack.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: #eed399;" +
                "-fx-pref-width: 260px;" +
                "-fx-pref-height: 60px ;" +
                "-fx-background-color: #f2d79d;" +
                "-fx-text-fill: #582b0d;" +
                ";-fx-font-weight: bold ;" +
                " -fx-font-size: 16px;");

        Label label = new Label("Game Over!", btBack);
        label.setStyle("-fx-text-fill: #582b0d; -fx-font-size: 26px;");
        pane.getChildren().addAll(label);
        label.setContentDisplay(ContentDisplay.BOTTOM);
        Scene endScene = new Scene(pane, 300, 200);
        return endScene;

    }

}
