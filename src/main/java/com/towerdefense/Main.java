package com.towerdefense;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.towerdefense.map.MapCell;
import com.towerdefense.ui.TowerPanel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {

        BorderPane uiPane = MapCell.getMap(2);

        Pane mainDraggablePane = new Pane();
        TowerPanel.setMainDraggablePane(mainDraggablePane);

        StackPane towerPanel = TowerPanel.getTowerPanel();
        uiPane.setRight(towerPanel);

        StackPane mainPane = new StackPane(uiPane, mainDraggablePane);

        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}