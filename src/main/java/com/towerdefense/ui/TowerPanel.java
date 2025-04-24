package com.towerdefense.ui;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

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
        primaryStage.setTitle("Test");
        Scene scene = new Scene(getTowerPanel(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public VBox getTowerPanel() {
        VBox vbx = new VBox(10);

        Group tower = new Group(new Rectangle(50, 50, Color.BLACK));

        vbx.setStyle("-fx-background-color: #faf1da");
        Label singleShotTower = new Label("Single Shot Tower - 50$", tower);
        singleShotTower.setContentDisplay(ContentDisplay.TOP);
        singleShotTower.setStyle("-fx-background-color: #f2d79d;" +
                "-fx-border-color: #eed399;" +
                "-fx-border-width: 2;" +
                "-fx-padding:5;" +
                "-fx-text-fill: #582b0d;" + "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;");

        vbx.getChildren().addAll(singleShotTower);
        vbx.setAlignment(Pos.CENTER);
        return vbx;
    }

    public static void main(String[] args) {
        launch(args);
    }
}