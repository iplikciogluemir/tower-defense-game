package com.towerdefense.projectiles;

import com.towerdefense.enemies.Enemy;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Bullet extends Application {
    @Override
    public void start(Stage primaryStage) {
    }

    public static void shootBullet(Pane pane, Group tower, Group enemy) {
        Circle bullet = new Circle(5, Color.RED);
        Bounds towerPos = tower.getBoundsInParent();
        // Bounds enemyPos = enemy.getBoundsInParent();
        bullet.setCenterX(towerPos.getCenterX());
        bullet.setCenterY(towerPos.getCenterY());
        pane.getChildren().add(bullet);

        final double speed = 200.0;
        final double interval = 20;

        // needed to enable modification
        final Timeline[] timelineRef = new Timeline[1];

        timelineRef[0] = new Timeline(new KeyFrame(Duration.millis(interval), e -> {
            double targetX = enemy.getTranslateX();
            double targetY = enemy.getTranslateY();
            double currentX = bullet.getCenterX();
            double currentY = bullet.getCenterY();

            double moveX = targetX - currentX;
            double moveY = targetY - currentY;

            double distance = Math.hypot(moveX, moveY);

            // Check collision
            if (distance < 10) {
                pane.getChildren().remove(bullet);
                // decrease enemy health
                timelineRef[0].stop();
                return;
            }

            double stepSize = speed / interval;
            double moveStepX = (moveX / distance) * stepSize;
            double moveStepY = (moveY / distance) * stepSize;

            bullet.setCenterX(currentX + moveStepX);
            bullet.setCenterY(currentY + moveStepY);
        }));
        timelineRef[0].setCycleCount(Timeline.INDEFINITE);
        timelineRef[0].play();
    }
}
