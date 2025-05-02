package com.towerdefense.game;

import java.io.IOException;
import java.util.ArrayList;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.map.MapCell;

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
    ArrayList<ArrayList> enemyArrayList;
    BorderPane uiPane;

    public WaveManager(BorderPane uiPane) {
        
        this.waveCount = MapCell.currMap.getWaveCount(); 
        this.tiles = MapCell.currMap.getPath().size() / 2 - 1;
        this.standartSeconds = tiles / 2.0;
        this.enemyArrayList = new ArrayList<>();
        this.uiPane = uiPane;

        
        for (int i = 0; i < MapCell.currMap.getWaveCount(); i++) {
            ArrayList<Enemy> EnemyList = new ArrayList<>();

            for (int j = 0; j < MapCell.currMap.getEnemyCount(i); j++) {
                
                Enemy enemy = new Enemy(100, 1);
                EnemyList.add(enemy);
                
            }
             
            enemyArrayList.add(EnemyList);
        }
       
    }

    public void waveSender(int waveIndex) {
        System.out.println("Total enemies in wave: " + enemyArrayList.get(waveIndex).size());
        
        for (int i = 0; i < enemyArrayList.get(waveIndex).size(); i++) {
            final int eventFix = i;
            double delay = i * MapCell.currMap.getEnemyInterval(waveIndex) + 0.1; // to be fixed  
            System.out.println("Scheduling enemy " + i + " with delay: " + delay);
            
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delay), e -> {
                System.out.println("Sending enemy " + eventFix);
                enemySender((Enemy)(enemyArrayList.get(waveIndex).get(eventFix)));
            }));
            timeline.play();
        }     
    }

    public void enemySender(Enemy enemy) {
        
        // Calculate the How fast The how much time the enemy will spend
        double seconds = standartSeconds / enemy.getSpeed();
        

        PathTransition pathTransition = new PathTransition();
        uiPane.getChildren().add(enemy.getEnemy());
        pathTransition.setDuration(Duration.seconds(seconds));
        pathTransition.setPath(EnemyPathAutoGenerator.getEnemyPath(uiPane));
        pathTransition.setNode(enemy.getEnemy());
        //pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();

        pathTransition.setOnFinished(e -> {
            uiPane.getChildren().remove(enemy.getEnemy());
        });
        
         
    }

}
