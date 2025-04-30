package com.towerdefense.enemies;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import com.towerdefense.map.MapCell;
import com.towerdefense.map.GameMapScanner;
import com.towerdefense.map.MapCell;
import com.towerdefense.ui.TowerPanel;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EnemyPathAutoGenerator {

    public static void EnemySender(Group enemy) throws IOException {

    }

    public static Path getEnemyPath(BorderPane uiPane) throws IOException {
         
        GameMapScanner map = MapCell.currMap;

        GridPane griddy = (GridPane) uiPane.getCenter();

        ArrayList<String> enemyPath = map.getPath();
        ArrayList<Integer> indexList = new ArrayList<>();
        

        for (int i = 0, j = 0; i < enemyPath.size() / 2; i++) {

            indexList.add(toIndex(map, Integer.parseInt(enemyPath.get(j)), Integer.parseInt(enemyPath.get(j + 1))));
            j += 2;

        }
        // First Enemy Path Start Point
        Path path = new Path();
        Point2D firstTile = ((Rectangle) (griddy.getChildren().get(indexList.get(0)))).localToScene(0, 0);
        path.getElements().add(new MoveTo(firstTile.getX() + MapCell.sideLength / 2, firstTile.getY() + MapCell.sideLength / 2));

        for (int i = 1; i < indexList.size(); i++) {
            Point2D tiles = ((Rectangle) (griddy.getChildren().get(indexList.get(i)))).localToScene(0, 0);
            path.getElements().add(new LineTo(tiles.getX() + MapCell.sideLength / 2, tiles.getY() + MapCell.sideLength / 2));

        }
        return path;

    }

    public static int toIndex(GameMapScanner map1, int row, int column) throws IOException {
        return column + row * map1.getWidth();
    }

    // @Override
    // public void start(Stage primaryStage) throws FileNotFoundException,
    // IOException {

    // BorderPane mainPane = MapCell.getMap(2);
    // mainPane.setRight(TowerPanel.getTowerPanel());
    // Scene scene = new Scene(mainPane, 500, 500);

    // GridPane griddy = (GridPane) mainPane.getCenter();
    // griddy.setStyle("-fx-background-color:rgb(0, 247, 66);");

    // griddy.setOnMouseClicked(e -> {
    // Point2D point = ((Rectangle) (griddy.getChildren().get(0))).localToScene(0,
    // 0);
    // double screenX = point.getX();
    // double screenY = point.getY();
    // System.out.println(screenX + " " + screenY);
    // Circle top = new Circle(screenX, screenY, 10);
    // top.setFill(Color.RED);
    // mainPane.getChildren().addAll(top);
    // });

    // primaryStage.setScene(scene);
    // primaryStage.show();

    // }

}
