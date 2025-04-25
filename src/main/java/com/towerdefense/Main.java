package com.towerdefense;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        Rectangle r1 = new Rectangle(0, 0, 6, 7);
        r1.setStyle("-fx-fill: #cbb296");

        Rectangle r2 = new Rectangle(6, 0, 3, 7);
        r2.setStyle("-fx-fill: #8b633a");

        Rectangle r3 = new Rectangle(12, 0, 6, 7);
        r3.setStyle("-fx-fill: #cbb296");

        Rectangle r4 = new Rectangle(18, 0, 3, 7);
        r4.setStyle("-fx-fill: #8b633a");

        Rectangle r5 = new Rectangle(24, 0, 6, 7);
        r5.setStyle("-fx-fill: #cbb296");

        Rectangle r6 = new Rectangle(30, 0, 3, 7);
        r6.setStyle("-fx-fill: #8b633a");

        Rectangle r7 = new Rectangle(36, 0, 6, 7);
        r7.setStyle("-fx-fill: #8b633a");

        Rectangle r8 = new Rectangle(42, 0, 3, 7);
        r8.setStyle("-fx-fill: #8b633a");

        Rectangle r9 = new Rectangle(12, 22, 7, 15);
        r9.setStyle("-fx-fill: #8b633a");

        Rectangle r10 = new Rectangle(12, 52, 7, 15);
        r10.setStyle("-fx-fill: #8b633a");

        Rectangle r11 = new Rectangle(0, 7, 30, 60);
        r11.setStyle("-fx-fill: #cbb296");

        Rectangle r12 = new Rectangle(30, 7, 15, 60);
        r12.setStyle("-fx-fill: #8b633a");

        Group tower = new Group();
        tower.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);

        r11.toBack();

        Scene scene = new Scene(tower);

        primaryStage.setTitle("TimelineDemo"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}