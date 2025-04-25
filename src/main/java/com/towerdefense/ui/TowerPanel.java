package com.towerdefense.ui;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

import com.towerdefense.towers.LaserTower;
import com.towerdefense.towers.MissileLauncherTower;
import com.towerdefense.towers.SingleShotTower;
import com.towerdefense.towers.TripleShotTower;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TowerPanel extends Application {

    public void start(Stage primaryStage) {
        Scene a = new Scene(getTowerPanel(), 200, 200);
        primaryStage.setTitle("test");
        primaryStage.setScene(a);
        primaryStage.show();
    }

    public VBox getTowerPanel() {
        VBox vbx = new VBox(10);
        final double prefWidth = 200;

        vbx.setStyle("-fx-background-color: #faf1da");
        Label singleShotTower = new Label("Single Shot Tower - 50$", SingleShotTower.getSingleShotTower());
        singleShotTower.setContentDisplay(ContentDisplay.TOP);
        singleShotTower.setStyle("-fx-background-color: #f2d79d;" +
                "-fx-border-color: #eed399;" +
                "-fx-border-width: 2;" +
                "-fx-padding:5;" +
                "-fx-text-fill: #582b0d;" + "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-pref-width:" + prefWidth + ";" +
                "-fx-alignment:center");

        Label laserTower = new Label("Single Shot Tower - 120$", LaserTower.getLaserTower());
        laserTower.setContentDisplay(ContentDisplay.TOP);
        laserTower.setStyle("-fx-background-color: #f2d79d;" +
                "-fx-border-color: #eed399;" +
                "-fx-border-width: 2;" +
                "-fx-padding:5;" +
                "-fx-text-fill: #582b0d;" + "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-pref-width:" + prefWidth + ";" +
                "-fx-alignment:center");

        Label tripleShotTower = new Label("Triple Shot Tower - 150$", TripleShotTower.getTripleShotTower());
        tripleShotTower.setContentDisplay(ContentDisplay.TOP);
        tripleShotTower.setStyle("-fx-background-color: #f2d79d;" +
                "-fx-border-color: #eed399;" +
                "-fx-border-width: 2;" +
                "-fx-padding:5;" +
                "-fx-text-fill: #582b0d;" + "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-pref-width:" + prefWidth + ";" +
                "-fx-alignment:center");

        Label missileLauncherTower = new Label("Missile Launcher Tower - 200$",
                MissileLauncherTower.getMissileLauncherTower());
        missileLauncherTower.setContentDisplay(ContentDisplay.TOP);
        missileLauncherTower.setStyle("-fx-background-color: #f2d79d;" +
                "-fx-border-color: #eed399;" +
                "-fx-border-width: 2;" +
                "-fx-padding:5;" +
                "-fx-text-fill: #582b0d;" + "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-pref-width:" + prefWidth + ";" +
                "-fx-alignment:center");

        vbx.getChildren().addAll(singleShotTower, laserTower, tripleShotTower, missileLauncherTower);
        vbx.setAlignment(Pos.CENTER);
        return vbx;
    }

    public static void main(String[] args) {
        launch(args);
    }
}