package com.towerdefense.projectiles;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyExplosion;
import com.towerdefense.game.LevelManager;
import com.towerdefense.game.WaveManager;
import com.towerdefense.ui.GameColors;
import com.towerdefense.ui.HUDVariables;
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

    public static void shootLaser(Pane pane, Group tower, Enemy enemy1) {
        Group enemy = enemy1.getEnemy();
        Line laser = new Line();
        laser.setStroke(Color.web(GameColors.getLaserColor()));
        laser.setStrokeWidth(3);

        pane.getChildren().add(laser);

        final Timeline[] timeline = new Timeline[1];

        timeline[0] = new Timeline(new KeyFrame(Duration.millis(15), e -> {

            if (!pane.getChildren().contains(enemy)) {
                pane.getChildren().remove(laser);
                if (!LevelManager.laserBeamExists()) {
                    LevelManager.LaserSound.stop();
                }
                timeline[0].stop();
                return;
            }

            Bounds towerBounds = tower.getBoundsInParent();
            Bounds enemyBounds = enemy.getBoundsInParent();

            double towerX = towerBounds.getCenterX();
            double towerY = towerBounds.getCenterY();
            double enemyX = enemyBounds.getCenterX();
            double enemyY = enemyBounds.getCenterY();

            laser.setStartX(towerX);
            laser.setStartY(towerY);
            laser.setEndX(enemyX);
            laser.setEndY(enemyY);

            double distance = Projectile.getDistance(enemyX, enemyY, towerX, towerY);

            if (distance <= 200) {
                Enemy.getLaserHit(enemy);

                if (Enemy.isDead(enemy)) {
                    EnemyExplosion.createExplosion(pane, enemy1);
                    timeline[0].stop();
                    WaveManager.currEnemyList.remove(enemy1);
                    pane.getChildren().removeAll(enemy, laser);
                    if (!LevelManager.laserBeamExists()) {
                        LevelManager.LaserSound.stop();
                    }
                    HUDVariables.setMoney(HUDVariables.getMoney() + 10);
                }
            }

            else {
                timeline[0].stop();
                pane.getChildren().remove(laser);
                if (!LevelManager.laserBeamExists()) {
                    LevelManager.LaserSound.stop();
                }
            }
        }));

        timeline[0].setCycleCount(Timeline.INDEFINITE);
        timeline[0].play();
    }
}