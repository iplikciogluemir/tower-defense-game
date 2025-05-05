package com.towerdefense;

import javax.print.attribute.HashDocAttributeSet;

import com.towerdefense.game.LevelManager;
import com.towerdefense.ui.DragTowers;
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
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {

        startGame();

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            if (HUDVariables.getLives() == 0) {
                lose();
                timeline.pause();
            } else if (LevelManager.isLevelOver()) {
                win();
                timeline.pause();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defense Game");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void startGame() {
        Pane pane = GameUI.startScreen();
        scene.setRoot(pane);
        Button startButton = (Button) pane.getChildren().get(0);

        startButton.setOnMouseClicked(e -> {
            levelIndex = 1;
            scene.setRoot(LevelManager.getLevelPane(levelIndex));
            HUDVariables.setMoney(1000);
            LevelManager.resetLevelCondition();
            timeline.play();
        });
    }

    public void win() {
        DragTowers.getTowerMap().clear();
        if (levelIndex == 5) {
            Pane pane = GameUI.endScreen();
            scene.setRoot(pane);
            Label mainMenuLabel = (Label) pane.getChildren().get(0);
            Button mainMenu = (Button) mainMenuLabel.getGraphic();

            mainMenu.setOnMouseClicked(e -> {
                startGame();
            });
        } else {
            Pane pane = GameUI.winScreen();
            levelIndex++;
            scene.setRoot(pane);

            Label continueLabel = (Label) pane.getChildren().get(0);
            Button continueButton = (Button) continueLabel.getGraphic();

            continueButton.setOnMouseClicked(e -> {
                scene.setRoot(LevelManager.getLevelPane(levelIndex));
                LevelManager.resetLevelCondition();
                timeline.play();
            });
        }
    }

    public void lose() {
        DragTowers.getTowerMap().clear();
        Pane pane = GameUI.loseScreen();
        scene.setRoot(pane);

        Label mainMenuLabel = (Label) pane.getChildren().get(0);
        Button mainMenu = (Button) mainMenuLabel.getGraphic();

        mainMenu.setOnMouseClicked(e -> {
            startGame();
        });
    }
}