package main.java.com.towerdefense.map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class MapCell extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GameMapScanner map1 = new GameMapScanner(new File(
                "C:\\Users\\2.0\\Desktop\\tower-defense-game\\src\\main\\resources\\maps\\levels\\level1.txt"));
        GameMapScanner map2 = new GameMapScanner(new File(
                "C:\\Users\\2.0\\Desktop\\tower-defense-game\\src\\main\\resources\\maps\\levels\\level2.txt"));
        GameMapScanner map3 = new GameMapScanner(new File(
                "C:\\Users\\2.0\\Desktop\\tower-defense-game\\src\\main\\resources\\maps\\levels\\level3.txt"));
        GameMapScanner map4 = new GameMapScanner(new File(
                "C:\\Users\\2.0\\Desktop\\tower-defense-game\\src\\main\\resources\\maps\\levels\\level4.txt"));
        GameMapScanner map5 = new GameMapScanner(new File(
                "C:\\Users\\2.0\\Desktop\\tower-defense-game\\src\\main\\resources\\maps\\levels\\level5.txt"));

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(showMap(map1));

        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public GridPane showMap(GameMapScanner map) throws Exception {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        int heightCount = map.getHeight();
        int widthCount = map.getWidth();
        double sideLength = 30;

        for (int row = 0; row < heightCount; row++) {
            for (int column = 0; column < widthCount; column++) {

                Rectangle rectangle = new Rectangle(sideLength, sideLength);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.WHITE);
                gridPane.add(rectangle, column, row);

            }
        }

        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}