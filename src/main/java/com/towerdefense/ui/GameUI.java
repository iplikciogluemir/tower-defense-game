package main.java.com.towerdefense.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameUI extends Application {
    @Override
    public void start(Stage primaryStage) {
    }

    private Scene startScene() {
        Pane pane = new StackPane();
        Button bt = new Button("Start Game");

        pane.getChildren().add(bt);
        Scene startScene = new Scene(pane, 200, 200);
        startScene.setFill(Color.web("#FAF1DA"));
        return startScene;
    }
}