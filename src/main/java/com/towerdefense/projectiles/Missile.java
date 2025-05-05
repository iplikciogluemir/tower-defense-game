package com.towerdefense.projectiles;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyExplosion;
import com.towerdefense.game.WaveManager;
import com.towerdefense.ui.HUDVariables;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Missile extends Application {

    @Override
    public void start(Stage primaryStage) {
    }

    public static void shootMissile(Pane pane, Group tower, Enemy enemy1) {
        Group enemy = enemy1.getEnemy();
        Circle missile = new Circle(10, Color.RED);
        Bounds towerPos = tower.getBoundsInParent();
        // Bounds enemyPos = enemy.getBoundsInParent();
        missile.setCenterX(towerPos.getCenterX());
        missile.setCenterY(towerPos.getCenterY());
        pane.getChildren().add(missile);

        final double speed = 133.0;
        final double interval = 20;

        final Timeline[] timelineRef = new Timeline[1];

        timelineRef[0] = new Timeline(new KeyFrame(Duration.millis(interval), e -> {

            if (!pane.getChildren().contains(enemy)) {
                pane.getChildren().remove(missile);
                timelineRef[0].stop();
                return;
            }

            double targetX = enemy.getTranslateX();
            double targetY = enemy.getTranslateY();
            double currentX = missile.getCenterX();
            double currentY = missile.getCenterY();

            double moveX = targetX - currentX;
            double moveY = targetY - currentY;

            double distance = Math.hypot(moveX, moveY);

            if (distance < 5) {
                pane.getChildren().remove(missile);

                if (Enemy.isDead(enemy)) {
                    EnemyExplosion.createExplosion(pane, enemy1);
                    WaveManager.currEnemyList.remove(enemy1);
                    pane.getChildren().remove(enemy);
                    HUDVariables.setMoney(HUDVariables.getMoney() + 10);
                }

                timelineRef[0].stop();
                return;
            }

            double stepSize = speed / interval;
            double moveStepX = (moveX / distance) * stepSize;
            double moveStepY = (moveY / distance) * stepSize;

            missile.setCenterX(currentX + moveStepX);
            missile.setCenterY(currentY + moveStepY);

        }));
        timelineRef[0].setCycleCount(Timeline.INDEFINITE);
        timelineRef[0].play();
    }
}
