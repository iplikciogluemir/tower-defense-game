package com.towerdefense.game;

import java.util.ArrayList;
import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.map.MapCell;
import com.towerdefense.ui.HUDVariables;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class WaveManager {
    int waveCount;
    int tiles;
    public double seconds;
    double standartSeconds;
    public ArrayList<ArrayList> waveList;
    BorderPane uiPane;
    public int currWave;
    public static ArrayList<Enemy> currEnemyList;

    public WaveManager(BorderPane uiPane) {

        this.waveCount = MapCell.currMap.getWaveCount();
        this.tiles = MapCell.currMap.getPath().size() / 2 - 1;
        this.standartSeconds = tiles / 2.0;
        this.waveList = new ArrayList<>();
        this.uiPane = uiPane;
        this.currWave = 0;

        for (int i = 0; i < MapCell.currMap.getWaveCount(); i++) {
            ArrayList<Enemy> enemyList = new ArrayList<>();
            for (int j = 0; j < MapCell.currMap.getEnemyCount(i); j++) {
                Enemy enemy = new Enemy(100, 1);
                enemyList.add(enemy);
            }
            waveList.add(enemyList);
        }

    }

    public void sendWave(int waveIndex) {
        this.currWave = waveIndex;

        Timeline timeline = new Timeline();
        currEnemyList = waveList.get(waveIndex);
        for (int i = 0; i < currEnemyList.size(); i++) {
            final int eventFix = i;
            Enemy enemy = currEnemyList.get(eventFix);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * MapCell.currMap.getEnemyInterval(waveIndex)), e -> {
                sendEnemy(enemy);
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
    }

    public void sendEnemy(Enemy enemy) {

        // Calculate the How fast The how much time the enemy will spend
        this.seconds = standartSeconds / enemy.getSpeed();

        Group enemyGroup = enemy.getEnemy();
        PathTransition pathTransition = new PathTransition();
        uiPane.getChildren().add(enemyGroup);
        pathTransition.setDuration(Duration.seconds(this.seconds));
        pathTransition.setPath(EnemyPathAutoGenerator.getEnemyPath(uiPane));
        pathTransition.setNode(enemyGroup);
        // pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();

        pathTransition.setOnFinished(e -> {
            if (!Enemy.isDead(enemyGroup)) {
                HUDVariables.setLives(HUDVariables.getLives() - 1);
            }
            uiPane.getChildren().remove(enemyGroup);
            waveList.get(currWave).remove(enemy);
        });

    }

}
