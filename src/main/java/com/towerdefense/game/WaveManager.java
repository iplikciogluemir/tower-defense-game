package com.towerdefense.game;

import java.io.IOException;
import java.util.ArrayList;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.map.MapCell;
import com.towerdefense.ui.HUDVariables;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class WaveManager {
    int waveCount;
    int tiles;
    double standartSeconds;
    public ArrayList<ArrayList> waveList;
    BorderPane uiPane;
    public int currWave;

    public WaveManager(BorderPane uiPane) {
        
        this.waveCount = MapCell.currMap.getWaveCount(); 
        this.tiles = MapCell.currMap.getPath().size() / 2 - 1;
        this.standartSeconds = tiles / 2.0;
        this.waveList = new ArrayList<>();
        this.uiPane = uiPane;

        
        for (int i = 0; i < MapCell.currMap.getWaveCount(); i++) {
            ArrayList<Enemy> enemyList = new ArrayList<>();
            for (int j = 0; j < MapCell.currMap.getEnemyCount(i); j++) {
                Enemy enemy = new Enemy(100, 1);
                enemyList.add(enemy);
            }
            waveList.add(enemyList);
        }
       
    }

    public void waveSender(int waveIndex) {
        this.currWave = waveIndex;
        Timeline timeline = new Timeline();
        ArrayList<Enemy> enemyList = waveList.get(waveIndex);
        for (int i = 0; i < enemyList.size(); i++) {
            final int eventFix = i;
            Enemy enemy = enemyList.get(eventFix);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i * MapCell.currMap.getEnemyInterval(waveIndex)+0.1), e -> {
                enemySender(enemy);
            });
            timeline.getKeyFrames().add(keyFrame);
        }     
        timeline.play();
    }

    public void enemySender(Enemy enemy) {
        
        // Calculate the How fast The how much time the enemy will spend
        double seconds = standartSeconds / enemy.getSpeed();
        
        Group enemyGroup = enemy.getEnemy();
        PathTransition pathTransition = new PathTransition();
        uiPane.getChildren().add(enemyGroup);
        pathTransition.setDuration(Duration.seconds(seconds));
        pathTransition.setPath(EnemyPathAutoGenerator.getEnemyPath(uiPane));
        pathTransition.setNode(enemyGroup);
        //pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();

        pathTransition.setOnFinished(e -> {
            if (!Enemy.isDead(enemyGroup)) {
                HUDVariables.setLives(HUDVariables.getLives() - 1);
            }
            uiPane.getChildren().remove(enemyGroup);
        });
        
         
    }

}
