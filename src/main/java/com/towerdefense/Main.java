package com.towerdefense;

import com.towerdefense.game.LevelManager;
import com.towerdefense.ui.GameUI;
import com.towerdefense.ui.HUDVariables;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private int levelIndex = 1;
    private Scene scene = new Scene(new Pane());

    @Override
    public void start(Stage primaryStage) {

        startGame();

        Timeline[] timeline = new Timeline[1];
        timeline[0] = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (isSuccessful())
                win();
            else
                lose();
        }));

        timeline[0].setCycleCount(Timeline.INDEFINITE);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defense Game");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean isSuccessful() {
        return LevelManager.isLevelOver() && (HUDVariables.getLives() > 0);
    }

    public void startGame() {
        Pane pane = GameUI.startScreen();
        scene.setRoot(pane);
        Button startButton = (Button) pane.getChildren().get(0);

        startButton.setOnMouseClicked(e -> {
            scene.setRoot(LevelManager.getLevelPane(levelIndex));
        });
    }

    public void lose() {
        Pane pane = GameUI.loseScreen();
        scene.setRoot(pane);

        Label mainMenuLabel = (Label) pane.getChildren().get(0);
        Button mainMenu = (Button) mainMenuLabel.getGraphic();

        mainMenu.setOnMouseClicked(e2 -> {
            levelIndex = 1;
            HUDVariables.setLives(5);
            HUDVariables.setMoney(1000);
            scene.setRoot(GameUI.startScreen());
        });
    }

    public void win() {
        Pane pane = GameUI.winScreen();
        if (levelIndex == 5) {
            scene.setRoot(GameUI.endScreen());
        } else {
            levelIndex++;
            HUDVariables.setLives(5);
            HUDVariables.setMoney(1000);
            scene.setRoot(pane);

            Label continueLabel = (Label) pane.getChildren().get(0);
            Button mainMenu = (Button) continueLabel.getGraphic();

            mainMenu.setOnMouseClicked(e -> {
                scene.setRoot(LevelManager.getLevelPane(levelIndex));
            });
        }
    }
}