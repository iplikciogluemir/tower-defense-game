package com.towerdefense.ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.towerdefense.map.MapCell;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class DragTowers {

    private Pane draggablePane;
    private BorderPane map;

    public DragTowers(Pane draggablePane, BorderPane uiPane) throws IOException, FileNotFoundException {

        this.draggablePane = draggablePane;
        this.map = uiPane;

    }

    public Group clone(Group group) {

        Group clonedGroup = new Group();
        clonedGroup.getChildren().addAll(group.getChildren());

        return clonedGroup;
    }

    public void draggableTower(Label towerLabel, Group towerGroup) {

        Group clonedDraggableGroup = clone(towerGroup);
        Circle towerCircle = new Circle(200);
        towerCircle.setFill(Color.rgb(255, 0, 0, 0.4));

        towerCircle.setVisible(false);
        clonedDraggableGroup.setVisible(false);

        draggablePane.getChildren().addAll(clonedDraggableGroup, towerCircle);

        towerLabel.setOnMousePressed(e -> {

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            towerCircle.setVisible(true);
            clonedDraggableGroup.setVisible(true);

            clonedDraggableGroup
                    .setLayoutX(sceneX - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup
                    .setLayoutY(sceneY - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(sceneX);
            towerCircle.setCenterY(sceneY);

            e.consume();
        });

        towerLabel.setOnMouseDragged(e -> {

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            clonedDraggableGroup
                    .setLayoutX(sceneX - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
            clonedDraggableGroup
                    .setLayoutY(sceneY - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

            towerCircle.setCenterX(sceneX);
            towerCircle.setCenterY(sceneY);

            e.consume();
        });

        towerLabel.setOnMouseReleased(e -> {

            towerCircle.setVisible(false);
            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();
            boolean isEnteredPane = false;
            boolean isOnEnemyPath = false;

            for (Node rectangle : ((GridPane) map.getCenter()).getChildren()) {

                Bounds bounds = rectangle.localToScene(rectangle.getBoundsInLocal());

                Color currentColor = (Color) ((Rectangle) rectangle).getFill();
                Color checkColor = Color.web("#f2e0c8");
                if (currentColor.equals(checkColor))
                    isOnEnemyPath = true;

                if (bounds.contains(sceneX, sceneY)) {

                    isEnteredPane = true;
                    double centerX = draggablePane.sceneToLocal((bounds.getMinX() + bounds.getMaxX()) / 2, 0).getX();
                    double centerY = draggablePane.sceneToLocal(0, ((bounds.getMinY() + bounds.getMaxY()) / 2)).getY();

                    clonedDraggableGroup
                            .setLayoutX(centerX - clonedDraggableGroup.getBoundsInLocal().getWidth() / 2);
                    clonedDraggableGroup
                            .setLayoutY(centerY - clonedDraggableGroup.getBoundsInLocal().getHeight() / 2);

                    towerCircle.setCenterX(centerX);
                    towerCircle.setCenterY(centerY);
                }
            }

            if (!isEnteredPane || isOnEnemyPath) {

                towerCircle.setVisible(false);
                clonedDraggableGroup.setVisible(false);

            }

            repositioner(clonedDraggableGroup, towerCircle);
            e.consume();

        });
    }

    public void repositioner(Group group, Circle circle) {
        final double[] locationDifference = new double[2];

        group.setOnMousePressed(e -> {

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            locationDifference[0] = sceneX - group.getLayoutX();
            locationDifference[1] = sceneY - group.getLayoutY();

            circle.setVisible(true);
            e.consume();
        });

        group.setOnMouseDragged(e -> {

            double sceneX = e.getSceneX();
            double sceneY = e.getSceneY();

            group.setLayoutX(sceneX - locationDifference[0]);
            group.setLayoutY(sceneY - locationDifference[1]);

            circle.setCenterX(group.getLayoutX() + group.getBoundsInLocal().getWidth() / 2);
            circle.setCenterY(group.getLayoutY() + group.getBoundsInLocal().getHeight() / 2);

            e.consume();
        });

        group.setOnMouseReleased(e -> {
            circle.setVisible(false);
            e.consume();
        });
    }
}
