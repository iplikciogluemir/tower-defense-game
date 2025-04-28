package com.towerdefense;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.map.MapCell;
import com.towerdefense.ui.TowerPanel;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {

        BorderPane uiPane = MapCell.getMap(2);

        Pane mainDraggablePane = new Pane();
        TowerPanel.setMainDraggablePane(mainDraggablePane);

        StackPane towerPanel = TowerPanel.getTowerPanel();
        uiPane.setRight(towerPanel);

        StackPane mainPane = new StackPane(uiPane, mainDraggablePane);

        Scene scene = new Scene(mainPane, 500, 500);
        
        //primaryStage.initStyle(StageStyle.UNDECORATED);
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
            
            pathTransition.play();



            // Point2D firstTile = (((GridPane)uiPane.getCenter()).getChildren().get(0)).localToScene(0,0);
            // Circle circle = new Circle(firstTile.getX() , firstTile.getY(), 60);
            // mainPane.getChildren().addAll(circle);


        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}