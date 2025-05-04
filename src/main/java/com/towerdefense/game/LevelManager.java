package com.towerdefense.game;

import java.io.File;
import java.util.HashMap;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.map.MapCell;
import com.towerdefense.projectiles.Bullet;
import com.towerdefense.projectiles.Laser;
import com.towerdefense.projectiles.Projectile;
import com.towerdefense.ui.DragTowers;
import com.towerdefense.ui.HUDVariables;
import com.towerdefense.ui.TowerPanel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class LevelManager {
    private static boolean isLevelOver;

    public static BorderPane getLevelPane(int levelIndex) {

        BorderPane uiPane = new BorderPane();
        uiPane.setCenter(MapCell.getMap(levelIndex));

        HUDVariables.setTime((int) MapCell.currMap.getWaveDelay(0));

        uiPane.setRight(TowerPanel.getTowerPanel(uiPane));
        uiPane.setStyle("-fx-background-color: #faf1da;");

        WaveManager waveManager = new WaveManager(uiPane);

        Timeline[] firstWave = new Timeline[1];
        firstWave[0] = new Timeline(new KeyFrame(Duration.seconds((int) MapCell.currMap.getWaveDelay(0)), e -> {
            if (HUDVariables.getTime() == 0) {
                waveManager.sendWave(0);
                firstWave[0].stop();
            }
        }));
        firstWave[0].play();
        firstWave[0].setCycleCount(Timeline.INDEFINITE);

        final boolean[] readyToSend = { false };

        Timeline[] waveTimeline = new Timeline[1];
        waveTimeline[0] = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {

                    // if last wave
                    if (waveManager.currWave == MapCell.currMap.getWaveCount() - 1) {
                        waveTimeline[0].stop();
                        firstWave[0].stop();
                    }
                    if (readyToSend[0] && HUDVariables.getTime() == 0) {

                        waveManager.sendWave(waveManager.currWave + 1);
                        readyToSend[0] = false;
                    }

                    if (waveManager.waveList.get(waveManager.currWave).isEmpty() &&
                            waveManager.currWave != MapCell.currMap.getWaveCount() - 1 &&
                            HUDVariables.getTime() == 0) {

                        HUDVariables.setTime((int) Math.round(MapCell.currMap.getWaveDelay(waveManager.currWave + 1)));
                        HUDVariables.getTimeline().play();
                        readyToSend[0] = true;

                    }

                }));
        waveTimeline[0].setCycleCount(Timeline.INDEFINITE);
        waveTimeline[0].play();

        final Timeline[] timeline = new Timeline[1];
        timeline[0] = new Timeline(new KeyFrame(Duration.seconds(1), e -> {

            Media media = new Media(new File("src/main/resources/sounds/BulletShoot.wav").toURI().toString());
            MediaPlayer shootSound = new MediaPlayer(media);

            for (int i = 0; i < waveManager.waveList.get(waveManager.currWave).size(); i++) {
                Enemy enemyTest = ((Enemy) waveManager.waveList.get(waveManager.currWave).get(i));

                HashMap<Group, Integer> towerMap = DragTowers.getTowerMap();
                for (Group tower : towerMap.keySet()) {
                    Bounds towerPositions = tower.getBoundsInParent();
                    Bounds enemyPositions = enemyTest.getEnemy().getBoundsInParent();
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
            }
            if (waveManager.currWave == MapCell.currMap.getWaveCount() - 1
                    && waveManager.waveList.get(waveManager.currWave).isEmpty()) {
                isLevelOver = true;
                timeline[0].stop();
            }
        }));
        timeline[0].setCycleCount(Timeline.INDEFINITE);
        timeline[0].play();

        return uiPane;
    }

    public static boolean isWaveOver() {
        return WaveManager.currEnemyList.isEmpty();
    }

    public static boolean isLevelOver() {
        return isLevelOver;
    }

}