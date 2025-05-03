package com.towerdefense;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.map.MapCell;
import com.towerdefense.ui.HUD;
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
    public void start(Stage primaryStage) {
        Scene scene = HUD.getScene();
        primaryStage.setTitle("test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}