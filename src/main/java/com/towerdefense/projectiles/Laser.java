package com.towerdefense.projectiles;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Laser extends Application {
    @Override
    public void start(Stage primaryStage) {
    }

    public static void shootLaser(Pane pane, Group tower, Group enemy) {
        Bounds towerBounds = tower.getBoundsInParent();
        double towerX = towerBounds.getCenterX();
        double towerY = towerBounds.getCenterY();

        Line laser = new Line();
        laser.setStartX(towerX);
        laser.setStartY(towerY);
        laser.setStroke(Color.RED);
        laser.setStrokeWidth(3);

        pane.getChildren().add(laser);

        final Timeline[] timelineRef = new Timeline[1];

        timelineRef[0] = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            Bounds enemyBounds = enemy.getBoundsInParent();
            double enemyX = enemyBounds.getCenterX();
            double enemyY = enemyBounds.getCenterY();

            laser.setEndX(enemyX);
            laser.setEndY(enemyY);

            if (towerBounds.intersects(enemyBounds)) {
                // Deal damage here
            }
        }));

        timelineRef[0].setCycleCount(Timeline.INDEFINITE);
        timelineRef[0].play();
    }
}