package com.towerdefense.map;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.towerdefense.ui.TowerPanel;

public class MapCell {
    public static final int sideLength = 60;
    public static GameMapScanner currMap;

    public static GridPane getMap(int level) {

        String filePath = "src/main/resources/maps/level" + level + ".txt";
        GameMapScanner map = null;
        try {
            map = new GameMapScanner(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GridPane mapPane = showMap(map);
        currMap = map;

        return mapPane;

    }

    public static GridPane showMap(GameMapScanner map) {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        int heightCount = map.getHeight();
        int widthCount = map.getWidth();

        Random random = new Random();
        int randomColor;

        for (int row = 0; row < heightCount; row++) {
            for (int column = 0; column < widthCount; column++) {

                Rectangle rectangle = new Rectangle(sideLength, sideLength);
                rectangle.setArcHeight(10);
                rectangle.setArcWidth(10);

                randomColor = random.nextInt(2);

                if (isEnemyPath(map, row, column)) {
                    rectangle.setFill(Color.web("#f2e0c8"));
                } else {
                    switch (randomColor) {
                        case 0:
                            rectangle.setFill(Color.web("#fac443"));
                            break;
                        case 1:
                            rectangle.setFill(Color.web("#fbd058"));
                            break;
                    }
                }

                rectangle.setOpacity(0);
                rectangle.setScaleX(0);
                rectangle.setScaleY(0);
                gridPane.add(rectangle, column, row);

                FadeTransition fadeAnimation = new FadeTransition(Duration.millis(750), rectangle);
                fadeAnimation.setFromValue(0);
                fadeAnimation.setToValue(1);
                fadeAnimation.setDelay(Duration.millis(500 + row * 100));
                fadeAnimation.play();

                ScaleTransition scaleAnimation = new ScaleTransition(Duration.millis(750), rectangle);
                scaleAnimation.setToX(1);
                scaleAnimation.setToY(1);
                scaleAnimation.setDelay(Duration.millis(500 + row * 100));
                scaleAnimation.play();
            }
        }
        return gridPane;
    }

    public static boolean isEnemyPath(GameMapScanner map, int row, int column) {

        boolean isEnemyPath = false;
        ArrayList<String> path = map.getPath();

        for (int rowChecker = 0; rowChecker < path.size() - 1; rowChecker = rowChecker + 2) {
            if (row == Integer.parseInt(path.get(rowChecker)) && column == Integer.parseInt(path.get(rowChecker + 1))) {
                isEnemyPath = true;
                break;
            }
        }
        return isEnemyPath;
    }
}