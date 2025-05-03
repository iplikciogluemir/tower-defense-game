package com.towerdefense.ui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.game.WaveManager;
import com.towerdefense.map.MapCell;
import com.towerdefense.projectiles.Bullet;
import com.towerdefense.projectiles.Laser;
import com.towerdefense.projectiles.Projectile;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HUD {
    static Group enemyTest;
    static BorderPane uiPane;

    public static Scene getScene() throws IOException, InterruptedException {
        uiPane = new BorderPane();

        uiPane.setCenter(MapCell.getMap(2));
        uiPane.setRight(TowerPanel.getTowerPanel(uiPane));
        uiPane.setStyle("-fx-background-color: #faf1da;");

        Scene scene = new Scene(uiPane, 500, 500);
        Enemy enemyTrial = new Enemy();
        enemyTest = enemyTrial.getEnemy();
        uiPane.getChildren().addAll(enemyTest);

        return scene;
    }

    public static void startAnimation() {
        // WaveManager manager = new WaveManager(uiPane);
        // manager.waveSender(1);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setPath(EnemyPathAutoGenerator.getEnemyPath(uiPane));
        pathTransition.setNode(enemyTest);
        pathTransition.setInterpolator(Interpolator.LINEAR);

        pathTransition.setOnFinished(e -> {
            if (!Enemy.isDead(enemyTest)) {
                HUDVariables.setLives(HUDVariables.getLives() - 1);
                uiPane.getChildren().remove(enemyTest);
            }
        });

        pathTransition.play();

        final Timeline[] timeline = new Timeline[1];
        timeline[0] = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            Media media = new Media(new File("src/main/resources/sounds/BulletShoot.wav").toURI().toString());
            MediaPlayer shootSound = new MediaPlayer(media);




            if (!uiPane.getChildren().contains(enemyTest)) {
                timeline[0].stop();
                return;
            }

            HashMap<Group, Integer> towerMap = DragTowers.getTowerMap();
            for (Group tower : towerMap.keySet()) {
                Bounds towerPositions = tower.getBoundsInParent();
                Bounds enemyPositions = enemyTest.getBoundsInParent();
                double distance = Projectile.getDistance(towerPositions.getCenterX(), towerPositions.getCenterY(),
                        enemyPositions.getCenterX(),
                        enemyPositions.getCenterY());
                if (distance <= 200 && DragTowers.price(tower) == 50) {
                    shootSound.setVolume(0.2);
                    shootSound.play();
                    Bullet.shootBullet(uiPane, tower, enemyTest);
                }
                if (distance <= 200 && DragTowers.price(tower) == 120) {
                    Laser.shootLaser(uiPane, tower, enemyTest);
                }
            }
        }));
        timeline[0].setCycleCount(Timeline.INDEFINITE);
        timeline[0].play();
    }
}
