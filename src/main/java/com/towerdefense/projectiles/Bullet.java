package com.towerdefense.projectiles;

import java.io.File;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Bullet {

    static Media media = new Media(new File("src/main/resources/sounds/BulletShoot.wav").toURI().toString());
    static MediaPlayer shootSound = new MediaPlayer(media);

    public static void shootBullet(Pane pane, Group tower, Enemy enemy1) {
        shootSound.setVolume(0.2);
        shootSound.play();
        Group enemy = enemy1.getEnemy();
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

            if (!pane.getChildren().contains(enemy)) {
                pane.getChildren().remove(bullet);
                timelineRef[0].stop();
                return;
            }

            double targetX = enemy.getTranslateX();
            double targetY = enemy.getTranslateY();
            double currentX = bullet.getCenterX();
            double currentY = bullet.getCenterY();

            double moveX = targetX - currentX;
            double moveY = targetY - currentY;

            double distance = Math.hypot(moveX, moveY);

            if (distance < 10) {
                pane.getChildren().remove(bullet);
                Enemy.getSingleHit(enemy);

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

            bullet.setCenterX(currentX + moveStepX);
            bullet.setCenterY(currentY + moveStepY);

        }));
        timelineRef[0].setCycleCount(Timeline.INDEFINITE);
        timelineRef[0].play();
    }
}
