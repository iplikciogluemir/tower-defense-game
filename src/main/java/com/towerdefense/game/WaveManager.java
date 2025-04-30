package com.towerdefense.game;

import java.io.IOException;
import java.util.ArrayList;

import com.towerdefense.enemies.Enemy;
import com.towerdefense.enemies.EnemyPathAutoGenerator;
import com.towerdefense.map.MapCell;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
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

    WaveManager(BorderPane uiPane) throws IOException  {
        
        // Borderpane for getting its path
        this.uiPane = uiPane;


        //How many Waves in the current level
        this.waveCount = MapCell.currMap.getWaveCount(); 

        // Calculate How many tiles the enemy will actually walk 
        this.tiles = MapCell.currMap.getPath().size() / 2 - 1;

        //Standart Seconds a basic enemy will spend on the given level path
        this.standartSeconds = tiles / 2.0;

        // Create All Enemies in the level
        for (int i = 1 ; i <= MapCell.currMap.getWaveCount() ; i++ ){
            ArrayList<Enemy> EnemyList = new ArrayList<>();


            for (int j = 0 ; j < MapCell.currMap.getEnemyCount(i) ; j++){
                Enemy enemy = new Enemy(1000 , 1 );
                EnemyList.add(enemy);
            }
            enemyArrayList.add(EnemyList);
        }

    }

    

    // public void waveSender (int waveIndex){
    //     for (int i = 0 ; i < enemyArrayList.get(waveIndex-1).size() ; i++){
    //         enemySender((Enemy)(enemyArrayList.get(waveIndex-1).get(i)) );


    //     }

        
    }


    public void enemySender (Enemy enemy) throws IOException {
        // Calculate the How fast The how much time the enemy will spend 
        double seconds = standartSeconds / enemy.getSpeed();
        
        PathTransition pathTransition = new PathTransition();
        uiPane.getChildren().add(enemy.getEnemy());
        pathTransition.setDuration(Duration.seconds(seconds));
        pathTransition.setPath(EnemyPathAutoGenerator.getEnemyPath( uiPane ));
        pathTransition.setNode(enemy.getEnemy());
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        
        pathTransition.play();
        pathTransition.setOnFinished(e ->{
            uiPane.getChildren().remove(enemy.getEnemy());
        });


    }

    



}
