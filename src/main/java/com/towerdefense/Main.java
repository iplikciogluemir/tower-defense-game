package com.towerdefense;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        Rectangle r1 = new Rectangle(0, 0, 40, 50);
        r1.setStyle("-fx-fill: #cbb296");

        Rectangle r4 = new Rectangle(8, 0, 4, 10);
        r4.setStyle("-fx-fill: #8b633a");

        Rectangle r5 = new Rectangle(12, 0, 4, 10);
        r5.setStyle("-fx-fill: #f2d79d");

        Rectangle r2 = new Rectangle(40, 0, 22, 50);
        r2.setStyle("-fx-fill: #8b633a");

        Rectangle r3 = new Rectangle(14, 30, 10, 20);
        r3.setStyle("-fx-fill: #8b633a");

        Rectangle r6 = new Rectangle(24, 0, 4, 10);
        r6.setStyle("-fx-fill: #8b633a");

        Rectangle r7 = new Rectangle(28, 0, 4, 10);
        r7.setStyle("-fx-fill: #f2d79d");

        Rectangle r8 = new Rectangle(44, 0, 4, 8);
        r8.setStyle("-fx-fill: #f2d79d");

        Rectangle r9 = new Rectangle(48, 0, 10, 8);
        r9.setStyle("-fx-fill: #cbb296");

        Group singleTower = new Group();
        singleTower.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r1.toBack();
        r2.toBack();

        Scene scene = new Scene(singleTower);
        primaryStage.setTitle("ShowLine"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

    }

    public static void main(String[] args) {
        launch(args);
    }
}