package com.towerdefense;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.map.MapCell;
import com.towerdefense.ui.TowerPanel;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException, InterruptedException {

        BorderPane uiPane = new BorderPane();

        uiPane.setCenter(MapCell.getMap(2));
        uiPane.setRight(TowerPanel.getTowerPanel(uiPane));
        uiPane.setStyle("-fx-background-color: #faf1da;");

        Scene scene = new Scene(uiPane, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        Group enemyTest = Enemy.getEnemy();
        uiPane.getChildren().addAll(enemyTest);

        uiPane.setOnMouseClicked(e -> {
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.seconds(2));
            try {
                pathTransition.setPath(EnemyPathAutoGenerator.getEnemyPath(2, uiPane));
            } catch (IOException e1) {

                e1.printStackTrace();
            }
            pathTransition.setNode(enemyTest);
            pathTransition.setCycleCount(PathTransition.INDEFINITE);
            pathTransition.setInterpolator(Interpolator.LINEAR);
            pathTransition.play();

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}