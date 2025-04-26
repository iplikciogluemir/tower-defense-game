package com.towerdefense.map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
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

    public static BorderPane getMap(int level) throws FileNotFoundException, IOException {

        GameMapScanner map1 = new GameMapScanner(new File(
                "src/main/resources/maps/level1.txt"));
        GameMapScanner map2 = new GameMapScanner(new File(
                "src/main/resources/maps/level2.txt"));
        GameMapScanner map3 = new GameMapScanner(new File(
                "src/main/resources/maps/level3.txt"));
        GameMapScanner map4 = new GameMapScanner(new File(
                "src/main/resources/maps/level4.txt"));
        GameMapScanner map5 = new GameMapScanner(new File(
                "src/main/resources/maps/level5.txt"));

        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: #faf1da;");

        switch (level) {
            case 1:
                mainPane.setCenter(showMap(map1));
                break;
            case 2:
                mainPane.setCenter(showMap(map2));
                break;
            case 3:
                mainPane.setCenter(showMap(map3));
                break;
            case 4:
                mainPane.setCenter(showMap(map4));
                break;
            case 5:
                mainPane.setCenter(showMap(map5));
                break;
        }

        return mainPane;
    }

    public static GridPane showMap(GameMapScanner map) throws IOException {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        int heightCount = map.getHeight();
        int widthCount = map.getWidth();
        double sideLength = 60;

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
                    rectangle.setStroke(Color.web("#f2e0c8"));
                } else {
                    switch (randomColor) {
                        case 0:
                            rectangle.setFill(Color.web("#fac443"));
                            rectangle.setStroke(Color.web("#fac443"));
                            break;
                        case 1:
                            rectangle.setFill(Color.web("#fbd058"));
                            rectangle.setStroke(Color.web("#fbd058"));
                            break;
                    }
                }

                gridPane.add(rectangle, column, row);

            }
        }
        return gridPane;
    }

    public static boolean isEnemyPath(GameMapScanner map, int row, int column) throws IOException {

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