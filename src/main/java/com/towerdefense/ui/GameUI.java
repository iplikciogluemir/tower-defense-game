package com.towerdefense.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameUI extends Application {

    @Override
    public void start(Stage primaryStage) {
    }

    private Scene startScene() {
        Pane pane = new StackPane();
        pane.setStyle("-fx-background-color:#faf1da");

        Button bt = new Button("Start Game");
        bt.setStyle("-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-color: #eed399;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 100px ;" +
                "-fx-background-color: #f2d79d;" +
                "-fx-text-fill: #582b0d;" +
                "-fx-font-weight: bold;" +
                " -fx-font-size: 16px;");
        pane.getChildren().add(bt);
        Scene startScene = new Scene(pane, 200, 200);
        return startScene;
    }

    private Scene endScreen() {
        StackPane pane = new StackPane();

        pane.setStyle("-fx-background-color:#faf1da");

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
