package com.towerdefense.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DragTowers {
    private static double initialXValue;
    private static double initialYValue;

    public static Group draggableTower(Group tower) {
        Group group = new Group();

        Circle towerCircle = new Circle(50);
        towerCircle.setFill(Color.rgb(255, 0, 0, 0.25));
        towerCircle.setVisible(false);

        group.getChildren().addAll(tower, towerCircle);

        tower.setOnMousePressed(e -> {

            initialXValue = e.getSceneX() - group.getTranslateX();
            initialYValue = e.getSceneY() - group.getTranslateY();

            towerCircle.setVisible(true);
            towerCircle.setCenterX(e.getSceneX());
            towerCircle.setCenterY(e.getSceneY());
        });

        tower.setOnMouseDragged(e -> {
            towerCircle.setCenterX(e.getSceneX());
            towerCircle.setCenterY(e.getSceneY());
            tower.setTranslateX(e.getSceneX() - initialXValue);
            tower.setTranslateY(e.getSceneY() - initialYValue);
        });

        tower.setOnMouseReleased(e -> {
            towerCircle.setVisible(false);
        });

        return group;
    }
}
