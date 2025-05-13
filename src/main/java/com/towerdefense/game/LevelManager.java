package com.towerdefense.game;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import com.towerdefense.Main;
import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyExplosion;
import com.towerdefense.map.LevelGenerator;
import com.towerdefense.map.MapCell;
import com.towerdefense.projectiles.Bullet;
import com.towerdefense.projectiles.Laser;
import com.towerdefense.projectiles.Missile;
import com.towerdefense.projectiles.Projectile;
import com.towerdefense.ui.GameColors;
import com.towerdefense.ui.HUDVariables;
import com.towerdefense.ui.TowerPanel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class LevelManager {
    static ArrayList<Enemy> enemiesInRange;
    private static boolean isLevelOver;
    private static Timeline[] firstWave;
    private static Timeline[] waveTimeline;
    private static Timeline[] timeline;
    public static BorderPane uiPane;

    public static Media SSTMedia;
    public static MediaPlayer SSTSound;
    public static Media LaserMedia;
    public static MediaPlayer LaserSound;
    public static Media TSTMedia;
    public static MediaPlayer TSTSound;
    public static Media MSTMedia;
    public static MediaPlayer MSTSound;
    public static Media MSTMediaExp;
    public static MediaPlayer MSTSoundExp;
    public static Media DSMedia;
    public static MediaPlayer DSSound;

    public static BorderPane getLevelPane(int levelIndex) {

        createMedia();
        uiPane = new BorderPane();
        uiPane.setCenter(MapCell.getMap(levelIndex));
        uiPane.setRight(TowerPanel.getTowerPanel(uiPane));
        HUDVariables.setTime((int) MapCell.currMap.getWaveDelay(0));
        uiPane.setStyle("-fx-background-color: " + GameColors.getBackgroundColor() + ";");

        WaveManager waveManager = new WaveManager(uiPane);
        firstWave = new Timeline[1];

        firstWave[0] = new Timeline(new KeyFrame(Duration.seconds((int) MapCell.currMap.getWaveDelay(0) + 1.4), e -> {
            if (HUDVariables.getTime() == 0) {
                waveManager.sendWave(0);
            }
        }));
        firstWave[0].play();

        final boolean[] readyToSend = { false };

        waveTimeline = new Timeline[1];
        waveTimeline[0] = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {

                    // if last wave
                    if (WaveManager.currWave == MapCell.currMap.getWaveCount() - 1) {
                        waveTimeline[0].stop();
                        firstWave[0].stop();
                    }
                    if (readyToSend[0] && HUDVariables.getTime() == 0) {

                        waveManager.sendWave(WaveManager.currWave + 1);
                        readyToSend[0] = false;
                    }

                    if (waveManager.waveList.get(WaveManager.currWave).isEmpty() &&
                            waveManager.currWave != MapCell.currMap.getWaveCount() - 1 &&
                            HUDVariables.getTime() == 0) {

                        HUDVariables.setTime((int) Math.round(MapCell.currMap.getWaveDelay(WaveManager.currWave + 1)));
                        HUDVariables.getTimeline().play();
                        readyToSend[0] = true;

                    }

                }));
        waveTimeline[0].setCycleCount(Timeline.INDEFINITE);
        waveTimeline[0].play();

        timeline = new Timeline[1];
        timeline[0] = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            attack(waveManager);

        }));
        timeline[0].setCycleCount(Timeline.INDEFINITE);
        timeline[0].play();

        return uiPane;
    }

    public static void attack(WaveManager waveManager) {
        HashMap<Group, Integer> towerMap = DragTowers.getTowerMap();
        for (Group tower : towerMap.keySet()) {
            if (DragTowers.price(tower) == 50) {

                Enemy targetEnemy = targetEnemy(tower, waveManager, 200);

                if (targetEnemy != null) {
                    if (SSTSound.getStatus() == MediaPlayer.Status.PLAYING) {
                        SSTSound.stop();
                    }
                    SSTSound.play();
                    Bullet.shootBullet(uiPane, tower, targetEnemy);
                }
            } else if (DragTowers.price(tower) == 120) {
                for (int i = 0; i < waveManager.waveList.get(WaveManager.currWave).size(); i++) {
                    Enemy enemyTest = ((Enemy) waveManager.waveList.get(WaveManager.currWave).get(i));

                    double distance = calculateDistance(tower, enemyTest);

                    if (distance <= 200) {
                        if (LaserSound.getStatus() != MediaPlayer.Status.PLAYING) {
                            LaserSound.play();
                        }
                        Laser.shootLaser(uiPane, tower, enemyTest);
                    }
                }
            } else if (DragTowers.price(tower) == 150) {
                PriorityQueue<Map.Entry<Double, Enemy>> pq = new PriorityQueue<>(
                        (a, b) -> Double.compare(a.getKey(), b.getKey()));

                for (int i = 0; i < waveManager.waveList.get(WaveManager.currWave).size(); ++i) {
                    Enemy enemyTest = waveManager.waveList.get(WaveManager.currWave).get(i);
                    double distance = calculateDistance(tower, enemyTest);

                    if (distance <= 200) {
                        pq.offer(new AbstractMap.SimpleEntry<>(distance, enemyTest));
                    }
                }

                int enemycount = 0;
                while (!pq.isEmpty() && enemycount < 3) {
                    Enemy target = pq.poll().getValue();

                    Bullet.shootBullet(uiPane, tower, target);
                    ++enemycount;
                }
                if (enemycount == 3) {
                    if (TSTSound.getStatus() == MediaPlayer.Status.PLAYING) {
                        TSTSound.stop();
                    }
                    TSTSound.play();
                } else if (enemycount == 2) {
                    if (DSSound.getStatus() == MediaPlayer.Status.PLAYING) {
                        DSSound.stop();
                    }
                    DSSound.play();
                } else if (enemycount == 1) {
                    if (SSTSound.getStatus() == MediaPlayer.Status.PLAYING) {
                        SSTSound.stop();
                    }
                    SSTSound.play();
                }

            } else {

                Enemy targetEnemy = targetEnemy(tower, waveManager, 200);

                if (targetEnemy != null) {

                    enemiesInRange = new ArrayList<>();
                    for (int i = 0; i < WaveManager.currEnemyList.size(); i++) {
                        Enemy boomenemy = (WaveManager.currEnemyList.get(i));
                        Bounds boomenemyPos = boomenemy.getEnemy().getBoundsInParent();
                        Bounds enemyPos = targetEnemy.getEnemy().getBoundsInParent();
                        double distance = Projectile.getDistance(boomenemyPos.getCenterX(),
                                boomenemyPos.getCenterY(),
                                enemyPos.getCenterX(),
                                enemyPos.getCenterY());
                        if (distance <= 75) {
                            enemiesInRange.add(boomenemy);
                        }
                    }
                    if (MSTSound.getStatus() == MediaPlayer.Status.PLAYING) {
                        MSTSound.stop();
                    }
                    MSTSound.play();
                    Missile.shootMissile(uiPane, tower, targetEnemy);

                }

            }

            if (WaveManager.currWave == MapCell.currMap.getWaveCount() - 1
                    && waveManager.waveList.get(WaveManager.currWave).isEmpty()) {
                isLevelOver = true;
                timeline[0].stop();
            }
        }

    }

    public static boolean isWaveOver() {
        return WaveManager.currEnemyList.isEmpty();
    }

    public static boolean isLevelOver() {
        return isLevelOver;
    }

    public static void resetLevelCondition() {
        isLevelOver = false;
    }

    private static double calculateDistance(Group tower, Enemy enemy) {
        Bounds towerPositions = tower.getBoundsInParent();
        Bounds enemyPositions = enemy.getEnemy().getBoundsInParent();
        double distance = Projectile.getDistance(towerPositions.getCenterX(),
                towerPositions.getCenterY(),
                enemyPositions.getCenterX(),
                enemyPositions.getCenterY());
        return distance;
    }

    private static Enemy targetEnemy(Group tower, WaveManager waveManager, double range) {
        Enemy targetEnemy = null;
        double shortestDistance = range;

        for (int i = 0; i < waveManager.waveList.get(WaveManager.currWave).size(); i++) {
            Enemy enemyTest = ((Enemy) waveManager.waveList.get(WaveManager.currWave).get(i));
            double distance = calculateDistance(tower, enemyTest);

            if (distance <= shortestDistance) {
                shortestDistance = distance;
                targetEnemy = enemyTest;
            }
        }
        return targetEnemy;
    }

    public static void clearAll() {
        isLevelOver = false;
        LaserSound.stop();

        if (WaveManager.currEnemyList != null)
            WaveManager.currEnemyList.clear();
        else
            WaveManager.currEnemyList = new ArrayList<>();

        if (timeline != null)
            timeline[0].stop();

        if (firstWave != null)
            firstWave[0].stop();

        if (waveTimeline != null)
            waveTimeline[0].stop();
    }

    public static void missileAreaKill() {
        for (Enemy boomenemy : enemiesInRange) {
            if (MSTSoundExp.getStatus() == MediaPlayer.Status.PLAYING) {
                MSTSoundExp.stop();
            }
            MSTSoundExp.play();

            Enemy.getMissileHit(boomenemy.getEnemy());
            if (Enemy.isDead(boomenemy.getEnemy())) {
                EnemyExplosion.createExplosion(uiPane, boomenemy);
                WaveManager.currEnemyList.remove(boomenemy);
                uiPane.getChildren().remove(boomenemy.getEnemy());
                HUDVariables.setMoney(HUDVariables.getMoney() + 10);
            }
        }
    }

    public static void createMedia() {

        // Single Shot Tower
        SSTMedia = new Media(new File("src/main/resources/sounds/BulletShoot.wav").toURI().toString());
        SSTSound = new MediaPlayer(SSTMedia);
        SSTSound.setVolume(0.2);

        // Laser Tower
        LaserMedia = new Media(new File("src/main/resources/sounds/LaserBeam.wav").toURI().toString());
        LaserSound = new MediaPlayer(LaserMedia);
        LaserSound.setVolume(0.2);
        LaserSound.setStartTime(Duration.seconds(1.5));

        // Triple Shot Tower
        TSTMedia = new Media(new File("src/main/resources/sounds/TripleShoot.wav").toURI().toString());
        TSTSound = new MediaPlayer(TSTMedia);
        TSTSound.setVolume(0.2);

        // Double Shot Sound for TST
        DSMedia = new Media(new File("src/main/resources/sounds/DoubleShoot.mp3").toURI().toString());
        DSSound = new MediaPlayer(DSMedia);
        DSSound.setVolume(0.2);

        // Missile Shot Tower
        MSTMedia = new Media(new File("src/main/resources/sounds/MissileLaunch.wav").toURI().toString());
        MSTSound = new MediaPlayer(MSTMedia);
        MSTSound.setVolume(0.2);

        // Missile Shot Tower Explosion
        MSTMediaExp = new Media(new File("src/main/resources/sounds/Kaboom.wav").toURI().toString());
        MSTSoundExp = new MediaPlayer(MSTMediaExp);
        MSTSoundExp.setVolume(0.2);
    }

    public static boolean laserBeamExists() {
        for (Node node : uiPane.getChildren()) {
            if (node instanceof Line) {
                return true;

            }
        }
        return false;
    }

    public static void muteEffects() {
        SSTSound.setMute(true);
        LaserSound.setMute(true);
        TSTSound.setMute(true);
        DSSound.setMute(true);
        MSTSound.setMute(true);
        MSTSoundExp.setMute(true);
    }

    public static void unMuteEffects() {
        SSTSound.setMute(false);
        LaserSound.setMute(false);
        TSTSound.setMute(false);
        DSSound.setMute(false);
        MSTSound.setMute(false);
        MSTSoundExp.setMute(false);
    }

    public static BorderPane getRandomLevel() {
        LevelGenerator generator = new LevelGenerator();
        String filePath = "src/main/resources/maps/level6.txt";
        generator.generateLevel(filePath);

        BorderPane levelPane = getLevelPane(6);
        return levelPane;
    }

}
