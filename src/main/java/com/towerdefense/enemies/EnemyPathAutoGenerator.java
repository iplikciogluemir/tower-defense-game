package com.towerdefense.enemies;

import java.io.IOException;
import java.util.ArrayList;
import com.towerdefense.map.MapCell;
import com.towerdefense.Main;
import com.towerdefense.map.GameMapScanner;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class EnemyPathAutoGenerator {

    public static int linecount = 0;

    public static Path getEnemyPath(BorderPane uiPane) {

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
        path.getElements()
                .add(new MoveTo(firstTile.getX() + MapCell.sideLength / 2, firstTile.getY() + MapCell.sideLength / 2));

        for (int i = 1; i < indexList.size(); i++) {
            Point2D tiles = ((Rectangle) (griddy.getChildren().get(indexList.get(i)))).localToScene(0, 0);
            path.getElements()
                    .add(new LineTo(tiles.getX() + MapCell.sideLength / 2, tiles.getY() + MapCell.sideLength / 2));

        }
        if (linecount < 1) {
            ++linecount;
            path.setStroke(Color.BLACK);
            path.setStrokeWidth(1);
            uiPane.getChildren().add(path);
        }
        return path;

    }

    public static int toIndex(GameMapScanner map1, int row, int column) {
        return column + row * map1.getWidth();
    }

}
